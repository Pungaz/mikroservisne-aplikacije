package rs.edu.raf.msa.pbp.model;

import lombok.Data;

import java.util.List;

@Data
public class Play {
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
