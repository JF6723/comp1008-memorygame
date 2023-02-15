package com.example.w23comp1008lhw5memorygame;

public class MemoryCard extends Card{
    private boolean matched;

    public MemoryCard(String suit, String faceName) {
        super(suit, faceName);
        this.matched = false;
    }

    /**
     * This method returns true if the card has been matched with another card
     * @return boolean (true or false)
     */
    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    public boolean isSameCard(MemoryCard otherCard)
    {
        if (this.getCardValue() != otherCard.getCardValue())
            return false;

        if (!this.getSuit().equals(otherCard.getSuit()))
            return false;

        return true;
    }
}
