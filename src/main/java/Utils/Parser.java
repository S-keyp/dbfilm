package Utils;

import java.io.File;

import com.fasterxml.jackson.databind.ObjectMapper;

import model.Film;
import model.Role;

public class Parser {
    public static Film[] parse() {
    
        ObjectMapper mapper = new ObjectMapper();
        Film[] films = null;
    
        try{
            films = mapper.readValue(new File("film.json"), Film[].class);
            for(Film film : films) {
                for(Role role : film.getRoles()){
                    role.setFilm(film);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    
        return films;
    }
}
