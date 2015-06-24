package DAO;

import Data.DataFactory;
import DataContract.IReviewer;
import DataContract.IReviewerData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ReviewerDao implements IReviewerData {

    public List<Integer> AddReviewers(List<IReviewer> reviewers)
            throws Exception {
        List<Integer> idsFromAdd = new ArrayList<Integer>();
        for(IReviewer reviewer : reviewers) {
            int idReturned = AddReviewer(reviewer);
            idsFromAdd.add(idReturned);
        }
        return idsFromAdd;
    }

    public void DeleteReviewers(List<IReviewer> reviewers) throws
            Exception {
        for(IReviewer reviewer : reviewers) {
            DeleteReviewer(reviewer);
        }
    }

    public int AddReviewer(IReviewer reviewer) throws Exception {
        if(!ReviewerExists(reviewer)) {
            String sqlString = "INSERT INTO " +
                    "public.Reviewer(\"username\", \"istest\") " +
                    "VALUES(?, ?) RETURNING *";
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            connection.setAutoCommit(false);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            sqlStatement.setString(1, reviewer.GetName());
            sqlStatement.setBoolean(2, reviewer.IsTest());

            ResultSet results = sqlStatement.executeQuery();
            connection.commit();
            connection.close();
            results.next();
            return results.getInt("id");
        }
        return GetReviewerId(reviewer);
    }

    public boolean ReviewerExists(IReviewer reviewer) {
        try {
            String sqlString = null;
            if (reviewer.GetId() != 0) {
                sqlString = "SELECT * FROM public.Reviewer t " +
                        "WHERE t.id = ?";
            }
            else {
                sqlString = "SELECT * FROM public.Reviewer t " +
                        "WHERE t.username LIKE ?";
            }
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            if(reviewer.GetId() != 0) {
                sqlStatement.setInt(1, reviewer.GetId());
            }
            else {
                sqlStatement.setString(1, reviewer.GetName());
            }
            ResultSet result = sqlStatement.executeQuery();
            connection.close();
            result.next();
            result.getInt("id");
            return true;
        } catch (Exception ignored) {
            return false;
        }
    }

    public List<IReviewer> GetReviewers() throws Exception {
        String sqlString = "SELECT * FROM public.Reviewer";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        ResultSet results = sqlStatement.executeQuery();
        connection.close();
        List<IReviewer> reviewerList = new ArrayList<IReviewer>();
        while (results.next()) {
            IReviewer reviewer = DataFactory.NewReviewer(results.getInt("id"),
                    results.getString("username"),
                    results.getBoolean("istest"));
            reviewerList.add(reviewer);
        }
        return reviewerList;
    }

    public void DeleteReviewer(IReviewer reviewer) throws Exception{
        if(ReviewerExists(reviewer)){
            String sqlString = "DELETE FROM public.Reviewer t " +
                    "WHERE t.id = " + reviewer.GetId();
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            PreparedStatement sqlStatement = connection.prepareStatement
                    (sqlString);
            sqlStatement.execute();
            connection.close();
        }
    }

    private int GetReviewerId(IReviewer reviewer) throws Exception {
        String sqlString = "SELECT t.id FROM public.Reviewer t " +
                "WHERE t.username LIKE ?";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        sqlStatement.setString(1, reviewer.GetName());
        ResultSet result = sqlStatement.executeQuery();
        connection.close();
        result.next();
        return result.getInt("id");
    }
}
