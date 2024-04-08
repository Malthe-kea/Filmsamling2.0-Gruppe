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
        print("*********************************");
        print("*                               *");
        print("*            WELCOME            *");
        print("*                               *");
        print("*********************************\n");
        menuInformation();

        while (programIsRunning) {
            String command = userInput.nextLine().toLowerCase();

            switch (command) {
                case "1" -> {
                    movieCollectionArr.addMovie(new Movie("Die Hard", "Michael Bay", "Action", 1990, 120, true, 1));
                    //movieCollectionArr.createAndAddMovieToMovieList();

                }
                case "2" -> {
                    print("Here is a list of alle the movies in the collection:");
                    movieCollectionArr.getListOfMovies();

                }
                case "3" -> {
                    print("write the ID of the movie you would like to edit");
                    movieCollectionArr.editMovieFromMovielist();

                }
                case "4" -> {
                    print("please enter the title of the movie you are looking for.");
                    movieCollectionArr.searchMovieOnList();

                }
                case "5" -> {
                    print("please enter the title of the movie you will like to remove.");
                    movieCollectionArr.removeMovieFromList();
                }
                case "6" -> {
                    menuInformation();

                }
                case "9" -> {
                    print("goodbye");
                    programIsRunning = false;
                    System.exit(0);
                }
                default -> {
                    print("Wrong information, try again");
                    menuInformation();
                }
            }
        }
    }

    private void menuInformation() {
        print("""
                You now have the following options
                Press 1 - Add movie to your collection
                Press 2 - See movie collection
                Press 3 - Edit movie from collection
                Press 4 - Search for movie from collection
                press 5 - Remove a title from collection
                Press 6 - Show Menu
                Press 9 - Exit
                """);
    }

    private void print(String s) {
        System.out.println(s);
    }
}

