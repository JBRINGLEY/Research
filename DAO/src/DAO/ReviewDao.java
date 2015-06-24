package DAO;

import DataContract.IReview;
import DataContract.IReviewData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDao implements IReviewData {

    public int AddReview(IReview review)  throws Exception{
        String sqlString = "INSERT INTO public.Reviews" +
                "(\"rating\", \"reviewDate\", \"review\", \"reviewerId\", " +
                "\"istest\", \"movieid\") " +
                "VALUES(?,?,?,?,?,?) RETURNING *";
        Class.forName(ConnectionHelper.DriverLocation);
        Connection connection = DriverManager.getConnection(
                ConnectionHelper.ConnectionString,
                ConnectionHelper.Username,
                ConnectionHelper.Password);
        connection.setAutoCommit(false);
        PreparedStatement sqlStatement = connection.prepareStatement
                (sqlString);
        sqlStatement.setInt(1, review.GetRating());
        sqlStatement.setDate(2, new Date(review.GetReviewDate().toEpochDay()));
        sqlStatement.setString(3, review.GetReview());
        sqlStatement.setInt(4, review.GetReviewer().GetId());
        sqlStatement.setBoolean(5, review.IsTest());
        sqlStatement.setInt(6, review.GetMovie().GetId());

        ResultSet sqlResult = sqlStatement.executeQuery();
        sqlResult.next();
        connection.commit();
        connection.close();
        return sqlResult.getInt(1);
    }

    public List<Integer> AddReviews(List<IReview> reviews) throws
            Exception{
        List<Integer> idsFromAdd = new ArrayList<Integer>();
        for(IReview review : reviews) {
            int idFromAdd = AddReview(review);
            idsFromAdd.add(new Integer(idFromAdd));
        }
        return idsFromAdd;
    }

    public void DeleteReview(IReview review) throws Exception{
        if(review.GetId() != 0) {
            String sqlString = "DELETE FROM public.Reviews t " +
                    "WHERE t.id = " + review.GetId();
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

    public void DeleteReviews(List<IReview> reviews) throws Exception {
        for(IReview review : reviews) {
            DeleteReview(review);
        }
    }

  public List<Integer> GetReviewIds() throws Exception {
    String sqlQuery = "SELECT r.id FROM public.reviews r " +
            "WHERE r.isTest = false";
    Class.forName(ConnectionHelper.DriverLocation);
    Connection conn = DriverManager.getConnection(
            ConnectionHelper.ConnectionString,
            ConnectionHelper.Username,
            ConnectionHelper.Password);
    PreparedStatement sqlStatement = conn.prepareStatement(sqlQuery);

    ResultSet result = sqlStatement.executeQuery();
    List<Integer> returnList = new ArrayList<Integer>();
    while(result.next()) {
      returnList.add(new Integer(result.getInt("id")));
    }
    return returnList;
  }
}
