package input;

import config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FileInput implements Input {
    @Override
    public List<List<String>> getInput() throws IOException {
        final List<List<String>> sentences = new ArrayList<>();

        final String inputFile = Config.INPUT_FILE_PATH;

        final BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        String currentLine = reader.readLine();
        while (currentLine != null) {
            List<String> currWords = List.of(currentLine.split(" "));
            sentences.add(currWords);
            currentLine = reader.readLine();
        }
        reader.close();
        return sentences;
    }
}
