package output;

import java.io.IOException;
import java.util.List;

public interface Output {
    /**
     * General method to write output to any destination
     *
     * @param lines List of lines to be written
     * @throws IOException If file handling has exception
     */
    void write(List<String> lines) throws IOException;
}
