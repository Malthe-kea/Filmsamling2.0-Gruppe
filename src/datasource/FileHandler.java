package datasource;

import domain_model.Movie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {

    private File file;

    public FileHandler() {
        file = new File("resources"+File.separator+"movies.csv");
    }

    public ArrayList<Movie> readFile() {
        ArrayList<Movie> movies = new ArrayList<>();
        Scanner fileReader;
        try {
            fileReader = new Scanner(file,StandardCharsets.ISO_8859_1);
        } catch(IOException ioE) {

            throw new RuntimeException(ioE);
        }

        while(fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] attributes = line.split(";");

            movies.add(new Movie(attributes[0],attributes[1],attributes[2],
                    Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]),
                    Boolean.parseBoolean(attributes[5]),Integer.parseInt(attributes[6])));
        }

        return movies;
    }

    public void writeFile(ArrayList<Movie> movies) {
        PrintStream out;
        try {
            out = new PrintStream(file);

        } catch(FileNotFoundException fnfe) {
            throw new RuntimeException(fnfe);
        }

        out.flush();

        for(Movie m : movies) {
            String line = m.getTitle() + ";" + m.getDirector() + ";" + m.getGenre() + ";" + m.getYear() +
                    ";" + m.getLengthInMinutes() + ";" + m.getInColor() + ";" + m.getMovieID();
            out.println(line);
        }





    }


}

