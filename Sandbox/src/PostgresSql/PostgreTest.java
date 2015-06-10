package PostgresSql;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class PostgreTest {
    public static void main(String[] args) throws Exception {
        String url = "jdbc:postgresql://localhost/research";
        String user = "schwabea530";
        String password = "";

        Class.forName("org.postgresql.Driver");

        String newstring ="";
        Connection connection = DriverManager.getConnection(url, user, password);

        System.out.println(connection.getSchema());
        Statement statmnt = connection.createStatement();
        statmnt.execute("INSERT INTO public.\"dbo.Movie\"(\"title\", " +
                "\"year\") VALUES " +
                "('TEST'," +
                " 2007);");
        connection.close();
    }
}
