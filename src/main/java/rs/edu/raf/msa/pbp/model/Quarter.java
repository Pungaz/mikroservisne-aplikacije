package rs.edu.raf.msa.pbp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Quarter {
    int q;

    List<Play> plays = new ArrayList<>(100);
}