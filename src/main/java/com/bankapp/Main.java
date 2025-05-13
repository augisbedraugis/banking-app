package com.bankapp;

import javafx.application.Application;
import javafx.stage.Stage;
import com.bankapp.controller.NavigationController;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        NavigationController.init(primaryStage);  // holds a static Stage ref
        NavigationController.switchToLogin();     // shows the login scene
    }

    public static void main(String[] args) {
        launch(args);
    }
}
