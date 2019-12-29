package eu.cz.yarovii.project.textEditor.controller;

import eu.cz.yarovii.project.textEditor.IO.IOResult;
import eu.cz.yarovii.project.textEditor.model.TextFile;
import eu.cz.yarovii.project.textEditor.service.Editor;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class TextFileController {
    @FXML
    private TextArea textArea;

    private Stage mainStage;

    private Editor service;
    private TextFile currentTextFile;

    private Path path;

    public TextFileController(Editor editor, Stage stage) {
        this.service = editor;
        mainStage = stage;
        path = Paths.get("./");   // Check for bag here !!!!
    }

    @FXML
    private void onOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(path.toString()));
        File file = fileChooser.showOpenDialog(mainStage);

        path = file.toPath();  // bag with path here !!!!!!!

        IOResult<TextFile> data = service.load(file.toPath());

        if(data.isOk() && data.hasData()){
            mainStage.setTitle(file.getName());
            currentTextFile = data.getData();
            textArea.clear();
            currentTextFile.getContent().forEach(line -> textArea.appendText(line + "\n"));
        }else
            System.err.println("Data == null  ayyyyy");
    }

    @FXML
    private void onSave(){
        if(currentTextFile.getLocation() != null) {   // bag with save as !!!
            this.currentTextFile = new TextFile(currentTextFile.getLocation(), Arrays.asList(textArea.getText().split("\n")));
            service.save(currentTextFile);
        }else
            onSaveAs();
    }

    @FXML
    private void onSaveAs(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));   /// add path here !!!!!!
        File file = fileChooser.showSaveDialog(mainStage);

        //if smn push cancel after choosing save as
        if(file != null) {
            this.currentTextFile = new TextFile(file.toPath(), Arrays.asList(textArea.getText().split("\n")));
            service.save(currentTextFile);
            mainStage.setTitle(file.getName());
        }
    }

    @FXML
    private void onClose(){
        service.close();
    }

}
