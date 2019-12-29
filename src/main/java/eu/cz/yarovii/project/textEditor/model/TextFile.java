package eu.cz.yarovii.project.textEditor.model;

import java.nio.file.Path;
import java.util.List;

public class TextFile {
    private final Path location;
    private List<String> content;

    public TextFile(Path location, List<String> content) {
        this.location = location;
        this.content = content;
    }

    public TextFile(Path location) {
        this.location = location;
    }

    public Path getLocation() {
        return location;
    }

    public List<String> getContent() {
        return content;
    }

}
