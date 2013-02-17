import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameSingleton extends CheckThenActBase<Integer[][]> {
    private static GameSingleton instance = null;

    public static GameSingleton Instance(){
        if(instance == null){
            instance = new GameSingleton();
        }
        return instance;

    }
    public String prettyString(){
        StringBuilder builder = new StringBuilder();
        for(Integer[] row:getDataPoint()){
            builder.append(formatRow(row));
            builder.append("\n");
        }
        return builder.toString();
    }

    private String  formatRow(Integer[] row){
        StringBuilder builder = new StringBuilder();
        for(Integer entry:row){
            String entryString="_____";
            if(entry != null){
                entryString = entry.toString();
            }
            builder.append(String.format("%5s", entryString));
            builder.append(",");
        }

        return builder.toString();

    }

    private GameSingleton(){
        super(new Integer[9][9]);
    }

    public Integer[][] CopyState(Integer[][] oldState){
        Integer[][]clone = new Integer[9][];
        for(int row=0;row<9;row++){
            clone[row] = Arrays.copyOf(oldState[row], 9);
        }
        return clone;
    }
}
