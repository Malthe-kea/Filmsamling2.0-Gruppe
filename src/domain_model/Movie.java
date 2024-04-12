package domain_model;

import java.util.List;

public class Movie {

    private String title;
    private String director;
    private String genre;
    private int year;
    private int lengthInMinutes;
    private Boolean isInColor;
    private int movieID;

    public Movie(String title, String director, String genre, int year, int lengthInMinutes, boolean isInColor, int movieID) {
        this.title = title;
        this.director = director;
        this.genre = genre;
        this.year = year;
        this.lengthInMinutes = lengthInMinutes;
        this.isInColor = isInColor;
        this.movieID = movieID;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public Boolean getInColor() {
        return isInColor;
    }

    public void setInColor(Boolean inColor) {
        isInColor = inColor;
    }

    public int getMovieID() {
        return movieID;
    }

    @Override
    public String toString() {
        String film = String.join("\n", List.of(
                "Title: " + title,
                "Director: " + director,
                "Year: " + year,
                "Length: " + lengthInMinutes,
                "Genre: " + genre,
                "Movie ID: " + movieID,
                "Is in color: " + (isInColor ? "Ja " : "Nej ")));

        return film + "\n";
    }

}
