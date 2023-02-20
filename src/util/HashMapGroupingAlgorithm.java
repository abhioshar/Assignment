package util;

import java.util.*;

import static config.Config.OUTPUT_LINE_PREFIX;

public class HashMapGroupingAlgorithm implements GroupingAlgorithm {
    private static void getFormattedOutputLines(List<List<String>> sentences, List<String> outputLines, HashMap<LinkedList<String>, ArrayList<SentenceIndex>> sentenceToIndicesMap) {
        for (Map.Entry<LinkedList<String>, ArrayList<SentenceIndex>> hashedItem : sentenceToIndicesMap.entrySet()) {
            final StringBuilder changingLine = new StringBuilder(OUTPUT_LINE_PREFIX);

            // If no other sentence matches this one, ignore it
            if (hashedItem.getValue().size() <= 1) continue;

            for (final SentenceIndex sentenceIndex : hashedItem.getValue()) {
                StringBuilder outputLine = new StringBuilder();

                final int sIndex = sentenceIndex.sentenceIndex;
                final int wIndex = sentenceIndex.wordOfSentenceIndex;

                for (final String word : sentences.get(sIndex)) {
                    outputLine.append(word).append(" ");
                }

                changingLine.append(sentences.get(sIndex).get(wIndex + 2)).append(", ");
                outputLines.add(String.valueOf(outputLine));
            }

            outputLines.add(String.valueOf(changingLine.substring(0, changingLine.length() - 2)));
        }
    }

    private static void addSentenceToHashmap(HashMap<LinkedList<String>, ArrayList<SentenceIndex>> sentenceToIndicesMap, int i, LinkedList<String> listOfWords, int j) {
        if (!sentenceToIndicesMap.containsKey(listOfWords)) {
            sentenceToIndicesMap.put(new LinkedList<>(listOfWords), new ArrayList<>());
        }
        sentenceToIndicesMap.get(listOfWords).add(new SentenceIndex(i, j));
    }

    private static void filterWords(LinkedList<String> listOfWords) {
        listOfWords.remove(0);
        listOfWords.remove(0);
    }

    private static LinkedList<String> getLowerCasedWords(List<String> line) {
        final LinkedList<String> lowerCasedWords = new LinkedList<>();
        for (String word : line) {
            lowerCasedWords.add(word.toLowerCase());
        }
        return lowerCasedWords;
    }

    @Override
    public List<String> groupSentences(final List<List<String>> sentences) {
        System.out.println("Starting to group sentences...");
        final ArrayList<String> outputLines = new ArrayList<>();

        final HashMap<LinkedList<String>, ArrayList<SentenceIndex>> sentenceToIndicesMap =
                new HashMap<>();

        for (int i = 0; i < sentences.size(); ++i) {
            final List<String> line = sentences.get(i);
            final LinkedList<String> listOfWords = getLowerCasedWords(line);
            filterWords(listOfWords);

            final ListIterator<String> listIterator = listOfWords.listIterator();

            // Try removing each word one at a time and adding the sentence to hashmap
            for (int j = 0; j < listOfWords.size(); ++j) {
                final String currWord = listIterator.next();
                listIterator.remove();
                addSentenceToHashmap(sentenceToIndicesMap, i, listOfWords, j);
                listIterator.add(currWord);
            }
        }

        getFormattedOutputLines(sentences, outputLines, sentenceToIndicesMap);

        System.out.println("Sentences grouping successfully done!");
        return outputLines;
    }
}
