package SudokuBackend;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 5:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class SudokuState implements Cloneable<SudokuState>{
    Integer[][] data;
    public static Integer blank = null;

    public SudokuState(){
        data = new Integer[9][9];
    }
    public String prettyString(){
        StringBuilder builder = new StringBuilder();
        builder.append("_|__0__,__1__,__2__|__3__,__4__,__5__|__6__,__7__,__8__|<br/>");
        int index = 0;
        for(Integer[] row:data){
            builder.append(formatRow(row, index));
            builder.append("<br/>");
            builder.append(rowSeparator(index));
            index++;
        }
        return builder.toString();
    }

    private String rowSeparator(Integer index){
        boolean isRowBarrier =  ((index % 3) == 2);
        return isRowBarrier ? "++++++++++++++++++++++++++++++++++++++++++++<br/>" : "";
    }

    private String  formatRow(Integer[] row, Integer rowIndex){
        StringBuilder builder = new StringBuilder(rowIndex + "|");
        int colIndex = 0;
        for(Integer entry:row){
            String entryString="_____";
            if(!blankEntry(entry)){
                entryString = "__" + entry.toString() + "__";
            }
            builder.append(entryString);
            builder.append(colSeparator(colIndex));
            colIndex++;
        }

        return builder.toString();
    }

    private String colSeparator(Integer index){
       boolean isColumnBarrier =  ((index % 3) == 2);
       return isColumnBarrier ? "|" : ",";
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
        if(SuDokuRules.Legal(data, row,col,value)){
            setDataAt(row, col, value);
        }
        return this;
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
