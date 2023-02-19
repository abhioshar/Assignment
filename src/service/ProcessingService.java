package service;

import input.Input;
import output.Output;
import util.GroupingAlgorithm;

import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

public class ProcessingService {
    public static void generateMatchingSentences(Input input,
                                                 GroupingAlgorithm groupingAlgorithm,
                                                 Output output) throws IOException {
        final List<List<String>> inputSentences = input.getInput();

        LocalTime time1 = LocalTime.now();
        final List<String> outputLines = groupingAlgorithm.groupSentences(inputSentences);
        LocalTime time2 = LocalTime.now();
        System.out.println((time2.getNano() - time1.getNano())/1000.0);

//        System.out.println(outputLines);
        output.write(outputLines);
    }
}
