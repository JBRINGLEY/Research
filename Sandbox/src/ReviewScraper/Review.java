package ReviewScraper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Review {
    private String _username;
    private String _review;
    private Date _reviewDate;
    private int _userRating;

    public Review(String username, int rating,
                  String review, String reviewDate) throws ParseException {
        if(username == null)
            throw new NullPointerException("The username passed is null");
        if (rating < 0)
            throw new IllegalArgumentException("The rating passed is < 0");
        if(review == null)
            throw new NullPointerException("The review passed is null");
        if(reviewDate == null)
            throw new NullPointerException("The review date passed is null");

        SimpleDateFormat formatter = new SimpleDateFormat("MMMM dd, yyyy");
        _username =username;
        _userRating = rating;
        _review = review;
        _reviewDate = formatter.parse(reviewDate);
    }
    public String GetUserName() {return _username;}
    public String GetReview() {return _review;}
    public int GetRating() {return _userRating;}
    public Date GetReviewDate() {return _reviewDate;}

    @Override
    public String toString() {
        StringBuilder buildString = new StringBuilder();
        buildString.append("[username, " + _username + " ] ");
        buildString.append("[review date, " + _reviewDate + " ] ");
        buildString.append("[rating, " + _userRating + " ] ");
        return buildString.toString();
    }
}
