package example.micronaut;

import java.util.List;

public interface ScoreCalculator {
    List<PlayerStats> calculate(List<GameResult> results);
}
