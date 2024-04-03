package domain_model;

import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {

    public ArrayList<Movie> movieListArr = new ArrayList<>();


    public void addMovie (Movie movie) {
        movieListArr.add(movie);
    }

    public ArrayList<Movie> getMovieListArr() {
        return movieListArr;
    }
    public void removeMovie(Movie movie) {
        movieListArr.remove(movie);
    }

    public void setMovieListArr(ArrayList movieListArr) {
        this.movieListArr = movieListArr;
    }

}
