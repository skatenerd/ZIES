import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SudokuState {
    Integer[][] data;

    public SudokuState(){
        data = new Integer[9][9];
    }
    public String prettyString(){
        StringBuilder builder = new StringBuilder();
        for(Integer[] row:data){
            builder.append(formatRow(row));
            builder.append("<br/>");
        }
        return builder.toString();
    }

    private String  formatRow(Integer[] row){
        StringBuilder builder = new StringBuilder();
        for(Integer entry:row){
            String entryString="_____";
            if(entry != null){
                entryString = "____" + entry.toString();
            }
            builder.append(entryString);
            builder.append(",");
        }

        return builder.toString();
    }
    public SudokuState Clone(){
        //Integer[][]clone = new Integer[9][];
        SudokuState clone = new SudokuState();
        for(int row=0;row<9;row++){
            clone.data[row] = Arrays.copyOf(data[row], 9);
        }
        return clone;
    }

    public SudokuState hardUpdate(int row, int col, int value){
        SudokuState newState = Clone();
        newState.setDataAt(row,col,value);
        return newState;
    }

    public SudokuState softUpdate(int row, int col, int value){
        SudokuState newState = Clone();
        if(dataAt(row,col) == null){
            newState.setDataAt(row, col, value);
        }
        return newState;
    }
    public Integer dataAt(int row, int col){
        return data[row][col];
    }

    public void setDataAt(int row, int col, int val){
        data[row][col] = val;
    }
    public boolean updateSucceeded(int row, int col, int val){
        return (dataAt(row,col) == val);
    }
}
