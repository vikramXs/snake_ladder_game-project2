module com.example.snake_ladder_game {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.snake_ladder_game to javafx.fxml;
    exports com.example.snake_ladder_game;
}