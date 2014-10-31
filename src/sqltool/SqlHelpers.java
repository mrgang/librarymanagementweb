package sqltool;

import com.mysql.jdbc.PreparedStatement;
import org.json.JSONArray;
import org.json.JSONObject;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ligan_000 on 2014/10/17.
 */
//工具类。
public class SqlHelpers {
    private static String driver = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/library_management";
    private static String user = "root", psw = "";
    private static Connection connection = null;
    //工具类的构造函数，与数据库建立链接。
    public SqlHelpers() {
        try {
            Class.forName(driver);
            System.out.println("数据库驱动加载成功！");

            connection = DriverManager.getConnection(url, user, psw);
            System.out.println("建立数据库链接成功！");

        } catch (ClassNotFoundException e) {
            System.out.println("数据库驱动加载失败！");
        } catch (SQLException e) {
            System.out.println("建立数据库链接出错！");
        }

    }
    //添加新书到书库。
    public static String addBook(String book_class_number, String book_name, String author, String press, String press_date, String contents, int total_count) {
        String sres = "success";
        PreparedStatement preparedStatement;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement("insert into book_inventory(book_class_number,book_name,author,press,press_date,contents,total_count,real_count)values(?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, book_class_number);
            preparedStatement.setString(2, book_name);
            preparedStatement.setString(3, author);
            preparedStatement.setString(4, press);
            preparedStatement.setString(5, press_date);
            preparedStatement.setString(6, contents);
            preparedStatement.setInt(7, total_count);
            preparedStatement.setInt(8, total_count);

            preparedStatement.execute();
        } catch (SQLException e) {
            sres = "fail";
            System.out.println("添加新书出错了。");
        }
        return sres;
    }
    //通过书名查找书籍。
    public static String selectBookName(String book_name) {
        JSONArray array = new JSONArray();
        JSONObject objectTemp;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        System.out.println("处理查询结果s！" + book_name);
        try {
            Statement statement = connection.createStatement();
            String sql = "select * from book_inventory where book_name like '%" + book_name + "%'";
            System.out.println(sql);
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println("处理查询结果" + rs.getString("contents"));
                objectTemp = new JSONObject();
                objectTemp.put("_id", rs.getInt("_id"));
                objectTemp.put("book_class_number", rs.getString("book_class_number"));
                objectTemp.put("book_name", rs.getString("book_name"));
                objectTemp.put("author", rs.getString("author"));
                objectTemp.put("press", rs.getString("press"));
                objectTemp.put("press_date", rs.getString("press_date"));
                objectTemp.put("contents", rs.getString("contents"));
                objectTemp.put("total_count", rs.getInt("total_count"));
                objectTemp.put("real_count", rs.getInt("real_count"));
                array.put(objectTemp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("建立按书名查找的statement出错");
            e.printStackTrace();
        }

        return array.toString();
    }
    //通过作者查找书籍。
    public static String selectAuthor(String author) {
        JSONArray array = new JSONArray();
        JSONObject objectTemp;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from book_inventory where author like '%" + author + "%'");
            while (rs.next()) {
                objectTemp = new JSONObject();
                objectTemp.put("_id", rs.getInt("_id"));
                objectTemp.put("book_class_number", rs.getString("book_class_number"));
                objectTemp.put("book_name", rs.getString("book_name"));
                objectTemp.put("author", rs.getString("author"));
                objectTemp.put("press", rs.getString("press"));
                objectTemp.put("press_date", rs.getString("press_date"));
                objectTemp.put("contents", rs.getString("contents"));
                objectTemp.put("total_count", rs.getInt("total_count"));
                objectTemp.put("real_count", rs.getInt("real_count"));
                array.put(objectTemp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("建立按书名查找的statement出错");
            e.printStackTrace();
        }

        return array.toString();
    }
    //通过书类号查找书籍。
    public static String selectBookClassName(String book_class_number) {
        JSONArray array = new JSONArray();
        JSONObject objectTemp;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from book_inventory where book_class_number like '%" + book_class_number + "%'");
            while (rs.next()) {
                objectTemp = new JSONObject();
                objectTemp.put("_id", rs.getInt("_id"));
                objectTemp.put("book_class_number", rs.getString("book_class_number"));
                objectTemp.put("book_name", rs.getString("book_name"));
                objectTemp.put("author", rs.getString("author"));
                objectTemp.put("press", rs.getString("press"));
                objectTemp.put("press_date", rs.getString("press_date"));
                objectTemp.put("contents", rs.getString("contents"));
                objectTemp.put("total_count", rs.getInt("total_count"));
                objectTemp.put("real_count", rs.getInt("real_count"));
                array.put(objectTemp);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("建立按书名查找的statement出错");
            e.printStackTrace();
        }

        return array.toString();
    }
    //用户归还书籍。
    public static boolean returnBook(String stu_number_or_idcard, String book_class_number) {
        boolean result = false;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            int count = 0;
            rs = statement.executeQuery("select real_count from book_inventory where book_class_number = '" + book_class_number + "'");
            while (rs.next()) {
                count = rs.getInt("real_count");
            }
            count = count + 1;

            PreparedStatement ps = (PreparedStatement) connection.prepareStatement("update book_inventory set real_count = ? where book_class_number = ?");
            ps.setInt(1, count);
            ps.setString(2, book_class_number);
            ps.execute();

            result = true;
            rs = statement.executeQuery("select _id from book_lends  where book_class_name = '" + book_class_number+"' and stu_number_or_idcard = "+stu_number_or_idcard);
            int mmid = 0;
            if (rs.next()){
                mmid = rs.getInt("_id");
            }
            String sql = "delete from book_lends where _id = "+mmid;
            System.out.println(sql);
            statement.execute(sql);

        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
    //用户注册。
    public static boolean regster(String stu_number_or_idcard, String stu_name, String password, String qq) {
        boolean result = false;
        PreparedStatement preparedStatement;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            preparedStatement = (PreparedStatement) connection
                    .prepareStatement("insert into user_info(stu_number_or_idcard,stu_name,password,qq)values(?,?,?,?)");
            preparedStatement.setString(1, stu_number_or_idcard);
            preparedStatement.setString(2, stu_name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, qq);

            preparedStatement.execute();
            result = true;
        } catch (SQLException e) {
            System.out.println("添加新用户出错了。");
        }

        return result;
    }
    //用户借阅书籍。
    public static boolean lendBook(String stu_number_or_idcard, int _id) {
        boolean result = false;
        if (stu_number_or_idcard.isEmpty()) return result;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            int count = 0;
            rs = statement.executeQuery("select real_count from book_inventory where _id = '" + _id + "'");
            while (rs.next()) {
                count = rs.getInt("real_count");
            }
            if (count < 1){return result;}
            count = count - 1;
            PreparedStatement ps = (PreparedStatement) connection.prepareStatement("update book_inventory set real_count = ? where _id = ?");
            ps.setInt(1, count);
            ps.setInt(2, _id);
            ps.execute();

            result = true;
            rs = statement.executeQuery("select * from user_info where stu_number_or_idcard = " + stu_number_or_idcard);
            String stu_name = null,qq = null;
            while (rs.next()) {
                stu_name = rs.getString("stu_name");
                qq = rs.getString("qq");
            }
            rs = statement.executeQuery("select * from book_inventory where _id =" + _id);
            String book_class_number = null;
            while (rs.next()) {
                book_class_number = rs.getString("book_class_number");
            }
            Date date = new Date();
            String rtime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);
            date.setMonth(date.getMonth()+3);
            String btime = new SimpleDateFormat("yyyyMMddHHmmss").format(date);

            statement.execute("insert into book_lends values (null ," + stu_number_or_idcard + ",'" + stu_name + "','" +book_class_number +"',"+rtime +","+btime+","+qq+")");

        } catch (SQLException e) {
            result = false;
            e.printStackTrace();
        }
        return result;
    }
    //用户登录。
    public static boolean login(String stu_number_or_idcard, String psword) {
        boolean result = false;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from user_info where stu_number_or_idcard = " + stu_number_or_idcard);
            while (rs.next()) {
                if (rs.getString("password").equals(psword)) {
                    result = true;
                    System.out.println("用户登录成功了");
                }
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("用户登录失败了");
            e.printStackTrace();
        }

        return result;
    }
    //查找用户信息。
    public static String selectUserInfo(String stu_number_or_idcard) {
        String result = null;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from user_info where stu_number_or_idcard = " + stu_number_or_idcard);
            JSONObject object = new JSONObject();
            while (rs.next()) {
                object.put("stu_number_or_idcard", rs.getString("stu_number_or_idcard"));
                object.put("stu_name", rs.getString("stu_name"));
                object.put("qq", rs.getString("qq"));
            }
            rs.close();
            result = object.toString();
        } catch (SQLException e) {
            System.out.println("查询用户信息失败了");
            e.printStackTrace();
        }

        return result;
    }
    //查看用户当前的借阅书籍。
    public static String selectLendBooks(String stu_number_or_idcard) {
        JSONArray array = new JSONArray();
        JSONObject object;
        ResultSet rs;
        if (connection == null) {
            try {
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, psw);
            } catch (ClassNotFoundException e) {
                System.out.println("数据库驱动加载失败！");
            } catch (SQLException e) {
                System.out.println("建立数据库链接出错！");
            }
        }
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("select * from book_lends where stu_number_or_idcard = " + stu_number_or_idcard);
            while (rs.next()) {
                object = new JSONObject();
                object.put("book_class_name",rs.getString("book_class_name"));
                object.put("lend_time",rs.getString("lend_time"));
                object.put("return_time",rs.getString("return_time"));
                array.put(object);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("查找用户借阅历史");
            e.printStackTrace();
        }

        return array.toString();
    }
}
