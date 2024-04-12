package domain_model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieCollectionTest {

    MovieCollection movieCollection = new MovieCollection();
    int testMovieID = 999;
    Movie testMovie = new Movie("Test Movie", "Test Director", "Test Genre", 2001, 200, true, testMovieID);

    @Test
    void addMovie() {

        //Act
        int expected = movieCollection.getMovieListArr().size()+1;
        movieCollection.createAndAddMovieToMovieList("Unicorn 1", "Noah", "Action", 2024, 120, true);
        int actual = movieCollection.getMovieListArr().size();

        //Assert
        assertEquals(expected,actual);
        movieCollection.removeMovieFromList(movieCollection.getMovieListArr().get(movieCollection.getMovieListArr().size()-1).getMovieID());
    }

    @Test
    void editTitle() {

        //Act
        String expected = "Edited Test Title";
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        movieCollection.editMovieFromMovielist(testMovieID,expected,1);
        String actual = testMovie.getTitle();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void editDirector() {

        //Act
        String expected = "Edited Test Director";
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        movieCollection.editMovieFromMovielist(testMovieID,expected,2);
        String actual = testMovie.getDirector();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void editGenre() {

        //Act
        String expected = "Edited Test Genre";
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        movieCollection.editMovieFromMovielist(testMovieID,expected,3);
        String actual = testMovie.getGenre();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void editYear() {
        //Act
        int expected = 2001;
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        movieCollection.editMovieFromMovielist(testMovieID,(expected+""),4);
        int actual = testMovie.getYear();

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void editYearInvalid() {

        //Act
        String expected = "Not a valid input. Please input a number.";
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        String actual = movieCollection.editMovieFromMovielist(testMovieID,"I am not a number",4);

        //Assert
        assertEquals(expected,actual);

    }

    @Test
    void editLengthInMinutesInvalid() {

        //Act
        String expected = "Not a valid input. Please input a number.";
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        String actual = movieCollection.editMovieFromMovielist(testMovieID,"I am not a number",4);

        //Assert
        assertEquals(expected,actual);


    }



    @Test
    void editIsInColor() {


        //Act
        boolean expected = true;
        /*
            1 - Edit title
            2 - Edit director
            3 - Edit genre
            4 - Edit year
            5 - Edit length in minutes
            6 - Edit is in color
         */
        movieCollection.editMovieFromMovielist(testMovieID,(expected ? "yes" : "no"),6);
        boolean actual = testMovie.getInColor();

        //Assert
        assertEquals(expected,actual);

        movieCollection.removeMovieFromList(testMovieID);
    }


    @Test
    void getListOfMovies() {
        //We test this by splitting the String Movie ID, as it is the only unique in the string.
        //If the list of movies splitted is equal to the size PLUS ONE (first list is splitted in two
        //For logical reasons), then the list contains all movies.

        //Arrange
        String listString = movieCollection.getListOfMovies();
        String[] listArray = listString.split("Movie ID");

        //Act
        int expected = movieCollection.getMovieListArr().size();
        int actual = listArray.length-1;

        //Assert
        assertEquals(expected,actual);


    }

    @Test
    void searchMovieOnList() {
        //Arrange
        int movieID = 999;
        movieCollection.getMovieListArr().add(testMovie);
        String listString = movieCollection.searchMovieOnList("Test", 1);
        String[] listArray = listString.split("Movie ID: ");
        System.out.println();

        //Act
        int expected = movieID;
        int actual = Integer.parseInt(listArray[1].substring(0,3));

        //Assert
        assertEquals(expected,actual);


    }

    @Test
    void removeMovieFromList() {
        //Arrange
        int movieID = 999;
        movieCollection.getMovieListArr().add(testMovie);

        //Act
        int expected = movieCollection.getMovieListArr().size()-1;
        movieCollection.removeMovieFromList(movieID);
        int actual = movieCollection.getMovieListArr().size();

        //Assert
        assertEquals(expected,actual);



    }

    @Test
    void sortMovieList() {
        //Arrange
        int movieID = 878;
        Movie testMovie = new Movie("AAAAAA", "Test Director", "Test Genre", 2001, 200, true, movieID);
        movieCollection.getMovieListArr().add(testMovie);
        String listString = movieCollection.sortMovieList(1, 2);
        String[] listArray = listString.split("Movie ID: ");

        //Act
        int expected = movieID;
        int actual = Integer.parseInt(listArray[1].substring(0,3));

        assertEquals(expected,actual);

        movieCollection.removeMovieFromList(movieID);

    }


    @BeforeEach
    void addTestMovie() {
        movieCollection.getMovieListArr().add(testMovie);
    }

    @AfterEach
    void removeTestMovie() {
        movieCollection.removeMovieFromList(testMovieID);
        movieCollection.removeMovieFromList(testMovieID);
    }
}