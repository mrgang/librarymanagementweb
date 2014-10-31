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
@WebServlet("/servlets/returnBook")
public class returnBook extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String stu_number_or_id = req.getParameter("stu_number_or_id");
        String book_class_number = req.getParameter("book_class_number");

        System.out.println("还书服务器得到的数据 "+stu_number_or_id+"  "+book_class_number);
        boolean res = SqlHelpers.returnBook(stu_number_or_id,book_class_number);
        PrintWriter out = resp.getWriter();
        out.println(res);
        out.flush();
    }
}
