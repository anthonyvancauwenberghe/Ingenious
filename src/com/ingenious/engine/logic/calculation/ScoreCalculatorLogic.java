package com.ingenious.engine.logic.calculation;

import com.ingenious.engine.Game;
import com.ingenious.engine.logic.Logic;
import com.ingenious.model.Move;
import com.ingenious.model.Score;
import com.ingenious.model.Tile;

public class ScoreCalculatorLogic<Void> extends Logic {
    private Move move;

    public ScoreCalculatorLogic(Game game, Move move) {
        super(game);
        this.move = move;
    }

    @Override
    public Void execute() {
        int headScoreStreak = this.getScoreStreakHeadPiece();
        int tailScoreStreak = this.getScoreStreakTailPiece();

        addScoreStreakToColor(move.getPiece().getHead(), this.getGame().getCurrentPlayer().score, headScoreStreak);
        addScoreStreakToColor(move.getPiece().getTail(), this.getGame().getCurrentPlayer().score, tailScoreStreak);
        return null;
    }

    public int getScoreStreakHeadPiece() {
        int x = this.move.getHeadNode().getX();
        int y = this.move.getHeadNode().getY();
        int addedScore = 0;
        int l;

        l = 1;
        while (this.getGame().getBoard().getNode(x, y - l) != null
                && !this.getGame().getBoard().getNode(x, y - l).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x, y - l).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x, y + l) != null
                && !this.getGame().getBoard().getNode(x, y + l).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x, y + l).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x + l, y) != null
                && !this.getGame().getBoard().getNode(x + l, y).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x + l, y).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x - l, y) != null
                && !this.getGame().getBoard().getNode(x - l, y).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x - l, y).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x + l, y - l) != null
                && !this.getGame().getBoard().getNode(x + l, y - l).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x + l, y - l).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x - l, y + l) != null
                && !this.getGame().getBoard().getNode(x - l, y + l).isEqual(this.move.getTailNode())
                && this.getGame().getBoard().getNode(x - l, y + l).getTile().isEqual(this.move.getPiece().getHead())) {
            addedScore++;
            l++;
        }
        return addedScore;
    }

    public int getScoreStreakTailPiece() {
        int x = this.move.getTailNode().getX();
        int y = this.move.getTailNode().getY();
        int addedScore = 0;
        int l;

        l = 1;
        while (this.getGame().getBoard().getNode(x, y - l) != null
                && !this.getGame().getBoard().getNode(x, y - l).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x, y - l).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x, y + l) != null
                && !this.getGame().getBoard().getNode(x, y + l).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x, y + l).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x + l, y) != null
                && !this.getGame().getBoard().getNode(x + l, y).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x + l, y).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x - l, y) != null
                && !this.getGame().getBoard().getNode(x - l, y).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x - l, y).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x + l, y - l) != null
                && !this.getGame().getBoard().getNode(x + l, y - l).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x + l, y - l).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        l = 1;
        while (this.getGame().getBoard().getNode(x - l, y + l) != null
                && !this.getGame().getBoard().getNode(x - l, y + l).isEqual(this.move.getHeadNode())
                && this.getGame().getBoard().getNode(x - l, y + l).getTile().isEqual(this.move.getPiece().getTail())) {
            addedScore++;
            l++;
        }

        return addedScore;
    }

    public Score addScoreStreakToColor(Tile tile, Score oldScore, int addedScore) {
        int newScore = 0;

        if (tile.isGreen()) {
            newScore = addedScore + oldScore.greenScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.greenScore = newScore;
        } else if (tile.isBlue()) {
            newScore = addedScore + oldScore.blueScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.blueScore = (newScore);
        } else if (tile.isRed()) {
            newScore = addedScore + oldScore.redScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.redScore = (newScore);
        } else if (tile.isYellow()) {
            newScore = addedScore + oldScore.yellowScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.yellowScore = (newScore);
        } else if (tile.isOrange()) {
            newScore = addedScore + oldScore.orangeScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.orangeScore = newScore;
        } else if (tile.isPurple()) {
            newScore = addedScore + oldScore.purpleScore;
            if (newScore > 18) {
                this.getGame().setBonusPlay(this.getGame().getBonusPlay()+1);
                newScore = 18;
            }
            oldScore.purpleScore = newScore;
        }

        return oldScore;
    }
}
