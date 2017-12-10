package tests;

import com.ingenious.engine.Game;

abstract public class Test {
    protected Game game;

    public Test(Game game) {
        this.game = game;
    }

    public void execute() {
        double startTime, endTime, timeDifference;

        startTime = System.nanoTime();
        System.out.println("-------------------------------------------------------");
        System.out.println("Executing Test: " + this.getClass().getSimpleName());
        System.out.println("-------------------------------------------------------");

        executeTest();

        endTime = System.nanoTime();
        timeDifference = (endTime - startTime) / 1000000;
        System.out.println("-------------------------------------------------------");
        System.out.println("Took " + timeDifference + " ms to execute Test " + this.getClass().getSimpleName());
        System.out.println("-------------------------------------------------------");
        System.out.println();
    }

    protected abstract void executeTest();
}
