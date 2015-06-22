package DAO;

import Data.DataFactory;
import DataContract.IMovie;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    // region Public Methods
    public static int AddMovie(IMovie movie)
            throws Exception {

        String insertSql = "INSERT INTO public.Movies(\"title\", " +
                "\"year\", \"istest\") VALUES (?, ?, ?) RETURNING *";
        ResultSet returnedId = null;
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection =
                DriverManager.getConnection(ConnectionHelper.ConnectionString,
                        ConnectionHelper.Username,
                        ConnectionHelper.Password);
        connection.setAutoCommit(false);
        if (!MovieExists(movie)) {
            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
            insertStatement.setString(1, movie.GetTitle());
            insertStatement.setInt(2, movie.GetYear());
            insertStatement.setBoolean(3, movie.IsTest());
            returnedId = insertStatement.executeQuery();
            connection.commit();
            connection.close();
            returnedId.next();
            return returnedId.getInt("id");
        }

        connection.close();

        return GetMovieId(movie);
    }

    public static IMovie GetMovie(IMovie movie) {
        if (MovieExists(movie)) {
            int id = GetMovieId(movie);
            return DataFactory.NewMovie(id, movie.GetYear(), movie.GetTitle(),
                    movie.IsTest());
        }
        try {
            int id = AddMovie(movie);
            return DataFactory.NewMovie(id, movie.GetYear(), movie.GetTitle(),
                    movie.IsTest());
        } catch (Exception ignored) {}
        return null;
    }

    public static boolean MovieExists(IMovie movie) {
        try {
            if (movie.GetId() != 0) {
                return MovieExistsBy(movie.GetId());
            } else {
                return MovieExistsBy(movie.GetTitle(), movie.GetYear());
            }
        } catch (Exception e) {
            return false;
        }
    }

    public static void DeleteMovie(IMovie movie) throws Exception {
        if(MovieExists(movie)) {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection =
                    DriverManager.getConnection(ConnectionHelper.ConnectionString,
                            ConnectionHelper.Username,
                            ConnectionHelper.Password);
            String deleteSql;
            if (movie.GetId() > 0) {
                deleteSql = "DELETE FROM public.Movies t " +
                        "WHERE t.id = " + movie.GetId();
                }
            else {
                deleteSql = "DELETE FROM public.Movies t "+
                        "WHERE t.title = " + movie.GetTitle() +
                        " AND t.year = " + movie.GetYear();
            }
            PreparedStatement deleteStatement = connection.prepareStatement
                    (deleteSql);
            deleteStatement.execute();
            connection.commit();
            connection.close();
        }
    }

    public static List<IMovie> GetMovies() throws Exception {
        String sql = "SELECT t.id, t.title, t.year, t.istest FROM public.Movies t";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);

        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet result = statement.executeQuery();
        connection.close();
        List<IMovie> returnList = new ArrayList<IMovie>();
        while(result.next()) {
            returnList.add(DataFactory.NewMovie(result.getInt("id"),
                    result.getInt("year"),
                    result.getString("title"),
                    result.getBoolean("istest")));
        }
        return returnList;
    }

    // endregion

    // region Private methods
    private static boolean MovieExistsBy(int id) throws Exception {
        String idSql = "SELECT t.id  FROM public.Movies t " +
                "WHERE t.id = ?";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);

            PreparedStatement idStatement = connection.prepareStatement(idSql);
            idStatement.setInt(1, id);

            ResultSet idResult = idStatement.executeQuery();
            idResult.next();
            connection.close();
            return true;
        } catch (Exception ignored) {return false;}
    }

    private static boolean MovieExistsBy(String title,
                                         int year) throws Exception {
        String sqlString = "SELECT t.id FROM public.Movies t" +
                " WHERE t.title LIKE ?";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        sqlStatement.setString(1, title);

        ResultSet sqlResult = sqlStatement.executeQuery();
        sqlResult.next();
        connection.close();
        return sqlResult.getInt(1) > 0;

    }

    private static int GetMovieId(IMovie movie) {
        try {
            String idSql = "SELECT t.id FROM public.Movies t " +
                    "WHERE t.title LIKE ?";

            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection =
                    DriverManager.getConnection(ConnectionHelper.ConnectionString,
                            ConnectionHelper.Username,
                            ConnectionHelper.Password);

            PreparedStatement idStatement = connection.prepareStatement(idSql);
            idStatement.setString(1, movie.GetTitle());
            ResultSet idResult = idStatement.executeQuery();
            connection.close();
            idResult.next();
            return idResult.getInt(1);
        } catch (Exception ignored) {
        }
        throw new IllegalArgumentException("Movie passed was not found");
    }
    // endregion
}
