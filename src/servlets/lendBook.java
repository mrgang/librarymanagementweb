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
 * Created by ligan_000 on 2014/10/28.
 */
@WebServlet("/servlets/lendBook")
public class lendBook extends HttpServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("cache-control", "no-cache");
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String stu_number_or_idcard = req.getParameter("stu_number");
        int book_id = Integer.parseInt(req.getParameter("book_id"));

        System.out.println("服务器得到的数据 "+stu_number_or_idcard+" "+book_id);
        boolean res = SqlHelpers.lendBook(stu_number_or_idcard,book_id);
        PrintWriter out = resp.getWriter();
        out.println(res+"");
        out.flush();
    }
}
