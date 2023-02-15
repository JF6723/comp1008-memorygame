package com.example.w23comp1008lhw5memorygame;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
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
    private MemoryCard firstCard, secondCard;
    private int numOfGuesses, correctGuesses;

    @FXML
    void playAgain() {
        firstCard = null;
        secondCard = null;
        numOfGuesses=0;
        correctGuesses=0;

        DeckOfCards deck = new DeckOfCards();
        deck.shuffle();

        cardsInGame = new ArrayList<>();

        for (int i =1; i<=cardFlowPane.getChildren().size()/2; i++)
        {
            Card cardDealt = deck.dealTopCard();
            cardsInGame.add(new MemoryCard(cardDealt.getSuit(),cardDealt.getFaceName()));
            cardsInGame.add(new MemoryCard(cardDealt.getSuit(),cardDealt.getFaceName()));
        }
        Collections.shuffle(cardsInGame);
        flipAllCards();

        //Give each ImageView a number and attach a click listener
        initializeImageView();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
        for (int i=0; i<cardFlowPane.getChildren().size(); i++)
        {
            //casting the generic Node object to an ImageView object
            ImageView imageView = getImageView(i);
            if (cardsInGame.get(i).isMatched())
                imageView.setImage(cardsInGame.get(i).getImage());
            else
                imageView.setImage(backOfCard);
        }
    }

    /**
     * This method will loop over all the image view objects, give them a number and then
     * connect a click listener to it
     */
    private void initializeImageView()
    {
        for (int i=0; i<cardFlowPane.getChildren().size();i++)
        {
            ImageView imageView = getImageView(i);
            imageView.setUserData(i);

            //register a click listener
            imageView.setOnMouseClicked(event -> {
                flipCard((int) imageView.getUserData());
            });
        }
    }

    /**
     * This method will "flip" the card at the specified index position showing the face
     * of the card
     */
    private void flipCard(int index)
    {
//        displayCardsInGame();
        ImageView imageView = getImageView(index);

        if (firstCard==null && secondCard==null)
            flipAllCards();

        //no cards are selected
        if (firstCard == null)
        {
            firstCard = cardsInGame.get(index);
            imageView.setImage(firstCard.getImage());
        }
        else if (secondCard == null)
        {
            numOfGuesses++;
            secondCard = cardsInGame.get(index);
            imageView.setImage(secondCard.getImage());
            checkForMatch();
        }
        updateLabels();
    }

    /**
     * This method will show the number of guesses and the number of correct guesses in the display
     */
    private void updateLabels()
    {
        correctGuessLabel.setText(Integer.toString(correctGuesses));
        guessesLabel.setText(Integer.toString(numOfGuesses));
    }

    /**
     * This method will check if the 2 cards showing are the same card.  If yes,
     * it sets them to be "matched"
     */
    private void checkForMatch()
    {
        if (firstCard.isSameCard(secondCard))
        {
            firstCard.setMatched(true);
            secondCard.setMatched(true);
            correctGuesses++;
        }
        firstCard=null;
        secondCard=null;
    }

    /**
     * This method will show each card and it's index position
     */
    private void displayCardsInGame()
    {
        for (int i=0; i<cardsInGame.size();i++)
        {
            System.out.printf("Index %2d: %s%n",i,cardsInGame.get(i));
        }
    }

    /**
     * This method will access the flowpane and return the ImageView object at the specified
     * index position
     */
    private ImageView getImageView(int indexPosition)
    {
        return (ImageView) cardFlowPane.getChildren().get(indexPosition);
    }

    @FXML
    void playWarGame(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("war-game.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        //get the Stage object from the ActionEvent
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        stage.setTitle("War Game");
        stage.setScene(scene);
        stage.show();
    }
}
