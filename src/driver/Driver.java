package driver;

import input.FileInput;
import input.Input;
import output.FileOutput;
import output.Output;
import service.ProcessingService;
import util.GroupingAlgorithm;
import util.HashMapGroupingAlgorithm;

public class Driver {
    /**
     * Creates the necessary Classes and starts the matching sentences functionality
     */
    public static void beginProgramExecution() {
        Input input = new FileInput();
        GroupingAlgorithm groupingAlgorithm = new HashMapGroupingAlgorithm();
        Output output = new FileOutput();

        ProcessingService.generateMatchingSentences(input, groupingAlgorithm, output);
    }
}
