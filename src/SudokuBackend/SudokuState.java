package SudokuBackend;

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
    public static Integer blank = null;

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
            if(!blankEntry(entry)){
                entryString = "____" + entry.toString();
            }
            builder.append(entryString);
            builder.append(",");
        }

        return builder.toString();
    }

    private boolean blankEntry(Integer entry){
        return (entry == blank);
    }

    private boolean entriesEqual(Integer first, Integer second){
        return first == second;
    }

    public SudokuState Clone(){
        SudokuState clone = new SudokuState();
        for(int row=0;row<9;row++){
            clone.data[row] = Arrays.copyOf(data[row], 9);
        }
        return clone;
    }

    public SudokuState updateIfLegal(Integer row, Integer col, Integer value){
        SudokuState newState = Clone();
        if(SuDokuRules.Legal(data, row,col,value)){
            newState.setDataAt(row, col, value);
        }
        return newState;
    }

    public Integer dataAt(int row, int col){
        System.out.println(data);
        return data[row][col];
    }

    private void setDataAt(Integer row, Integer col, Integer val){
        data[row][col] = val;
    }

    public boolean updateSucceeded(Integer row, Integer col, Integer val){
        return entriesEqual(dataAt(row,col), val);
    }
}
