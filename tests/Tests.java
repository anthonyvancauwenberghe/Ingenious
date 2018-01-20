import com.ingenious.engine.Game;
import com.ingenious.provider.GameProvider;
import tests.Test;
import tests.impl.ExpectiMiniMaxAlgorithmTest;
import tests.impl.MCTSAlgorithmTest;

public class Tests {
    public static void main(String[] args) {
        GameProvider.start(false);
        Game game = GameProvider.getInstance().game;
        executeTests(game);
    }

    public static void executeTests(Game game) {

        while(!game.isOver()){
            Test monteCarloAlgoTest = new MCTSAlgorithmTest(game);
            monteCarloAlgoTest.execute();

            Test alphabetaTest = new ExpectiMiniMaxAlgorithmTest(game);
            alphabetaTest.execute();
        }

    }

}
