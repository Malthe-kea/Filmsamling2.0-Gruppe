import java.util.Scanner;
import java.util.ArrayList;

public class MovieCollection {

    ArrayList<Movie> movieListArr = new ArrayList<>();
    Scanner userInput = new Scanner(System.in);


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
