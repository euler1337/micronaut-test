package example.micronaut;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerStats {
    private String name;
    private int homeWin;
    private int awayWin;
    private int goalDifference;
}
