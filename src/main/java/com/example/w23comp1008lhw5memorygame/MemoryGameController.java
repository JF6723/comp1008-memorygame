package com.example.w23comp1008lhw5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;

public class MemoryGameController implements Initializable {

    @FXML
    private Label correctGuessLabel;

    @FXML
    private FlowPane cardFlowPane;

    @FXML
    private Label guessesLabel;

    private ArrayList<MemoryCard> cardsInGame;

    @FXML
    void playAgain() {
        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        cardsInGame = new ArrayList<>();

        for (int i =1; i<=5 ; i++)
        {
            Card cardDealt = deck.dealTopCard();
            cardsInGame.add(new MemoryCard(cardDealt.getSuit(),cardDealt.getFaceName()));
            cardsInGame.add(new MemoryCard(cardDealt.getSuit(),cardDealt.getFaceName()));
        }
        Collections.shuffle(cardsInGame);
        System.out.println(cardsInGame);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        flipAllCards();
        playAgain();
    }

    /**
     * This will check if a MemoryCard is matched or not.  If it is not matched, it will display the
     * back of the Card
     */
    private void flipAllCards()
    {
        Image backOfCard = new Image(MemoryCard.class.getResourceAsStream("images/back_of_card.png"));

        //loop over all the imageview objects in the flowpane
        //and set their image to be the back of a card
        for (int i=0; i<cardFlowPane.getChildren().size();i++)
        {
            //casting the generic Node object to an ImageView object
            ImageView imageView = (ImageView) cardFlowPane.getChildren().get(i);
            imageView.setImage(backOfCard);
        }
    }
}
