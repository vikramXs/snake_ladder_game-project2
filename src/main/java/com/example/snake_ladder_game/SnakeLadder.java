package com.example.snake_ladder_game;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

public class SnakeLadder extends Application {

    public static final int tilesize=40, height = 10,width = 10;

    public static final int buttonline = height*tilesize +50, infoline = buttonline-30;

    private static Dice dice = new Dice();

    private Player playerOne,playerTwo;

    private boolean gamestarted = false, playerOneTurn = false,playerTwoTurn = false;
    private Pane createContent(){
        Pane root = new Pane();
        root.setPrefSize(width*tilesize, height*tilesize+100);

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = new Tile(tilesize);
                tile.setTranslateX(j*tilesize);
                tile.setTranslateY(i*tilesize);
                root.getChildren().add(tile);
            }
        }

        Image img = new Image("C:\\Users\\vikram\\IdeaProjects\\snake_ladder_game\\src\\main\\snakes-and-ladders-img.jpg");
        ImageView board = new ImageView();
        board.setImage(img);
        board.setFitHeight(height*tilesize);
        board.setFitWidth(width*tilesize);

        //Buttons
        Button playerOneButton = new Button("player One");
        Button playerTwoButton = new Button("Player Two");
        Button startButton = new Button("Start");

        playerOneButton.setTranslateY(buttonline);
        playerOneButton.setTranslateX(20);
        playerOneButton.setDisable(true);
        playerTwoButton.setTranslateY(buttonline);
        playerTwoButton.setTranslateX(300);
        playerTwoButton.setDisable(true);
        startButton.setTranslateY(buttonline);
        startButton.setTranslateX(160);

        //Info Display
        Label playerOneLabel = new Label("");
        Label playerTwoLabel = new Label("");
        Label diceLabel = new Label("Start the Game");

        playerOneLabel.setTranslateY(infoline);
        playerOneLabel.setTranslateX(20);
        playerTwoLabel.setTranslateY(infoline);
        playerTwoLabel.setTranslateX(300);
        diceLabel.setTranslateY(infoline);
        diceLabel.setTranslateX(160);

        playerOne = new Player(tilesize, Color.BLACK, "Amit");
        playerTwo = new Player(tilesize-5,Color.WHITE,"Sumit");

        //player action
        playerOneButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestarted){
                    if(playerOneTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        playerOne.movePlayer(diceValue);
                        //Winning condition

                        if(playerOne.isWinner()){
                            diceLabel.setText("Winner is "+ playerOne.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                            gamestarted = false;
                        }
                        else{
                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(false);
                            playerTwoLabel.setText("Your Turn " + playerTwo.getName());
                        }

                    }
                }

            }
        });

        playerTwoButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(gamestarted){
                    if(playerTwoTurn){
                        int diceValue = dice.getRolledDiceValue();
                        diceLabel.setText("Dice Value : "+diceValue);
                        playerTwo.movePlayer(diceValue);
                        //Winning condition

                        if(playerTwo.isWinner()){
                            diceLabel.setText("Winner is "+ playerTwo.getName());

                            playerOneTurn = false;
                            playerOneButton.setDisable(true);
                            playerOneLabel.setText("");

                            playerTwoTurn = true;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");

                            startButton.setDisable(false);
                            startButton.setText("Restart");
                        }
                        else{
                            playerOneTurn = true;
                            playerOneButton.setDisable(false);
                            playerOneLabel.setText("Your Turn " + playerOne.getName());

                            playerTwoTurn = false;
                            playerTwoButton.setDisable(true);
                            playerTwoLabel.setText("");
                        }

                    }
                }

            }
        });

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                gamestarted = true;
                diceLabel.setText("Game Started");
                startButton.setDisable(true);
                playerOneTurn = true;
                playerOneLabel.setText("Your Turn "+ playerOne.getName());
                playerOneButton.setDisable(false);
                playerOne.startingPosition();

                playerTwoTurn = false;
                playerTwoLabel.setText("");
                playerTwoButton.setDisable(true);
                playerTwo.startingPosition();

            }
        });

        root.getChildren().addAll(board,
                playerOneButton,playerTwoButton,startButton,
                playerOneLabel, playerTwoLabel, diceLabel,
                playerOne.getCoin(),playerTwo.getCoin()
        );

        return root;
    }
    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(createContent());
        stage.setTitle("Snake & Ladder");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}