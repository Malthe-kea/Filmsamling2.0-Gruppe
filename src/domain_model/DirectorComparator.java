package domain_model;

import domain_model.Movie;

import java.util.Comparator;

public class DirectorComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getDirector().compareToIgnoreCase(movie2.getDirector());
    }

}