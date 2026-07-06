package com.examplez.demo.Model;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
public class PlayerHuman extends Player{
    public PlayerHuman(List<Card> deckCards, int turn) {
        super(deckCards, turn);
    }
    boolean turnState;
    public boolean geTurnState(){
    return turnState;
}
public void setTurnState(boolean turnState){
        this.turnState=turnState;

    }
}
