package example.micronaut;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.reactivex.Single;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Controller("/result")
public class HelloController {
    private static final Logger LOG = LoggerFactory.getLogger(HelloController.class);
    private ScoreCalculator scoreCalculator;

    public HelloController(ScoreCalculator scoreCalculator) {
        this.scoreCalculator = scoreCalculator;
    }

    @Post
    public Single<List<PlayerStats>> result(@Body List<GameResult> result) {
        return Single.just(scoreCalculator.calculate(result));
    }
}
