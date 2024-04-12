package ui;

import datasource.FileHandler;
import domain_model.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInterface {
    private Boolean programIsRunning;
    Scanner userInput;
    FileHandler fileHandler;

    Controller controller;
    boolean editPossible;

    public UserInterface() {
        controller = new Controller();
        programIsRunning = true;
        userInput = new Scanner(System.in);
        userInput.useDelimiter("\n");
        fileHandler = new FileHandler();
        editPossible = true;
    }

    public void startProgram() {
        print("*********************************");
        print("*                               *");
        print("*            WELCOME            *");
        print("*                               *");
        print("*********************************\n");

        while (programIsRunning) {
            menuInformation();
            String command = userInput.nextLine().toLowerCase();

            switch (command) {
                case "1" -> {

                    print(addMovie());

                }
                case "2" -> {
                    print("Here is a list of alle the movies in the collection:");
                    print(controller.getListOfMovies());
                }
                case "3" -> {
                    editMovie();
                }
                case "4" -> {
                    searchForMovie();
                }
                case "5" -> {
                    print(searchForMovie());
                    print("From the list above, please select the id of the movie you wish to edit.");
                    int movieIdToRemove = userInput.nextInt();
                    print(controller.removeMovieFromList(movieIdToRemove));
                }

                case "6" -> {
                    print(sortMovies());
                }
                case "7" -> {
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

    private String addMovie() {
        print("Enter a title:");
        String title = userInput.nextLine();

        print("Enter a director:");
        String director = userInput.nextLine();

        print("Enter a genre:");
        String genre = userInput.nextLine();

        print("Enter the year the movie was created:");
        int year = userInput.nextInt();

        print("Enter the length in minutes:");
        int lenghtInMinute = userInput.nextInt();

        print("Is the movie in color? yes/no");
        boolean isInColor = false;
        String colorOrNot = userInput.next().toLowerCase();
        if (colorOrNot.equals("yes")) {
            isInColor = true;
        }

        return controller.createAndAddMovieToMovieList(title, director, genre, year, lenghtInMinute, isInColor);
    }

    private void menuInformation() {
        print("""
                You now have the following options
                Press 1 - Add movie to your collection
                Press 2 - See movie collection
                Press 3 - Edit movie from collection
                Press 4 - Search for movie from collection
                press 5 - Remove a title from collection
                Press 6 - Sort list
                Press 7 - Show Menu
                Press 9 - Exit
                """);
    }

    private void sortMenuInformation(String s) {
        System.out.printf("""
                Select %s attribute to sort by:
                Press 1 - Sort movies by title
                Press 2 - Sort movies by director
                Press 3 - Sort movies by genre
                Press 4 - Sort movies by year
                Press 5 - Sort movies by length in minutes
                Press 6 - Sort movies by color
                """, s);
    }

    private String searchForMovie() {
        editPossible = true;
        print("""
                Select attribute to search by:
                Press 1 - Search movies by title
                Press 2 - Search movies by director
                Press 3 - Search movies by genre
                Press 4 - Search movies by year
                Press 5 - Search movies by length in minutes
                Press 6 - Search movies by color
                """);
        int searchIndex = 0;
        try {
            searchIndex = userInput.nextInt();
        } catch(RuntimeException re) {
            editPossible = false;
            return "Invalid number has been inputted";
        }

        if(searchIndex < 1 || searchIndex > 6) {
            editPossible = false;
            return "You need to input a number between 1 and 6.";
        } else {
            print("please enter the title of the movie you are looking for.");
            String searchWord = userInput.next();
            String returnedMovieList = controller.searchMovieOnList(searchWord, searchIndex);
            if(returnedMovieList.equals("false")) {
                editPossible = false;
                return "No matches found for "+searchWord;
            } else {
                return returnedMovieList;
            }
        }

    }

    private void editMovie() {
        print(searchForMovie());
        if(editPossible) {
            try {
                print("From the list above, please select the id of the movie you wish to edit.");
                int movieIdToEdit = userInput.nextInt();
                int attrToEdit = 0;
                String attrContent = "";
                while(attrToEdit != 9) {
                    print("""
                                        Press 1 - Change the title
                                        Press 2 - Change the name of the director
                                        Press 3 - Change the genre
                                        Press 4 - Change the year of the movie
                                        Press 5 - Change the length in minutes
                                        Press 6 - Change if the movie is in color
                                        Press 9 - Exit editing
                                        """);
                    attrToEdit = userInput.nextInt();
                    switch (attrToEdit) {
                        case 1 -> {
                            print("What should the new title be?");
                            attrContent = userInput.next();
                        }
                        case 2 -> {
                            print("What should the new director be?");
                            attrContent = userInput.next();
                        }
                        case 3 -> {
                            print("What should the new genre be?");
                            attrContent = userInput.next();
                        }
                        case 4 -> {
                            print("What should the new year be?");
                            attrContent = userInput.next();
                        }
                        case 5 -> {
                            print("What should the new length be?");
                            attrContent = userInput.next();
                        }
                        case 6 -> {
                            print("Is the movie in color type yes. Otherwise, type no");
                            attrContent = userInput.next();
                        }
                        case 9 -> {
                            print("Exiting editing");
                        }
                        default -> {
                            print("Incorrect setting input. Please choose a number between 1 - 6.");
                        }
                    }
                    if(attrToEdit >= 1 && attrToEdit <= 6) {
                        print(controller.editMovieFromMovielist(movieIdToEdit, attrContent, attrToEdit));
                    }
                }



            } catch (RuntimeException re) {
                print("You need to input a valid number");

            }

        }
    }

    private String sortMovies() {
        int primary = 0;
        int secondary = 0;

        do {
            try {
                sortMenuInformation("primary");
                primary = userInput.nextInt();
                sortMenuInformation("secondary");

                secondary = userInput.nextInt();
            } catch (InputMismatchException ime) {
                print("You need to input a valid number");
            }
        } while ((primary < 1 || primary > 6) || (secondary < 1 || secondary > 6));

        return controller.SortMovieList(primary, secondary);

    }

    private void print(String s) {
        System.out.println(s);
    }
}

