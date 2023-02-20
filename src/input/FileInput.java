package input;

import config.Config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileInput implements Input {
    /**
     * Get input from File
     *
     * @return List of sentences ordered by words in each sentence
     * @throws IOException If file opening has exception
     */
    @Override
    public List<List<String>> getInput() throws IOException {
        System.out.println("Starting to get file input...");
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

        System.out.println("File input successfully fetched.");
        return sentences;
    }
}
