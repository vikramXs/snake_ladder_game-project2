package com.example.snake_ladder_game;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Tile extends Rectangle {
    public Tile(int tilesize){
        setWidth(tilesize);
        setHeight(tilesize);
        setFill(Color.AZURE);
        setStroke(Color.BLACK);

    }
}
