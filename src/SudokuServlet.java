import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class SudokuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            handleQuery(response);
        }else if(pathInfo.equals("/update")){
            handleUpdate(request,  response);
        }else if(pathInfo.equals("/soft_update")){
            handleSoftUpdate(request,  response);
        }else{
            PrintWriter out = response.getWriter();
            out.println("BAD ACTION");
        }





//
//
//        PrintWriter out = response.getWriter();
//        String haha = request.getParameter("datta");
//        out.println("Hello World: " + haha);
    }
    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final GameSingleton singleton = GameSingleton.Instance();
        final int row = Integer.parseInt(request.getParameter("row"));
        final int col = Integer.parseInt(request.getParameter("col"));
        final int value = Integer.parseInt(request.getParameter("value"));
        try{
            singleton.checkThenAct(new ArgumentCallable<Integer[][], Integer[][]>() {
                @Override
                public Integer[][] call(Integer [][] oldState) {
                    Integer[][] newState = singleton.CopyState(oldState);
                    newState[row][col] = value;
                    return newState;
                }
            });
        }catch (Exception e){System.out.println("ERROR");};
        response.sendRedirect("/sudoku");
    }
    private void handleSoftUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final GameSingleton singleton = GameSingleton.Instance();
        final int row = Integer.parseInt(request.getParameter("row"));
        final int col = Integer.parseInt(request.getParameter("col"));
        final int value = Integer.parseInt(request.getParameter("value"));
        try{
            singleton.checkThenAct(new ArgumentCallable<Integer[][], Integer[][]>() {
                @Override
                public Integer[][] call(Integer [][] oldState) {
                    Integer[][] newState = singleton.CopyState(oldState);
                    if(newState[row][col] == null){
                    newState[row][col] = value;
                    }
                    return newState;
                }
            });
        }catch (Exception e){System.out.println("ERROR");};
        PrintWriter out = response.getWriter();
        if(singleton.getDataPoint()[row][col] != value){
            out.println("SOMEONE GOT THERE FIRST!!!");
            out.println("STATE IS:\n" + singleton.prettyString());

        }else{
            response.sendRedirect("/sudoku");
        }
    }

    private void handleQuery(HttpServletResponse response) throws IOException{
        GameSingleton singleton = GameSingleton.Instance();
        PrintWriter out = response.getWriter();
        out.println("Hello World:\n" + singleton.prettyString());

    }
}
