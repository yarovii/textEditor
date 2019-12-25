package eu.cz.yarovii.project.textEditor.controller;

import eu.cz.yarovii.project.textEditor.IO.IOResult;
import eu.cz.yarovii.project.textEditor.model.TextFile;
import eu.cz.yarovii.project.textEditor.service.Editor;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.Arrays;

public class TextFileController {
    @FXML
    private TextArea textArea;

    private Editor service;
    private TextFile currentTextFile;

    public TextFileController(Editor editor) {
        this.service = editor;
    }

    @FXML
    private void onOpen(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showOpenDialog(null);

        IOResult<TextFile> data = service.load(file.toPath());

        if(data.isOk() && data.hasData()){
            currentTextFile = data.getData();
            textArea.clear();
            currentTextFile.getContent().forEach(line -> textArea.appendText(line + "\n"));
        }else
            System.err.println("Data == null  ayyyyy");
    }

    @FXML
    private void onSave(){
        if(currentTextFile != null) {
            this.currentTextFile = new TextFile(currentTextFile.getLocation(), Arrays.asList(textArea.getText().split("\n")));
            service.save(currentTextFile);
        }else
            onSaveAs();
    }

    @FXML
    private void onSaveAs(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File("./"));
        File file = fileChooser.showSaveDialog(null);

        //if smn push cancel after choosing save as
        if(file != null) {
            this.currentTextFile = new TextFile(file.toPath(), Arrays.asList(textArea.getText().split("\n")));
            service.save(currentTextFile);
        }
    }

    @FXML
    private void onClose(){
        service.close();
    }

}
