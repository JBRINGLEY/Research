package DataContract;

import java.util.List;

public interface IMovieData {
    int AddMovie(IMovie movie) throws Exception;
    public IMovie GetMovie (IMovie movie);
    boolean MovieExists(IMovie movie);
    void DeleteMovie(IMovie movie) throws Exception;
    List<IMovie> GetMovies() throws Exception;
}
