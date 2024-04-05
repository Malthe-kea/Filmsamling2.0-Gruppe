package ui;

import datasource.FileHandler;
import domain_model.*;

import java.util.Scanner;

public class UserInterface {
    private Boolean programIsRunning = true;
    Scanner userInput;
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
        System.out.println("*            WELCOME            *");
        System.out.println("*                               *");
        System.out.println("*********************************\n");
        movieCollectionArr.menuInformation();

        while (programIsRunning) {
            String command = userInput.nextLine().toLowerCase();

            switch (command) {
                case "1" -> {
                    movieCollectionArr.addMovie(new Movie("Die Hard", "Michael Bay", "Action", 1990, 120, true, 1));
                    //movieCollectionArr.createAndAddMovieToMovieList();

                }
                case "2" -> {
                    System.out.println("Here is a list of alle the movies in the collection:");
                    movieCollectionArr.getListOfMovies();

                }
                case "3" -> {
                    System.out.println("write the ID of the movie you would like to edit");
                    movieCollectionArr.editMovieFromMovielist();

                }
                case "4" -> {
                    System.out.println("please enter the title of the movie you are looking for.");
                    movieCollectionArr.searchMovieOnList();

                }
                case "5" -> {
                    System.out.println("please enter the title of the movie you will like to remove.");
                    movieCollectionArr.removeMovieFromList();
                    movieCollectionArr.menuInformation();
                }
                case "6" -> {
                    movieCollectionArr.menuInformation();

                }
                case "9" -> {
                    System.out.println("goodbye");
                    programIsRunning = false;
                    System.exit(0);
                }
                default -> {
                    System.out.println("Wrong information, try again");
                    movieCollectionArr.menuInformation();
                }
            }
        }
    }
}

