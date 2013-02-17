import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/16/13
 * Time: 6:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class CheckThenActBase<T> {
    public AtomicInteger collisions = new AtomicInteger(0);

    protected AtomicReference<T> dataPoint;

    public CheckThenActBase(T data){
        this.dataPoint = new AtomicReference<T>(data);
    }

    public void checkThenAct(ArgumentCallable<T, T> toExecute) throws Exception{
        T data = getDataPoint();

        T newPoint = toExecute.call(data);

        if (setDataPoint(data, newPoint)){
            return;
        }else{
            collisions.getAndIncrement();
            checkThenAct(toExecute);
        }
    }
    public T getDataPoint(){
        return dataPoint.get();
    }
    private boolean setDataPoint(T expected, T newValue){
        return dataPoint.compareAndSet(expected, newValue);
    }

}
