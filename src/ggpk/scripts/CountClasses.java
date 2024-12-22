package ggpk.scripts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class CountClasses {
    public static void main(String[] args) throws IOException {
        String read = Files.readString(Path.of("classes.txt"));
        Map<String, Integer> map = new HashMap<>();
        for (String s : read.split("\\n")) {

        }
    }
}
