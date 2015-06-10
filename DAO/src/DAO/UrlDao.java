package DAO;

import Data.DataFactory;
import DataContract.IUrl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UrlDao {

    // region Visited url data access
    public static List<Integer> AddUrls(List<IUrl> urls) throws Exception{
        List<Integer> idsFromAdd = new ArrayList<Integer>();
        for(IUrl url : urls) {
            idsFromAdd.add(new Integer(AddUrl(url)));
        }
        return idsFromAdd;
    }

    public static void DeleteUrls(List<IUrl> urls) throws Exception{
        for(IUrl url : urls) {
            DeleteUrl(url);
        }
    }

    public static int AddUrl(IUrl url) throws Exception{
        String sqlString = "INSERT INTO public.\"dbo.VisitedUrl\"(\"url\", " +
                "\"istest\") VALUES(?, ?) RETURNING *";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        sqlStatement.setString(1, url.GetUrl());
        sqlStatement.setBoolean(2, url.IsTest());

        ResultSet sqlResult = sqlStatement.executeQuery();
        sqlResult.next();
        connection.commit();
        connection.close();
        return sqlResult.getInt(1);
    }

    public static void DeleteUrl(IUrl url) throws Exception{
        String sqlString = "DELETE FROM public.\"dbo.VisitedUrl\" " +
                "WHERE id = " + url.GetId();
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        sqlStatement.execute();
        connection.commit();
        connection.close();
    }

    public static List<IUrl> GetUrls() throws Exception {
        String sqlString = "SELECT * FROM public.\"dbo.VisitedUrl\"";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        ResultSet results =  sqlStatement.executeQuery();
        connection.commit();
        connection.close();
        List<IUrl> urlList = new ArrayList<IUrl>();
        while(results.next()) {
            urlList.add(DataFactory.NewUrl(results.getInt("id"),
                    results.getString("url"),
                    results.getBoolean("istest")));
        }
        return urlList;
    }

    public static boolean UrlVisited(IUrl url) {
        String sqlString = "SELECT id FROM public.\"dbo.VisitedUrl\" " +
                "WHERE url LIKE ?";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            sqlStatement.setString(1, url.GetUrl());
            ResultSet result = sqlStatement.executeQuery();
            result.next();
            result.getInt("id");
            connection.commit();
            connection.close();
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }
    // endregion

    // region Url to visit data access
    public static int UrlsToVisitCount() {
        String sqlString = "SELECT COUNT(t.id) AS count " +
                " FROM public.\"dbo.UrlsToVisit\" t";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            ResultSet result = sqlStatement.executeQuery();
            result.next();
            connection.commit();
            connection.close();
            return result.getInt("count");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static void AddUrlToVisit(IUrl url) {
        String sqlString = "INSERT INTO public.\"dbo.UrlsToVisit\"(\"url\", " +
                "\"istest\") VALUES(?, ?) RETURNING *";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            sqlStatement.setString(1, url.GetUrl());
            sqlStatement.setBoolean(2, url.IsTest());
            ResultSet result = sqlStatement.executeQuery();
            result.next();
            result.getInt("id");
            connection.commit();
            connection.close();
        } catch (Exception e) {
        }
    }

    public static IUrl GetUrlToVisit() {
        String countString = "SELECT COUNT(*) AS movieUrlCount FROM public.\"dbo.UrlsToVisit\"" +
                " WHERE url LIKE '%/m/%type=user%'";
        String movieString = "SELECT * FROM public.\"dbo.UrlsToVisit\" WHERE " +
                "url LIKE '%/m/%type=user%'";
        String generalUrlSqlString = "SELECT * FROM public.\"dbo.UrlsToVisit\" LIMIT 1";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement movieUrlCountSql = connection.prepareStatement
                    (countString);
            ResultSet countResult = movieUrlCountSql.executeQuery();
            countResult.next();

            ResultSet result = null;
            if(countResult.getInt("movieUrlCount") > 0) {
              PreparedStatement movieUrlSql = connection.prepareStatement
                      (movieString);
                result = movieUrlSql.executeQuery();
            }
            else {
                PreparedStatement sqlStatement = connection.prepareStatement
                        (generalUrlSqlString);
                result = sqlStatement.executeQuery();
            }
            result.next();
            connection.commit();
            connection.close();
            IUrl urlFromGet = DataFactory.NewUrl(result.getInt("id"),
                    result.getString("url"),
                    result.getBoolean("istest"));
            DeleteUrlToVisit(urlFromGet);
            return urlFromGet;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void DeleteUrlToVisit(IUrl url) {

        String sqlString = "DELETE FROM public.\"dbo.UrlsToVisit\" t " +
                "WHERE t.id = " + url.GetId();
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            sqlStatement.execute();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // endregion

}
