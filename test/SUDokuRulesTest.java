import SudokuBackend.SuDokuRules;
import SudokuBackend.SudokuState;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/18/13
 * Time: 5:20 PM
 * To change this template use File | Settings | File Templates.
 */

public class SUDokuRulesTest {
    @Test
    public void emptyLegal(){
        Integer[][] state = new Integer[9][9];
        assertTrue(SuDokuRules.Legal(state, 1, 0, 0));
    }

    @Test
    public void nullAlwaysLegal(){
        Integer[][] state = new Integer[9][9];
        state[1][1]=3;
        assertTrue(SuDokuRules.Legal(state, 1, 1, null));
    }

    @Test
    public void sameSpotIllegal(){
        Integer[][] state = new Integer[9][9];
        state[1][1]=3;
        assertFalse(SuDokuRules.Legal(state, 1, 1, 6));

    }
    @Test
    public void colLegal(){
        Integer[][] state = new Integer[9][9];
        state[0][0] = 0;
        assertFalse(SuDokuRules.Legal(state, 1, 0, 0));
    }
    @Test
    public void rowLegal(){
        Integer[][] state = new Integer[9][9];
        state[0][0] = 0;
        assertFalse(SuDokuRules.Legal(state, 0, 5, 0));
    }
    @Test
    public void boxLegal(){
        Integer[][] state = new Integer[9][9];
        state[8][8] = 0;
        assertFalse(SuDokuRules.Legal(state, 7, 7, 0));
        assertTrue(SuDokuRules.Legal(state, 0, 0, 0));
    }
    @Test
    public void boxLegalAgain(){
        Integer[][] state = new Integer[9][9];
        state[3][3] = 0;
        assertTrue(SuDokuRules.Legal(state, 0, 0, 0));
        assertFalse(SuDokuRules.Legal(state, 4, 4, 0));
    }
}
