package service;

import input.Input;
import output.Output;
import util.GroupingAlgorithm;

import java.io.IOException;
import java.util.List;

public class ProcessingService {
    /**
     * Service which integrates input, output and the matching algorithm
     * @param input Input type, e.g. FileInput
     * @param groupingAlgorithm Grouping Algorithm, e.g. HashMap based algorithm
     * @param output Output type, eg. FileOutput
     */
    public static void generateMatchingSentences(final Input input,
                                                 final GroupingAlgorithm groupingAlgorithm,
                                                 final Output output) {
        try {
            final List<List<String>> inputSentences = input.getInput();
            final List<String> outputLines = groupingAlgorithm.groupSentences(inputSentences);
            output.write(outputLines);
        } catch (IOException exception) {
            System.err.println("Exception occurred during file handling: " + exception.getMessage());
            exception.printStackTrace();
        } catch (Exception exception) {
            System.err.println("Exception occurred: " + exception.getMessage());
            exception.printStackTrace();
        }
    }
}
