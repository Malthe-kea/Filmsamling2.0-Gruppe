package domain_model;

import datasource.FileHandler;
import ui.UserInterface;

import java.util.Scanner;
import java.util.ArrayList;



public class MovieCollection {
Scanner userInput = new Scanner(System.in);

FileHandler fileHandler = new FileHandler();

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
                System.out.println("domain_model.Movie found! Please enter the new information for the movie");
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

                System.out.println("The movie " + m.getTitle() +" has been edited.");
            } else {
                System.out.println("No movie with the ID " + searchNumber + " found");
            }
        }
    }
    public void noMoviesOnList() {
        System.out.println("there are no movies on the list");
        System.out.println("\n");
        menuInformation();
    }

    public void getListOfMovies() {
        if (movieListArr.isEmpty()) {
            noMoviesOnList();
        } else {
            System.out.println(movieListArr.toString());
        }
    }
    public void searchMovieOnList() {
        ArrayList<Movie> tempMovieList = new ArrayList<>();
        String titleToSearchFor = userInput.nextLine();

        for (Movie i : getMovieListArr()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                tempMovieList.add(i);
                System.out.println();
                System.out.println("Movie found! \n");
                System.out.println(i.toString());
            } else if (i.getTitle().toLowerCase().isEmpty()) {
                System.out.println("there is no movie on the list withe the name " + i + ".");
            } else {
                System.out.println("Invalid input");
            }
        }
    }
    public void removeMovieFromList() {
        ArrayList<Movie> tempMovieList = new ArrayList<>();
        String titleToSearchFor = userInput.nextLine();

        Movie movieToRemove = null;

        for (Movie i : getMovieListArr().stream().toList()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                movieToRemove = i;
                System.out.println("The movie " + titleToSearchFor + " has been removed from collection");
            }
        }
        if (movieToRemove != null) {
            removeMovie(movieToRemove);
        } else {
            System.out.println("there is no movie on the list withe the name " + titleToSearchFor + ".");
        }
    }
    public void menuInformation() {
        System.out.println("You now have the following options");
        System.out.println("Press 1 - Add movie to your collection");
        System.out.println("Press 2 - See movie collection");
        System.out.println("Press 3 - Edit movie from collection");
        System.out.println("Press 4 - Search for movie from collection");
        System.out.println("press 5 - Remove a title from collection");
        System.out.println("Press 6 - Show Menu");
        System.out.println("Press 9 - Exit");
    }



}
