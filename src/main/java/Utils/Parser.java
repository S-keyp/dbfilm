package Utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Film;

public class Parser {
    public static void main(String[] args) throws Exception{
        
        ObjectMapper mapper = new ObjectMapper();

        Film film = mapper.readValue(new File("film.json"), Film.class);
        System.out.println(film);

    }
}
