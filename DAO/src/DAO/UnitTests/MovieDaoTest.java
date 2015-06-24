package DAO.UnitTests;

import DAO.MovieDao;
import Data.DataFactory;
import DataContract.IMovie;
import DataContract.IMovieData;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class MovieDaoTest {

    @Test
    public void testAddMovie() {
        IMovieData movieDao = new MovieDao();

        IMovie movieForAdd = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        int id = 0;
        try {
            id = movieDao.AddMovie(movieForAdd);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(id > 0);
    }

    @Test
    public void testGetMovies() {
        IMovieData movieDao = new MovieDao();

        List<IMovie> movies = null;
        try {
            movies = movieDao.GetMovies();
        } catch (Exception e) {Assert.fail();}
        Assert.assertTrue(movies.size() > 0);
    }

    @Test
    public void testDeleteMovie() {
        IMovieData movieDao = new MovieDao();

        List<IMovie> movies = null;
        try {
            movies = movieDao.GetMovies();
            for(IMovie movie : movies) {
                if(movie.GetTitle().toLowerCase().contains("test")) {
                    movieDao.DeleteMovie(movie);
                }
            }
        } catch (Exception ignored) {}
    }

    @Test
    public void testGetMovie() {
        IMovieData movieDao = new MovieDao();
        IMovie movieForAdd = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        try {
            int id = movieDao.AddMovie(movieForAdd);
            IMovie movieFromGet = movieDao.GetMovie(movieForAdd);

            Assert.assertTrue(id == movieFromGet.GetId());
            Assert.assertTrue(movieForAdd.GetTitle().equals(movieFromGet.GetTitle()));
            Assert.assertTrue(movieForAdd.GetYear() == movieFromGet.GetYear());
        } catch (Exception ignored) {
            Assert.fail();
        }

        IMovie movieNotAdded = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        try{

            IMovie movieFromGet = movieDao.GetMovie(movieNotAdded);
            Assert.assertTrue(movieNotAdded.GetTitle().equals(movieFromGet
                    .GetTitle()));
            Assert.assertTrue(movieNotAdded.GetYear() == movieFromGet.GetYear
                    ());
        } catch (Exception ignored) {Assert.fail();}
    }
}
