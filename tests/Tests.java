import com.ingenious.engine.Game;
import com.ingenious.provider.GameProvider;
import tests.Test;
import tests.impl.MCTSAlgorithmTest;

public class Tests {
    public static void main(String[] args) {
        GameProvider.start(false);
        Game game = GameProvider.getInstance().game;
        executeTests(game);
    }

    public static void executeTests(Game game) {
        Test monteCarloAlgoTest = new MCTSAlgorithmTest(game);
        monteCarloAlgoTest.execute();
    }

}
