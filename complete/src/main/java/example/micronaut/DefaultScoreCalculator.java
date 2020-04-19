package example.micronaut;


import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Singleton
public class DefaultScoreCalculator implements ScoreCalculator {

    public List<PlayerStats> calculate2(List<GameResult> results) {
        PlayerStats stats = new PlayerStats("micke",1,1,1);
        return List.of(stats, stats);
    }

    public List<PlayerStats> calculate(List<GameResult> results) {

        HashMap<String, PlayerStats> uniquePlayers = new HashMap<String, PlayerStats>();

        for (GameResult game : results) {
            TeamScore home = game.getHome();
            TeamScore away = game.getAway();

            if(home.getScore() > away.getScore()) {
                for (String player : home.getPlayers()) {
                    updateWin(player,
                            home.getScore() - away.getScore(),
                    true,
                            uniquePlayers);
                }

                for (String player : away.getPlayers()) {
                    updateLoss(player,
                            home.getScore() - away.getScore(),
                            uniquePlayers);
                }
            }
            else  if(home.getScore() < away.getScore()) {
                for (String player : away.getPlayers()) {
                    updateWin(player,
                            away.getScore() - home.getScore(),
                            false,
                            uniquePlayers);
                }

                for (String player : home.getPlayers()) {
                    updateLoss(player,
                            away.getScore() - home.getScore(),
                            uniquePlayers);
                }
            }
        }

        List<PlayerStats> list = new ArrayList<PlayerStats>(uniquePlayers.values());
        return list;
    }

    private void updateWin(String player, int goalDifference, boolean isHome, HashMap<String, PlayerStats> map) {
        PlayerStats val = map.get(player);

        if (val == null) {
            val = new PlayerStats(player, 0, 0, 0);
            map.put(player, val);
        }
        val.setGoalDifference(val.getGoalDifference()+goalDifference);

        if(isHome) {
            val.setHomeWin(val.getHomeWin()+1);
        }
        else {
            val.setAwayWin(val.getAwayWin()+1);
        }
    }

    private void updateLoss(String player, int goalDifference, HashMap<String, PlayerStats> map) {
        PlayerStats val = map.get(player);

        if (val == null) {
            val = new PlayerStats(player, 0, 0, 0);
            map.put(player, val);
        }
        val.setGoalDifference(val.getGoalDifference()-goalDifference);
    }

}
