package domain_model;

import domain_model.Movie;

import java.util.Comparator;

public class LengthInMinutesComparator implements Comparator<Movie> {
    @Override
    public int compare(Movie movie1, Movie movie2) {
        return Integer.compare(movie1.getLengthInMinutes(), movie2.getLengthInMinutes());
    }
}