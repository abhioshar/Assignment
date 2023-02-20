package output;

import config.Config;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileOutput implements Output {
    @Override
    public void write(List<String> lines) throws IOException {
        System.out.println("Starting to do file output...");
        final FileWriter writer = new FileWriter(Config.OUTPUT_FILE_PATH);
        final BufferedWriter buffer = new BufferedWriter(writer);
        for (final String line : lines) {
            buffer.write(line + "\n");
        }
        buffer.close();
        System.out.println("Successfully written to file output.");
    }
}
