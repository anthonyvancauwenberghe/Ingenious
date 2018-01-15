package com.ingenious.algorithm.support;

import com.ingenious.engine.Game;
import com.ingenious.model.Piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PieceTracker
{
    Map<Piece, Integer> pieceLookupTable = new HashMap<>();

    public PieceTracker()
    {
        resetTableValues();
    }

    private void resetTableValues()
    {
        pieceLookupTable.put(Piece.RED_RED, 5);
        pieceLookupTable.put(Piece.BLUE_BLUE, 5);
        pieceLookupTable.put(Piece.GREEN_GREEN, 5);
        pieceLookupTable.put(Piece.ORANGE_ORANGE, 5);
        pieceLookupTable.put(Piece.YELLOW_YELLOW, 5);
        pieceLookupTable.put(Piece.PURPLE_PURPLE, 5);
        pieceLookupTable.put(Piece.RED_BLUE,6);
        pieceLookupTable.put(Piece.RED_GREEN,6);
        pieceLookupTable.put(Piece.RED_ORANGE,6);
        pieceLookupTable.put(Piece.RED_YELLOW,6);
        pieceLookupTable.put(Piece.RED_PURPLE,6);
        pieceLookupTable.put(Piece.BLUE_GREEN,6);
        pieceLookupTable.put(Piece.BLUE_ORANGE,6);
        pieceLookupTable.put(Piece.BLUE_YELLOW,6);
        pieceLookupTable.put(Piece.BLUE_PURPLE,6);
        pieceLookupTable.put(Piece.GREEN_ORANGE,6);
        pieceLookupTable.put(Piece.GREEN_YELLOW,6);
        pieceLookupTable.put(Piece.GREEN_PURPLE,6);
        pieceLookupTable.put(Piece.ORANGE_YELLOW,6);
        pieceLookupTable.put(Piece.ORANGE_PURPLE,6);
        pieceLookupTable.put(Piece.YELLOW_PURPLE,6);
    }

    public void updateTable(Game game, boolean checkCurrentPlayerRack)
    {
        resetTableValues();
        ArrayList<Piece> rackPieces = (checkCurrentPlayerRack) ?
                game.getCurrentPlayer().getRack().getPieces() : game.getOtherPlayer().getRack().getPieces();

        ArrayList<Piece> boardPieces = game.getBoard().getPlacedPieces();

        for(Piece p : rackPieces)
        {
            pieceLookupTable.put(p, pieceLookupTable.get(p) - 1);
        }
        for(Piece p : boardPieces)
        {
            pieceLookupTable.put(p, pieceLookupTable.get(p) - 1);
        }
    }

    public int getNumTotalPieces()
    {
        int total = 0;
        for(Piece key : pieceLookupTable.keySet())
        {
            total += pieceLookupTable.get(key);
        }
        return total;
    }

    public int getNumAvailablePieceTypes()
    {
        int count = 0;
        for(Piece key : pieceLookupTable.keySet())
        {
            if(pieceLookupTable.get(key) > 0)
                count++;
        }
        return count;
    }

    public float getChanceForPiece(Piece piece)
    {
        int numOfPiece = pieceLookupTable.get(piece);
        int totalPieces = getNumTotalPieces();

        return numOfPiece / totalPieces;
    }

    public ArrayList<Piece> getEstimatedHand()
    {
        ArrayList<Piece> hand = new ArrayList<>();
        ArrayList<Piece> p1 = new ArrayList<>();
        ArrayList<Piece> p2 = new ArrayList<>();
        ArrayList<Piece> p3 = new ArrayList<>();
        ArrayList<Piece> p4 = new ArrayList<>();
        ArrayList<Piece> p5 = new ArrayList<>();
        ArrayList<Piece> p6 = new ArrayList<>();
        for(Piece p : pieceLookupTable.keySet())
        {
            switch (pieceLookupTable.get(p))
            {
                case 6:
                    p6.add(p);
                    break;
                case 5:
                    p5.add(p);
                    break;
                case 4:
                    p4.add(p);
                    break;
                case 3:
                    p3.add(p);
                    break;
                case 2:
                    p2.add(p);
                    break;
                case 1:
                    p1.add(p);
                    break;
            }
        }

        if(p6.size() == 6) return p6;//if there are exactly 6 piece types available
        else if(p6.size() > 6) hand = getRandomSetFromTooLargeList(6, hand, p6);//if there's more
        else if(p6.size() < 6) //if there's less
        {
            if(p6.size() > 0) hand = putPiecesInList(hand, p6);

            if(p5.size() == (6 - hand.size())) hand = putPiecesInList(hand, p5);
            else if(p5.size() > (6 - hand.size())) hand = getRandomSetFromTooLargeList((6 - hand.size()), hand, p5);
            else if(p5.size() < (6 - hand.size()))
            {
                if(p5.size() > 0) hand = putPiecesInList(hand, p5);

                if(p4.size() == (6 - hand.size())) hand = putPiecesInList(hand, p4);
                else if(p4.size() > (6 - hand.size())) hand = getRandomSetFromTooLargeList((6 - hand.size()), hand, p4);
                else if(p4.size() < (6 - hand.size()))
                {
                    if(p4.size() > 0) hand = putPiecesInList(hand, p4);

                    if(p3.size() == (6 - hand.size())) hand = putPiecesInList(hand, p3);
                    else if(p3.size() > (6 - hand.size())) hand = getRandomSetFromTooLargeList((6 - hand.size()), hand, p3);
                    else if(p3.size() < (6 - hand.size()))
                    {
                        if(p3.size() > 0) hand = putPiecesInList(hand, p3);

                        if(p2.size() == (6 - hand.size())) hand = putPiecesInList(hand, p2);
                        else if(p2.size() > (6 - hand.size())) hand = getRandomSetFromTooLargeList((6 - hand.size()), hand, p2);
                        else if(p2.size() < (6 - hand.size()))
                        {
                            if(p2.size() > 0) hand = putPiecesInList(hand, p2);

                            if(p1.size() == (6 - hand.size())) hand = putPiecesInList(hand, p1);
                            else if(p1.size() > (6 - hand.size())) hand = getRandomSetFromTooLargeList((6 - hand.size()), hand, p1);
                            else if(p1.size() < (6 - hand.size()))
                            {
                                if(p1.size() > 0) hand = putPiecesInList(hand, p1);

                                if(hand.size() < 6 && getNumTotalPieces() >= (6 - hand.size()))
                                    hand = getRandomDuplicates(hand);
                                else
                                    System.out.println("Error: Not enough pieces left for a full hand.");
                            }
                        }
                    }
                }
            }
        }

        return hand;
    }

    private ArrayList<Piece> getRandomDuplicates(ArrayList<Piece> list)
    {
        Map<Piece, Integer> h = new HashMap<Piece, Integer>();

        for(Piece p : list)
        {
            h.put(p, 1);
        }

        int c = (6 - list.size());
        int i = 0;
        int duplicatesAdded = 0;

        while (i < list.size())
        {
            if (duplicatesAdded < c)
            {
                Piece p = list.get(i);
                if (pieceLookupTable.get(p) > h.get(p))
                    h.put(p, h.get(p) + 1);

                duplicatesAdded++;
            }
            else break;

            if(i + 1 == list.size()) i = 0;
            else i++;
        }

        for (Piece key : h.keySet())
        {
            if (h.get(key) > 1)
            {
                for (int x = 0; x < (h.get(key) - 1); x++)
                {
                    list.add(key);
                }
            }
        }
        return list;
    }

    private ArrayList<Piece> getRandomSetFromTooLargeList(int amount, ArrayList<Piece> target, ArrayList<Piece> list)
    {
        Random rand = new Random();
        for(int x = 0; x < amount; x++)
        {
            int n = rand.nextInt(list.size());
            target.add(list.get(n));
            list.remove(n);
        }
        return target;
    }

    private ArrayList<Piece> putPiecesInList(ArrayList<Piece> list, ArrayList<Piece> source)
    {
        for (int x = 0; x < source.size(); x++)
        {
            list.add(source.get(x));
        }
        return list;
    }
}
