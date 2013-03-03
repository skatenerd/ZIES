package SudokuBackend;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/16/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckThenActBase<T extends Cloneable<T>> {
    public AtomicInteger collisions = new AtomicInteger(0);

    protected AtomicReference<T> dataPoint;

    public CheckThenActBase(T data){
        this.dataPoint = new AtomicReference<T>(data);
    }

    public void OptimisticUpdate(Updater<T> toExecute) {
        T initialValue = dataPoint.get();
        T newPoint = toExecute.update(GetSnapshot());

        if (setDataPoint(initialValue, newPoint)){
            return;
        }else{
            collisions.getAndIncrement();
            OptimisticUpdate(toExecute);
        }
    }

    public T GetSnapshot(){
        return dataPoint.get().Clone();
    }

    private boolean setDataPoint(T expected, T newValue){
        return dataPoint.compareAndSet(expected, newValue);
    }

}
