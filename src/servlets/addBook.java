package servlets;

import sqltool.SqlHelpers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by ligan_000 on 2014/10/21.
 */
@WebServlet("/servlets/addBook")
public class addBook extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String book_class_number = req.getParameter("book_class_number");
        String book_name = req.getParameter("book_name");
        String author = req.getParameter("author");
        String press = req.getParameter("press");
        String press_date = req.getParameter("press_date");
        String contents = req.getParameter("contents");
        int total_count = Integer.valueOf(req.getParameter("total_count"));

        System.out.println("服务器得到的数据 " + book_class_number);
        String res = SqlHelpers.addBook(book_class_number, book_name, author, press, press_date, contents, total_count);
        PrintWriter out = resp.getWriter();
        out.println(res);
        out.flush();
    }
}
