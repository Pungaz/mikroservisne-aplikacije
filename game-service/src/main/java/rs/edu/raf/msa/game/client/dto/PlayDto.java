package rs.edu.raf.msa.game.client.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlayDto {
    int p;
    String a;
    String c;
    String d;
    String t;
    String atin;
    List<String> players;
    int x;
    int y;
    int hs;
    int id;
    int vs;
    String et;
}
