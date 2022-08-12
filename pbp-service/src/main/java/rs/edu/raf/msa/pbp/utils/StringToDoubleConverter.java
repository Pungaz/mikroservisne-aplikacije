package rs.edu.raf.msa.pbp.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class StringToDoubleConverter {

    public static double fromStringToDouble(String string) {
        try {
            if (!string.equals("12:00")) {
                String[] stringArray = string.split(":");
                return Double.parseDouble(stringArray[0]) + Double.parseDouble(stringArray[1]) / 100;
            } else {
                return 11.6;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error with entered time period", e);
        }
    }

}
