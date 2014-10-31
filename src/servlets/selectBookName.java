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
@WebServlet("/servlets/selectByName")
public class selectBookName extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String book_name = req.getParameter("book_name");

        System.out.println("selectByName服务器得到的数据："+book_name);
        String res = SqlHelpers.selectBookName(book_name);
        PrintWriter out = resp.getWriter();
        out.println(res);
        out.flush();
    }
}
