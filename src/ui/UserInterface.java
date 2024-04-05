package ui;

import datasource.FileHandler;
import domain_model.*;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Boolean programIsRunning = true;
    Scanner userInput;

    String commandParameter;
    FileHandler fileHandler;

    MovieCollection movieCollectionArr = new MovieCollection();

    public UserInterface() {
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
        fileHandler = new FileHandler();
    }

    public void startProgram() {
        System.out.println("*********************************");
        System.out.println("*                               *");
        System.out.println("*   MALTHE'S MOVIE COLLECTION   *");
        System.out.println("*                               *");
        System.out.println("*********************************\n");
        menuInformation();

        while (programIsRunning) {
            String command = userInput.nextLine().toLowerCase();

            switch (command) {
                case "1" -> {
                    movieCollectionArr.addMovie(new Movie("Die Hard", "Malthe Bay", "Action", 1990, 190, true, 5));
                    createAndAddMovieToMovieList();

                }
                case "2" -> {
                    System.out.println("Here is a list of alle the movies in the collection:");
                    getListOfMovies();

                }
                case "3" -> {
                    System.out.println("write the ID of the movie you would like to edit");
                    editMovieFromMovielist();

                }
                case "4" -> {
                    System.out.println("please enter the title of the movie you are looking for.");
                    searchMovieOnList();

                }
                case "5" -> {
                    System.out.println("please enter the title of the movie you will like to remove.");
                    removeMovieFromList();
                    menuInformation();
                }
                case "6" -> {
                    menuInformation();

                }
                case "9" -> {
                    System.out.println("goodbye");
                    programIsRunning = false;
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong information, try again");
                    menuInformation();
                }
            }
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

    public void createAndAddMovieToMovieList() {
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
        movieCollectionArr.addMovie(movie);
        fileHandler.writeFile(movieCollectionArr.getMovieListArr());
        System.out.println("The movie has been added you your filmlist");
    }


    public void getListOfMovies() {
        if (movieCollectionArr.movieListArr.isEmpty()) {
            noMoviesOnList();
        } else {
            System.out.println(movieCollectionArr.movieListArr.toString());
        }
    }

    public void editMovieFromMovielist() {
        int searchNumber = userInput.nextInt();

        for (Movie m : movieCollectionArr.getMovieListArr()) {
            if (movieCollectionArr.getMovieListArr().isEmpty()) {
                noMoviesOnList();
            } else if (m.getMovieID() == searchNumber) {
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
            } else {
                System.out.println("No movie with the ID " + searchNumber + " found");
            }
        }
    }

    public void searchMovieOnList() {
        ArrayList<Movie> tempMovieList = new ArrayList<>();
        String titleToSearchFor = userInput.nextLine();

        for (Movie i : movieCollectionArr.getMovieListArr()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                tempMovieList.add(i);
                System.out.println();
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

        for (Movie i : movieCollectionArr.getMovieListArr().stream().toList()) {
            if (i.getTitle().toLowerCase().contains(titleToSearchFor.toLowerCase())) {
                movieToRemove = i;
                System.out.println("The movie " + titleToSearchFor + " has been removed from collection");
            }
        }
        if (movieToRemove != null) {
            movieCollectionArr.removeMovie(movieToRemove);
        } else {
            System.out.println("there is no movie on the list withe the name " + titleToSearchFor + ".");
        }
    }

    public void noMoviesOnList() {
        System.out.println("there are no movies on the list");
        System.out.println("\n");
        menuInformation();
    }
}

