package com.example.snake_ladder_game;

public class Dice {

    public int getRolledDiceValue(){
        return (int) (Math.random()*6+1);
    }
}
