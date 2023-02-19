package driver;

import input.FileInput;
import input.Input;
import output.FileOutput;
import output.Output;
import service.ProcessingService;
import util.GroupingAlgorithm;
import util.HashMapGroupingAlgorithm;

import java.io.IOException;

public class Driver {
    public static void beginProgramExecution() throws IOException {
        Input input = new FileInput();
        GroupingAlgorithm groupingAlgorithm = new HashMapGroupingAlgorithm();
        Output output = new FileOutput();

        ProcessingService.generateMatchingSentences(input, groupingAlgorithm, output);
    }
}
