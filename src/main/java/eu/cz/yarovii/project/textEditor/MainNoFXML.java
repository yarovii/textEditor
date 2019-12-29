package eu.cz.yarovii.project.textEditor;

import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.EventListener;

public class MainNoFXML extends Application implements EventHandler {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Editor");

        Window window = new Window();

        BorderPane border = window.create();

        stage.setScene(new Scene(border));
        stage.setWidth(800);
        stage.setHeight(600);
        stage.show();
    }

    @Override
    public void handle(Event event) {
        System.out.println("Here we go\n");
    }

    class Window{
        private BorderPane create(){
            BorderPane border = new BorderPane();
            HBox hBox = new HBox();
            hBox.setStyle("-fx-padding: 5; -fx-spacing: 10;");

            //effects
            DropShadow shadow = new DropShadow();

            // Set top
            MenuBar menuBar = new MenuBar();

            // --- Menu File
            Menu menuFile = new Menu("File");
            MenuItem open = new MenuItem("Open");
            MenuItem save = new MenuItem("Save");
            MenuItem saveAs = new MenuItem("Save as");
            MenuItem close = new MenuItem("Close");

            menuFile.getItems().addAll(open, save, saveAs, close);

            // --- Menu Edit
            Menu menuEdit = new Menu("Edit");

            // --- Menu View
            Menu menuView = new Menu("View");

            menuBar.getMenus().addAll(menuFile, menuEdit, menuView);

            border.setTop(menuBar);

            //set center
            TextArea textArea = new TextArea();
            textArea.setStyle("-fx-faint-focus-color: transparent; -fx-focus-color:rgba(0,0,0,0.2);");
            border.setCenter(textArea);

            //set bottom
            Button buttonSave = new Button("Save");
            Button button2 = new Button("B2");
            Button button3 = new Button("b3");
            button2.setOnAction(e -> System.out.println("sosi"));

            hBox.getChildren().addAll(buttonSave, button2, button3);

            hBox.addEventHandler(MouseEvent.MOUSE_ENTERED, (MouseEvent e) -> {
                buttonSave.setEffect(shadow);
            });
            hBox.addEventHandler(MouseEvent.MOUSE_EXITED, (MouseEvent e) -> {
                buttonSave.setEffect(null);
            });

            border.setBottom(hBox);

            return border;
        }
    }
}

