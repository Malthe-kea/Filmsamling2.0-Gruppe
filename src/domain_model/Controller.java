package domain_model;

public class Controller {

    MovieCollection movieCollection;

    public Controller() {
        movieCollection = new MovieCollection();
    }

    public String createAndAddMovieToMovieList(String title, String director, String genre, int year, int lengthInMinutes, boolean isInColor) {
        return movieCollection.createAndAddMovieToMovieList(title, director, genre, year, lengthInMinutes, isInColor);
    }


    public String editMovieFromMovielist(int idToEdit, String editString, int editIndex) {
        return movieCollection.editMovieFromMovielist(idToEdit, editString, editIndex);
    }


    public String getListOfMovies() {
        return movieCollection.getListOfMovies();
    }

    public String searchMovieOnList(String stringToSearchFor, int i) {
        return movieCollection.searchMovieOnList(stringToSearchFor, i);
    }

    public String removeMovieFromList(int movieIdToRemove) {
        return movieCollection.removeMovieFromList(movieIdToRemove);
    }


    public String sortMovieList(int primary, int secondary) {
        return movieCollection.sortMovieList(primary, secondary);
    }

}
