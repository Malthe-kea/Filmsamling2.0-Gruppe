package domain_model;

import datasource.FileHandler;

import java.util.Scanner;
import java.util.ArrayList;


public class MovieCollection {
    Scanner userInput = new Scanner(System.in);

    FileHandler fileHandler = new FileHandler();

    private ArrayList<Movie> movieListArr = new ArrayList<>();

    public void addMovie(Movie movie) {
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

    public void createAndAddMovieToMovieList() {
        userInput.useDelimiter("\n");
        System.out.println("Enter a title:");
        String title = userInput.nextLine();

        System.out.println("Enter a director:");
        String director = userInput.nextLine();

        System.out.println("Enter a genre:");
        String genre = userInput.nextLine();

        System.out.println("Enter the year the movie was created:");
        int year = userInput.nextInt();

        System.out.println("Enter the length in minutes:");
        int lenghtInMinute = userInput.nextInt();

        System.out.println("Is the movie in color? yes/no");
        boolean isInColor = false;
        String colorOrNot = userInput.next().toLowerCase();
        if (colorOrNot.equals("yes")) {
            isInColor = true;
        }

        System.out.println("Enter a unique movie ID");
        int movieID = userInput.nextInt();

        Movie movie = new Movie(title, director, genre, year, lenghtInMinute, isInColor, movieID);
        addMovie(movie);
        fileHandler.writeFile(getMovieListArr());
        System.out.println("The movie has been added you your filmlist");
    }

    public void editMovieFromMovielist() {
        userInput.useDelimiter("\n");
        int searchNumber = userInput.nextInt();

        for (Movie m : getMovieListArr()) {
            if (getMovieListArr().isEmpty()) {
                noMoviesOnList();
            } else if (m.getMovieID() == searchNumber) {
                userInput.nextLine();
                System.out.println("Movie found! Please enter the new information for the movie");
                System.out.println("Enter a title:");
                String title = userInput.nextLine();

                System.out.println("Enter a director:");
                String director = userInput.nextLine();

                System.out.println("Enter a genre:");
                String genre = userInput.nextLine();

                System.out.println("Enter the year the movie was created:");
                int year = userInput.nextInt();

                System.out.println("Enter the length in minutes:");
                int lenghtInMinutes = userInput.nextInt();

                System.out.println("Is the movie in color? yes/no");
                boolean isInColor = false;
                String colorOrNot = userInput.next().toLowerCase();
                if (colorOrNot.equals("yes")) {
                    isInColor = true;
                }
                Movie currentMovie = m;
                currentMovie.setTitle(title);
                currentMovie.setDirector(director);
                currentMovie.setGenre(genre);
                currentMovie.setYear(year);
                currentMovie.setLengthInMinutes(lenghtInMinutes);
                currentMovie.setInColor(isInColor);

                System.out.println("The movie " + m.getTitle() + " has been edited.");
            } else {
                System.out.println("No movie with the ID " + searchNumber + " found");
            }
        }
    }

    public String noMoviesOnList() {
        String noMovie;
        return noMovie = "No movies on list";
    }

    public String getListOfMovies() {
        if (movieListArr.isEmpty()) {
            return noMoviesOnList();
        } else {
            return movieListArr.toString();
        }
    }

    public String searchMovieOnList(String titleToSearchFor) {
        ArrayList<Movie> tempMovieList = new ArrayList<>();

        for (Movie i : getMovieListArr()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                tempMovieList.add(i);
                return "Movie found\n" + i.getTitle();
            } else if (i.getTitle().isEmpty()) {
                return "there is no movie on the list withe the name " + i + ".";
            } else {
                return "Invalid input";
            }
        }
        return null;
    }

    public String removeMovieFromList(String titleToSearchFor) {
        Movie movieToRemove = null;

        for (Movie i : getMovieListArr().stream().toList()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                movieToRemove = i;
            }
        }
        if (movieToRemove != null) {
            removeMovie(movieToRemove);
            return "The movie " + titleToSearchFor + " has been removed from collection";
        } else {
            return "there is no movie on the list withe the name " + titleToSearchFor + ".";
        }
    }

}
