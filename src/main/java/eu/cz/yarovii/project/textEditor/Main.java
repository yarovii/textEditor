package eu.cz.yarovii.project.textEditor;

import eu.cz.yarovii.project.textEditor.controller.TextFileController;
import eu.cz.yarovii.project.textEditor.service.Editor;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("scene.fxml"));

        loader.setControllerFactory(t -> new TextFileController(new Editor()));
        Parent root = loader.load();
        stage.setScene(new Scene(root));
        stage.setTitle("Editor");

        stage.show();
    }
}
