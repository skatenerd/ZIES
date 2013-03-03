package SudokuBackend;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created with IntelliJ IDEA.
 * User: 8thlight
 * Date: 2/17/13
 * Time: 2:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class SudokuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        final Integer row = Integer.parseInt(request.getParameter("row"));
        final Integer col = Integer.parseInt(request.getParameter("col"));
        final Integer value = parseValueInput(request.getParameter("value"));
        handleUpdate(row, col, value, request, response);
    }
    public static String formatError(HttpServletRequest request){
        String error = request.getParameter("error");
        if(error == null){
            return "";
        }else{
            return error;
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if(pathInfo == null){
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }else if(pathInfo.equals("/poll")){
            handleQuery(response);
        }else{
            PrintWriter out = response.getWriter();
            out.println("BAD ACTION");
        }
    }

    private void handleUpdate(final Integer row, final Integer col, final Integer value, final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        final GameSingleton singleton = GameSingleton.Instance();

        singleton.OptimisticUpdate(new Updater<SudokuState>() {
            @Override
            public SudokuState update(SudokuState oldState) {
                return oldState.updateIfLegal(row, col, value);
            }
        }
        );

        PrintWriter out = response.getWriter();
        if(singleton.GetSnapshot().updateSucceeded(row,col, value)){
            response.sendRedirect("/sudoku");
        }else{
            response.sendRedirect("/sudoku?error=ILLEGAL_MOVE");
        }
    }

    private Integer parseValueInput(String input){
        Integer result = Integer.parseInt(input);
        if (result < 0){
            return null;
        }
        return result;
    }

    private void handleQuery(HttpServletResponse response) throws IOException{
        GameSingleton singleton = GameSingleton.Instance();
        PrintWriter out = response.getWriter();
        out.println(singleton.GetSnapshot().prettyString());

    }
}
