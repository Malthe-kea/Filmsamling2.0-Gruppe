package domain_model;

import datasource.FileHandler;

import java.util.*;


public class MovieCollection {

    FileHandler fileHandler = new FileHandler();

    private ArrayList<Movie> movieListArr;

    private HashMap<Integer, Comparator<Movie>> comparatorHashMap;

    public MovieCollection() {
        movieListArr = fileHandler.readFile();
        comparatorHashMap = new HashMap<>();
        comparatorHashMap.put(1, new TitleComparator());
        comparatorHashMap.put(2, new DirectorComparator());
        comparatorHashMap.put(3, new GenreComparator());
        comparatorHashMap.put(4, new YearComparator());
        comparatorHashMap.put(5, new LengthInMinutesComparator());
        comparatorHashMap.put(6, new ColorComparator());

    }

    private void removeMovie(Movie movie) {
        movieListArr.remove(movie);
    }

    public String createAndAddMovieToMovieList(String title, String director, String genre, int year, int lengthInMinutes, boolean isInColor) {
        int movieID = 1;
        if (!movieListArr.isEmpty()) {
            movieID = movieListArr.get(movieListArr.size() - 1).getMovieID() + 1;
        }
        movieListArr.add(new Movie(title, director, genre, year, lengthInMinutes, isInColor, movieID));
        fileHandler.writeFile(movieListArr);

        return title + " was successfully added to the movie collection!";
    }


    public String editMovieFromMovielist(int idToEdit, String editString, int editIndex) {
        Movie movietoEdit = null;

        for(Movie m : movieListArr) {
            if(m.getMovieID() == idToEdit) {
                movietoEdit = m;
                break;
            }
        }

        switch (editIndex) {
            case 1 -> {
                movietoEdit.setTitle(editString);
            }
            case 2 -> {
                movietoEdit.setDirector(editString);
            }
            case 3 -> {
                movietoEdit.setGenre(editString);
            }
            case 4 -> {
                try {
                    movietoEdit.setYear(Integer.parseInt(editString));
                } catch (RuntimeException re) {
                    return "Not a valid input. Please input a number.";
                }

            }
            case 5 -> {
                try {
                    movietoEdit.setLengthInMinutes(Integer.parseInt(editString));
                } catch (RuntimeException re) {
                    return "Not a valid input. Please input a number.";
                }
            }
            case 6 -> {
                boolean isInColor = !editString.equals("no");
                movietoEdit.setInColor(isInColor);
            }
            default -> {
                return "Error - Edit not applicable with index ID.";
            }
        }
        fileHandler.writeFile(movieListArr);
        return "The movie was successfully edited!";
    }

    private String noMoviesOnList() {
        return "No movies on list";
    }

    public String getListOfMovies() {
        if (movieListArr.isEmpty()) {
            return noMoviesOnList();
        } else {
            StringBuilder listString = new StringBuilder();
            for (Movie m : movieListArr) {
                listString.append(m.toString() + "\n");
            }


            return listString.toString();
        }
    }

    public String searchMovieOnList(String stringToSearchFor, int i) {
        ArrayList<Movie> searchResults = searchList(stringToSearchFor, i);

        if(searchResults.isEmpty()) {
            return "false";
        }
        StringBuilder searchedMovieList = new StringBuilder();
        for (Movie m : searchResults) {
            searchedMovieList.append(m.toString());
        }
        return searchedMovieList.toString();
    }

    private ArrayList<Movie> searchList(String searchAtt, int i) {
        ArrayList<Movie> tempMovieList = new ArrayList<>();
        for (Movie movie : movieListArr) {
            switch(i) {
                case 1 -> {
                    if (movie.getTitle().toLowerCase().contains(searchAtt.toLowerCase())) {
                        tempMovieList.add(movie);
                    }
                }
                case 2 -> {
                    if (movie.getDirector().toLowerCase().contains(searchAtt.toLowerCase())) {
                        tempMovieList.add(movie);
                    }
                }
                case 3 -> {
                    if (movie.getGenre().toLowerCase().contains(searchAtt.toLowerCase())) {
                        tempMovieList.add(movie);
                    }
                }
                case 4 -> {
                    if (movie.getYear() == Integer.parseInt(searchAtt)) {
                        tempMovieList.add(movie);
                    }
                }
                case 5 -> {
                    if (movie.getLengthInMinutes() == Integer.parseInt(searchAtt)) {
                        tempMovieList.add(movie);
                    }
                }
                case 6 -> {
                    boolean isInColor = !searchAtt.equalsIgnoreCase("no");
                    if (movie.getInColor() == isInColor) {
                        tempMovieList.add(movie);
                    }
                }
                default -> {
                    System.out.println("Error searching. Integer for setting is not between 1 and 6.");
                }
            }
        }

        return tempMovieList;
    }

    public String removeMovieFromList(int movieIdToRemove) {
        Movie movieToRemove = null;

        for(Movie m : movieListArr) {
            if(m.getMovieID() == movieIdToRemove) {
                movieToRemove = m;
                break;
            }
        }

        if (movieToRemove != null) {
            removeMovie(movieToRemove);
            fileHandler.writeFile(movieListArr);
            return "The movie with id" + movieIdToRemove + " has been removed from collection";
        } else {
            return "there is no movie on the list with the id " + movieIdToRemove + ".";
        }
    }


    public String sortMovieList(int primary, int secondary) {
        movieListArr.sort(comparatorHashMap.get(primary).thenComparing(comparatorHashMap.get(secondary)));
        return getListOfMovies();
    }

    public ArrayList<Movie> getMovieListArr() {
        return movieListArr;
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }
}