package eu.cz.yarovii.project.textEditor.service;

import eu.cz.yarovii.project.textEditor.IO.IOResult;
import eu.cz.yarovii.project.textEditor.model.TextFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class Editor {

    public void close(){
        System.exit(0);
    }

    public void save(TextFile textFile){
        try {
            Files.write(textFile.getLocation(), textFile.getContent(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public IOResult<TextFile> load(Path file){
        try {
            return new IOResult<TextFile>(true, new TextFile(file, Files.readAllLines(file)));
        } catch (IOException e) {
            e.printStackTrace();
            return new IOResult<>(false, null);
        }

    }
}
