package example.micronaut;

import java.util.List;

public interface ScoreCalculator {
    public List<PlayerStats> calculate(List<GameResult> results);
}
