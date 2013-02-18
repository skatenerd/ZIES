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
        String style = request.getParameter("style");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else if(pathInfo.equals("/update")){
            handleUpdate(request,  response);
        }else if(pathInfo.equals("/soft_update")){
            handleSoftUpdate(request,  response);
        }else if(pathInfo.equals("/poll")){
            handleQuery(response);
        }else{
            PrintWriter out = response.getWriter();
            out.println("BAD ACTION");
        }
    }


    private void handleUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final GameSingleton singleton = GameSingleton.Instance();
        final int row = Integer.parseInt(request.getParameter("row"));
        final int col = Integer.parseInt(request.getParameter("col"));
        final int value = Integer.parseInt(request.getParameter("value"));
        try{
            singleton.checkThenAct(new ArgumentCallable<SudokuState, SudokuState>() {
                @Override
                public SudokuState call(SudokuState oldState) {
                    return oldState.hardUpdate(row,col,value);
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
            singleton.checkThenAct(new ArgumentCallable<SudokuState, SudokuState>() {
                @Override
                public SudokuState call(SudokuState oldState) {
                    return oldState.softUpdate(row,col,value);
                }
            });
        }catch (Exception e){System.out.println("ERROR");};
        PrintWriter out = response.getWriter();
        if(singleton.getDataPoint().updateSucceeded(row,col, value)){
            response.sendRedirect("/sudoku");
        }else{
            out.println("SOMEONE GOT THERE FIRST!!!");
            out.println("STATE IS:\n" + singleton.getDataPoint().prettyString());
        }
    }

    private void handleQuery(HttpServletResponse response) throws IOException{
        GameSingleton singleton = GameSingleton.Instance();
        PrintWriter out = response.getWriter();
        out.println(singleton.getDataPoint().prettyString());

    }
}
