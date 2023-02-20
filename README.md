# Assignment

## Overview of the solution:

*Assumptions:*
1. I've assumed there are no special characters in the input
2. Sentence matching has to preserve the relative ordering of the words as given in input

I've used interfaces for Input/Output/Grouping algorithm and
strategy for choosing grouping algorithm at runtime. These follow single responsibility,
Open/Closed principles.

Class Flow is:

#### Class Diagram ![Class Diagram](classDiagrams/ClassDiagramImage.png?raw=true)
####

#### Main method -> Driver -> ProcessingService -> Input -> ProcessingService -> GroupingAlgorithm -> ProcessingService -> Output ####

#### Code is inside /src directory ####
#### Files are inside /files directory ####
#### Class diagram is inside /classDiagram directory ####


#### Algorithm ####
The solution uses hashmap based approach for solving.
Hashmap has sentence as the key(omitting a single word at a time) which maps to a vector of pair of
two
values.

First value of the pair: the index of the mapped line
in the file.

Second: the index of word omitted from the line.


Eg: a line from file

*01-01-2012 19:45:00 Naomi is getting into the car*

We generate a linked list from a given sentence omitting a single word. Why linked list? Because
it has constant insertion/deletion time for an entry which will help us later when we remove a
word at a time from a line to generate key for a hashmap.

Eg. for example for above line from file would generate six hashmap entries

Map:
1. is getting into the car (omitting 1st word)
2. Naomi getting into the car (omitting 2nd word)
3. ... so on...

Each key in the hashmap, maps to list of {lineIndex, wordIndexInLine} pair.

Eg for above hashmap:

1. [is getting into the car] -> {{0 (line index in original file, 0 (index of the word omitted
   in line, here it's 0}}
2. [Naomi getting into the car] -> {{0 (line index is remains 0, 1 (the word omitted had index 1}}


If another line anywhere in the file matches any entry in the above map, we add the {lineIndex,
wordIndex) pair to the map key.

Eg: in above example if some line arrives (assume line index 2) which is:

*"John is getting into the car"*

Here omitting the first word we get -> "is getting into the car". This matches the first entry
from the map above. Hence, we'll add this line index to the map , and it becomes:

[is getting into the car] -> {{0, 0}, {2,0}}

In this way after we iterate over all the lines in the file, we'll have all the lines which have
one changed word will map to the same key in the map. And later we can generate the output
required from the map value viz. line index and the word changed.

## How to run?

1. It's a Java project created on Intellij Idea. It can be directly imported. No external 
   dependencies are required.

2. I've added sample input and output files, so they can be used directly. These are in */files*
   directory in root.

## Other Questions
### 1. What can you say about the complexity of your code? ###


    ### Time Complexity ###
    
    Let,
    
    N: Total lines in the file
    
    W: Maximum no. of words in a line
    
    HashMap: Each entry in map takes O(1) amortized time to search/update etc. where N are 
    number of entries in the map. Here I'm using list as the key of the hashmap, hence, complexity 
    will become O(1) * (time taken by hashmap to generate hash for the the key, which here is O
    (no. of words in a line(W))) as each key in hashmap has list of O(W) words.
    
    Total complexity: N * W * W

    As, when we are processing each line, we are omitting one word at a time. Hence, we iterate over 
    each line W times (=> N * W). And, each operation over hashmap is O(W). Hence, O(N * W * W) 
    total complexity.

    ### Space Complexity ###

    Extra storage used is Hashmap, which has complexity: O(N * W * (W-1)) => O(N * W * W). 
    As for each line, we are storing W-1 words(omitting one word every time) in each of our W 
    iterations.

### 2. How will your algorithm scale? ###


    2.1 Time wise, algorithm with scale linearly with no. of input lines and quadratically with  
    max no. of words used in a line. 
    I've successfully tested similar ~30,000 lines file input in ~0.3sec.

    2.2 Space wise, algorithm will scale same as above point as both complexities are the same.

    2.3 Current algorithm works on HashMap based approach. If that is not required, we can just 
    create a new strategy for grouping algorithm and use it in our program. Existing code remains 
    closed for modification and open for extension in this way.

    2.4 I've also used lowercasing for all the matching. Hence, string matching will work with 
    both lower/upper cases.

    2.5 Input/Output wise, I've used interfaces which can be implemented to support various other
    input/output sources, following the Single Responsibility and Open/Closed Solid principle.

    2.6 Support for any grouping algorithm can be made, as I've used strategy pattern to select
    any algorithm. Interface "util/GroupingAlgorithm" can be implemented to implement any other 
    algorithm for grouping the sentences, following the Single Responsibility and Open/Closed Solid 
    principle.


### 3. If you had two weeks to do this task, what would you have done differently? What would be better? ###


    3.1 Start with UML/Class diagrams to better understand/communicate the system. 

    3.2 Adding Unit test cases.

    3.3 Add documentation/Java Doc.

    3.4 Adding better Exception Handling.

    3.5 Use Test Driven Development.

    3.6 Add better logging.

    3.7 Add support for multiple files.

    3.8 Add multiple input/output sources.

    3.9 Try to deploy the application to the cloud and add REST APIs for the functionalities
