import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class GameSingleton extends CheckThenActBase<SudokuState> {
    private static GameSingleton instance = null;

    public static GameSingleton Instance(){
        if(instance == null){
            instance = new GameSingleton();
        }
        return instance;

    }

    private GameSingleton(){
        super(new SudokuState());
    }

}
