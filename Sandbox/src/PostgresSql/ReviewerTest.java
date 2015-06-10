package PostgresSql;
import org.postgresql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class ReviewerTest {
    public static void main(String[] args) throws Exception{
        String url = "jdbc:postgresql://localhost/";
        String user = "schwabea530";
        String password = "";
        Connection conn= DriverManager.getConnection(url, user, password);
        Statement sqlStatement = conn.createStatement();

        String sqlString = "SELECT * FROM public.dbo.Reviewer";
        sqlStatement.executeQuery(sqlString);
        sqlStatement.close();
        conn.commit();
    }
}
