package SudokuBackend;

import java.sql.Connection;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/18/13
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class SuDokuRules {
    private Integer[][]data;
    private Integer row, col, value;
    private SuDokuRules(Integer[][]state, Integer row, Integer col, Integer value){
        this.data = state;
        this.row = row;
        this.col = col;
        this.value = value;
    }

    public static boolean Legal(Integer[][]state, Integer row, Integer col, Integer value){
        SuDokuRules instance = new SuDokuRules(state, row, col, value);
        if (value == SudokuState.blank){
            return true;
        }
        return (instance.colLegal() && instance.rowLegal() && instance.gridLegal() && instance.spotUnoccupied());
    }

    private boolean spotUnoccupied(){
        return(data[row][col] == null);
    }

    private boolean colLegal(){
        for(int curRow = 0; curRow < 9; curRow ++){
            if(data[curRow][col] == value){
                return false;
            }
        }
        return true;
    }
    private boolean rowLegal(){
        for(int curCol = 0; curCol < 9; curCol ++){
            if(data[row][curCol] == value){
                return false;
            }
        }
        return true;
    }

    private boolean gridLegal(){
        int minRow = (row / 3) * 3;
        int minCol = (col / 3) * 3;
        for(int curRow = minRow; curRow < (minRow + 3); curRow++){
            for(int curCol = minCol; curCol < (minCol + 3); curCol++){
                if(data[curRow][curCol] == value){
                    return false;
                }
            }
        }
        return true;

    }

}
