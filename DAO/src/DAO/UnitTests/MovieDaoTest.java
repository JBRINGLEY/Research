package DAO.UnitTests;

import DAO.MovieDao;
import Data.DataFactory;
import DataContract.IMovie;
import junit.framework.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Random;

public class MovieDaoTest {

    @Test
    public void testAddMovie() {
        IMovie movieForAdd = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        int id = 0;
        try {
            id = MovieDao.AddMovie(movieForAdd);
        } catch (Exception e) {
            Assert.fail();
        }
        Assert.assertTrue(id > 0);
    }

    @Test
    public void testGetMovies() {
        List<IMovie> movies = null;
        try {
            movies = MovieDao.GetMovies();
        } catch (Exception e) {Assert.fail();}
        Assert.assertTrue(movies.size() > 0);
    }

    @Test
    public void testDeleteMovie() {
        List<IMovie> movies = null;
        try {
            movies = MovieDao.GetMovies();
            for(IMovie movie : movies) {
                if(movie.GetTitle().toLowerCase().contains("test")) {
                    MovieDao.DeleteMovie(movie);
                }
            }
        } catch (Exception ignored) {}
    }

    @Test
    public void testGetMovie() {
        IMovie movieForAdd = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        try {
            int id = MovieDao.AddMovie(movieForAdd);
            IMovie movieFromGet = MovieDao.GetMovie(movieForAdd);

            Assert.assertTrue(id == movieFromGet.GetId());
            Assert.assertTrue(movieForAdd.GetTitle().equals(movieFromGet.GetTitle()));
            Assert.assertTrue(movieForAdd.GetYear() == movieFromGet.GetYear());
        } catch (Exception ignored) {
            Assert.fail();
        }

        IMovie movieNotAdded = DataFactory.NewMovie(2007, "testTitle" + new Random()
                .nextInt(), true);
        try{

            IMovie movieFromGet = MovieDao.GetMovie(movieNotAdded);
            Assert.assertTrue(movieNotAdded.GetTitle().equals(movieFromGet
                    .GetTitle()));
            Assert.assertTrue(movieNotAdded.GetYear() == movieFromGet.GetYear
                    ());
        } catch (Exception ignored) {Assert.fail();}
    }
}
