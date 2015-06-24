package DAO;

import DataContract.ILemmaData;
import DataContract.ILemmatizedReview;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LemmaDao implements ILemmaData {

    public int AddLemmatizedReview(ILemmatizedReview review) {
        String insertSql = "INSERT INTO public.lemmareview(\"reviewId\", " +
                "\"review\", \"isTest\") " +
                "VALUES(?, ?, ?) RETURNING id";
        try {
            Class.forName(ConnectionHelper.DriverLocation);
            Connection connection = DriverManager.getConnection(
                    ConnectionHelper.ConnectionString,
                    ConnectionHelper.Username,
                    ConnectionHelper.Password);
            connection.setAutoCommit(false);
            PreparedStatement insertStatement = connection.prepareStatement
                    (insertSql);
            insertStatement.setInt(1, review.GetReviewId());
            insertStatement.setString(2, review.GetReview());
            insertStatement.setBoolean(3, review.IsTest());
            ResultSet insertResult = insertStatement.executeQuery();
            connection.commit();
            insertResult.next();
            connection.close();
            return insertResult.getInt(1);
        } catch (Exception ignored) {
            return 0;
        }
    }

}
