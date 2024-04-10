package domain_model;

import domain_model.Movie;

import java.util.Comparator;

public class ColorComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2){
        return movie1.getInColor().compareTo(movie2.getInColor());
    }

}