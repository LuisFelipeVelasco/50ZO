package com.examplez.demo.Model;

/**
 * Immutable-in-practice representation of one card used by the game model.
 *
 * <p>Each card stores the numeric value used by the rules and an identifier
 * that also forms part of the corresponding image filename.</p>
 *
 * @version 1.0
 */
public class Card {
    // -----------------------------------------------------------------------
    // Card state
    // -----------------------------------------------------------------------

    /** Numeric value contributed by the card; an ace is represented by {@code -1}. */
    int cardValue;
    /** Identifier used to distinguish the card and locate its image resource. */
    String idCard;
   /**
    * Creates a card with its rule value and resource identifier.
    *
    * @param cardValue numeric value represented by the card
    * @param idCard unique identifier associated with the card image
    *
    * @throws IllegalArgumentException if the identifier is null
    *         or the card value is greater than 10
    */
   public Card(int cardValue,String idCard){

       if (idCard == null) {
           throw new IllegalArgumentException(
                   "The card must have an id"
           );
       }
       if (cardValue > 10) {
           throw new IllegalArgumentException(
                   "The maximum value for a card is 10."
           );
       }

        this.cardValue=cardValue;
        this.idCard=idCard;
    }
  /**
   * Returns the value used when evaluating a play.
   *
   * @return card value, or {@code -1} for an ace
   */
  public  int getCardValue(){
        return cardValue;
    }
   /**
    * Returns the identifier assigned to this card.
    *
    * @return card identifier used by the model and image resources
    */
   public String getIdCard(){
        return idCard;
    }
}
