package domain_model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MovieCollectionTest {

    @Test
    public void testRemoveMovieFromList() {

        //arrange
        Controller controller = new Controller();

        controller.movieCollection.createAndAddMovieToMovieList("Die Hard", "Michael Bay", "Action", 1992, 89,true);
        controller.movieCollection.createAndAddMovieToMovieList("Tomorrow Never Dies", "Hanne Boel", "Rom-com", 1973, 45,true);
        controller.movieCollection.createAndAddMovieToMovieList("Batman Forever", "Michael Bay", "Action", 1991, 2,true);


        //Act
        controller.removeMovieFromList(1);

        String removedMovieTitle = controller.movieCollection.searchMovieOnList("Die Hard", 1);
        String movieOnListToRemove = null;

        //Assert
        assertEquals(movieOnListToRemove, removedMovieTitle);


        assertNull(controller.movieCollection.searchMovieOnList(movieOnListToRemove, 1));
    }
}