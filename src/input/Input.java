package input;

import java.io.IOException;
import java.util.List;

public interface Input {
    /**
     * General method to get input from any source
     *
     * @return List of sentences ordered by words in each sentence
     * @throws IOException If file opening has exception
     */
    List<List<String>> getInput() throws IOException;
}
