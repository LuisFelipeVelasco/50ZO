package com.examplez.demo.Model;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;
import java.util.concurrent.ThreadLocalRandom;

public class Game {
    int numberOfPlayers;
    int numberOfCardsInHand=4;
    int currentSumGame=0;
    int maximumSumGame=50;
    List<Player> players;
    Desk deskGame;
    DiscardPile discardPileGame;

    void Game(int numberOfPlayers){this.numberOfPlayers=numberOfPlayers;}
    void startGame(){
        List<Card> setCards= setCards();
        Card initialCard= drawRandomCard(setCards);
        discardPileGame=new DiscardPile(List.of(initialCard));
        currentSumGame=initialCard.getCardValue();
        setPlayers(setCards);
        deskGame= new Desk(setCards);
    }

    List<Card> setCards(){
        List<Card> setCards=new ArrayList<>();
        Path directory = Path.of("src/main/resources/com/examplez/demo/images/Cards");
        try (Stream<Path> files = Files.list(directory)) {
            files.forEach(path -> {
                String fileName= String.valueOf(path.getFileName());
                String id=fileName.substring(0,2);
                //Task:Handle Exception
                int valueCard=Integer.parseInt(fileName.substring(3));
                Card card= new Card(valueCard,id);
                setCards.add(card);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return setCards;
    }
    Card drawRandomCard(List<Card> setCards){
        int randomIndex= ThreadLocalRandom.current().nextInt(1, setCards.size());
        Card randomCard=setCards.get(randomIndex);
        setCards.remove(randomIndex);
        return  randomCard;
    }

    void setPlayers(List <Card> setCards){
        for(int i=0;i<numberOfPlayers;i++){
            List<Card> handPlayer=new ArrayList<>();
            for(int j = 0;j<numberOfCardsInHand;j++){
                handPlayer.add(drawRandomCard(setCards));
            }
            Player player= new Player(handPlayer,i);
            players.add(player);
        }
    }
    Card getFirstCardDiscardPile(){
        return discardPileGame.getDiscardPile().getFirst();
    }
    void restartDesk(){
        deskGame.addCardsToDesk(discardPileGame.getCardsExceptLastOne());
    }

    List<Card> getHandHumanPlayer(){
        for(Player player: players){
            if(player.getId()==0){
                return player.getHandCard();
            }
        }
        return List.of();
    }
    boolean isPlayerHumanCardValid(Card cardPlayed){
        if(maximumSumGame<cardPlayed.getCardValue()+currentSumGame) return false;
        return true;
    }

    void addCardPlayedToDiscardPile(Card cardPlayed){
        discardPileGame.addNewCard(cardPlayed);
    }

    //boolean isMachinePlayerAbleToPlay(){}
    //Card getCardPlayedByMachinePlayer(int idMachinePlayer){}
//    void eliminatePlayer(int turnPlayer){
//        for(int i=0;i<players.size();i++){
//            Player currentPlayer=players.get(i);
//            if(currentPlayer.getTurn()==turnPlayer){
//                deskGame.addCardsToDesk(currentPlayer.getHandCard());
//                players.remove(i);
//            }
//        }
//    }

//    void addDeskCardToPlayerHand(int turnPlayer){
//        for(int i=0;i<players.size();i++){
//            Player currentPlayer=players.get(i);
//            if(currentPlayer.getTurn()==turnPlayer){
//                currentPlayer.addCardToHand(deskGame.getLastCard());
//            }
//        }
//    }
}
