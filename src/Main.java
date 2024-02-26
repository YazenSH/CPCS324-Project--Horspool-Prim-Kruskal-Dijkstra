import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
    1- YAZAN AL SHAEBI      ID: 2142647
    2- MOHANAD AL DAKHEEL   ID: 2135847
    3- ABDULHAMID SAATI     ID: 2135877
 */
public class Main {
    public static HashMap<Character, Integer> shiftTable = new HashMap<>();
    // Start of Horsepool
    // -----------------------------------------------------------------------------------------------------------------------
    // READ AND STORE METHOD: IT READS FROM INPUT FILE AND RETURNS A SUBSTRING WITH DESIRED NUMBER OF LINES
    //-----------------------------------------------------------------------------------------------------------------------
    public static String readAndStore( Scanner inputFile) {
        StringBuilder txt = new StringBuilder();
        while (inputFile.hasNextLine())
            txt.append(inputFile.nextLine()+"\n");
        return txt.toString().toLowerCase();
    }
    //END OF READ AND STORE
    //-----------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------
    // PATTERN GENERATOR: GENERATES WHATEVER NUMBER OF PATTERNS YOU WANT FROM A LARGER STRING
    //-----------------------------------------------------------------------------------------------------------------------
    public static String[] patternGenerator(int numberOfPatterns, String txt, int patternLength) {
        String[] pattern = new String[numberOfPatterns]; // ARRAY OF PATTERNS
        Random rand = new Random();
        for (int i = 0; i < pattern.length; i++) {
            // THE FOLLOWING IS A RANDOMLY GENERATED INDEX NUMBER RANGES FROM THE FIRST TO LAST INDEX
            //THIS RANDOM NUMBER WILL REPRESENT THE FIRST POSITION IN THE PATTERN
            int randomIndex = rand.nextInt(txt.length() - patternLength);
            pattern[i] = txt.substring(randomIndex, randomIndex + patternLength);
        }
        return pattern;
    }
    // END OF PATTERN GENERATOR
    //------------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //THE FOLLOWING IS SHIFT TABLE GENERATOR
    //-----------------------------------------------------------------------------------------------------------------------
    public static void shiftTableCreator(int patternLength, String pattern, int numberOfPatterns) {
        HashSet<Character> PatternChar = new HashSet<>();
        for (char c : pattern.toCharArray()) {
            PatternChar.add(c);
        }
        for (char c : PatternChar) {
            int Shift = 0;//intializing shift amount
            for (int j = pattern.length() - 2; j >= 0; j--) {
                if (pattern.charAt(j) == c) {
                    Shift = pattern.length() - j - 1;
                    break;
                }
            }
            if (Shift == 0) {
                Shift = patternLength;
            }
            shiftTable.put(c, Shift);
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //THE FOLLOWING TWO METHODS ARE THE ALGORITHMS WHICH ARE GOING TO BE USED
    public static int bruteForce(String txt, int patternLength, String pattern) {
        for (int i = 0; i <= txt.length() - patternLength; i++) {
            int j = 0;
            while ((j < patternLength) && (pattern.substring(j, j + 1).equals(txt.substring(i + j, i + j + 1)))) {
                j++;
            }
            if (j == patternLength) {
                return i;
            }
        }
        return -1;
    }
    public static int horspool(String txt, int patternLength, String pattern) {
        int i = patternLength - 1;
        int v =0;
        while (i <= txt.length() -1) {
            int k = 0;
            while ((k <= patternLength - 1) && (pattern.charAt(patternLength - 1 - k) == (txt.charAt(i - k)))) {
                k++;
            }
            if (k == patternLength) {
                return i - patternLength +1;
            }
            i += shiftTable.getOrDefault(txt.charAt(i), patternLength);

        }
        return -1;
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    private static String NumberOfLines(String txt,int linesQuantitiy){
        StringBuilder newTxt= new StringBuilder();
        String []arrayOfTXT = txt.split("\n");
        for(int i =0;i<linesQuantitiy-1;i++){
            newTxt.append(arrayOfTXT[i]);
        }
    return newTxt.toString();}
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //THE FOLLOWING METHOD CALCULATES AVERAGE TIME OF THE CHOSEN ALGORITHM
    //-----------------------------------------------------------------------------------------------------------------------

    //THE FOLLOWING METHOD CALCULATES AVERAGE TIME OF THE CHOSEN ALGORITHM
    public static long avgTime(int option, String txt, int patternLength, String[] pattern,int linesQuantitiy) {
        long startB, endB, endH,startH;
        // cut text
        txt = NumberOfLines(txt,linesQuantitiy);
        //IF SYSTEM CHOOSE 1 THEN ADD THE DURATION OF EACH PATTERN USING BRUTE FORCE, OTHERWISE ADD DURATION OF EACH PATTERN USING HORSPOOL
        switch (option) {
            case 1:

                startB = System.nanoTime();
                for(int i = 0; i < pattern.length ; i++) {
                    bruteForce(txt, patternLength, pattern[i]);
                }
                endB = System.nanoTime();
                return (endB - startB)/pattern.length;
            case 2:


                startH = System.nanoTime();
                for(int i = 0; i < pattern.length ; i++) {
                    shiftTableCreator(patternLength, pattern[i], patternLength);
                    horspool(txt, patternLength, pattern[i]);
                }
                endH = System.nanoTime();

                for (int i = 0; i < pattern.length; i++) {
                    shiftTable.clear();
                    shiftTableCreator(patternLength, pattern[i], patternLength);
                    System.out.println("the shift table for pattern" + "(" + pattern[i] + ")" + shiftTable);
                }
                return (endH - startH) / pattern.length;

        }



        return 0;}
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------

    //-----------------------------------------------------------------------------------------------------------------------
    // COMPARISON METHOD PRINTS A STATEMENT DETERMINING THE BETTER ALGORITHM
    public static void comparison(int linesQuantitiy,String txt, int patternLength, String[] patternsStrings) {
        long hTime = avgTime(2, txt, patternLength, patternsStrings,linesQuantitiy);
        System.out.println("Average time for horspool Approach: " + hTime);


        long bTime = avgTime(1, txt, patternLength, patternsStrings,linesQuantitiy);
        System.out.println("Average time for brute force Approach: " + bTime);
        if (bTime < hTime) {
            System.out.println("for this instance brute force approach is better than horspool");
        } else {
            System.out.println("for this instance horspool approach is better than brute force");
        }
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //end of Horsepool

    // Start Of Kruskal Algorithm methods
    public static void PrintKruskalTime(long start,long end){

        System.out.println("Running Time of Kruskal’s algorithm using Union-Find approach is "+(end - start)+" Nano seconds.");
    }
    public static void PrintKruskal(Stack<Edge> mst) {
        System.out.println("Minimum spanning tree:");
        int totalWeight =Kruskal.calcTotalWeight(mst);
        System.out.println("Total weight of MST by Kruskal's algorithm: " + (double)totalWeight);
        System.out.println("The edges in the MST are:");

        for (Edge edge : mst) {
            System.out.println("Edge from " + edge.ver1 + " to " + edge.ver2 + " has weight " +(double) edge.weight);
        }
    }

    public static List<Edge> ReadKruskalEdges(int nEdges,Scanner read) {
        List<Edge> edges = new ArrayList<>();
        for (int i = 0; i < nEdges; i++) {
            edges.add(new Edge(read.nextInt(), read.nextInt(), read.nextInt()));
        }
        Collections.sort(edges);
        return edges;
    }
    public static Stack<Edge> KruskalAndStack(int n,List<Edge> edges){
        Kruskal uf = new Kruskal(n);
        Stack<Edge> mst = new Stack<>();
        for (Edge edge : edges) {
            if (!uf.isSameSet(edge.ver1, edge.ver2)) {
                uf.unionSet(edge.ver1, edge.ver2);
                mst.push(edge);
            }
        }
        return mst;
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //Method for printing Graph in Dijkstra
    public static void printDetailsGraph(Graph graph){
        System.out.println("Weight Matrix: \n");
        System.out.println("   0  1  2  3  4\n");
        for (int i =0; i<graph.getVertexList().length;i++){
            System.out.print(i);
            for (int j = 0; j <graph.getVertexList().length ; j++) {
                System.out.print("  "+graph.getEdgesArray()[i][j]);
            }
            System.out.println();
        }
        System.out.println();

        System.out.println("# of vertices is: " +graph.getNumberOfVerts()+", # of edges is: "+graph.numEdgesEntered);
        for (int i =0; i<graph.getVertexList().length;i++){
            System.out.print(i+":  ");
            for (int j = 0; j <graph.getVertexList().length ; j++) {
                if (graph.getEdgesArray()[i][j]!=0){
                    System.out.print(i+"-"+j+" "+graph.getEdgesArray()[i][j]+"  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
    //-----------------------------------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------------------------------
    //print dijkstra comparison
    public static void printComparison(long heapTime, long arrayTime){
        System.out.println("Comparison Of the running time :");
        System.out.println("Running time of Dijkstra using priority queue is: "+arrayTime+" nano seconds");
        System.out.println("Running time of Dijkstra using min Heap is: "+heapTime+" nano seconds");
        if (heapTime<arrayTime)
            System.out.println("Running time of Dijkstra using min Heap is better");
        else
            System.out.println("Running time of Dijkstra using priority queue is better");


    }
    //-----------------------------------------------------------------------------------------------------------------------
    //Print Dijkstra details
    public static void printDijkstra(Graph graph,int[] distances,String type,int start){
        if (type.equalsIgnoreCase("Heap"))
            System.out.println("Dijkstra using min heap:");
        else
            System.out.println("Dijkstra using priority queue:");
        System.out.println("Shortest paths from vertex "+start+" are");

        for (int i =0;i<distances.length;i++){
            System.out.println("A path from "+start+" to "+i+": " +
                    graph.getVertexList()[i].dijPrev+ " (Length:"+(double)distances[i]+")");
        }
        System.out.println();
    }
    //----------------------------------------------------------------------------------------------
    //Method to read from graph
    public static Graph readGraph(Scanner read, String Algo){
        int numberVertices =Integer.parseInt(read.nextLine());
        int numOfEdges =Integer.parseInt(read.nextLine());
        Graph graph = new Graph(numberVertices);
        graph.numEdgesEntered =numOfEdges; //for Dijkstra print
        if (Algo.equalsIgnoreCase("Prims")){
            for (int i = 0; i < numOfEdges; i++) {
                String []ReadEdges = read.nextLine().split(" ");
                graph.setEdgePrims(Integer.parseInt(ReadEdges[0]),
                    Integer.parseInt(ReadEdges[1]),Integer.parseInt(ReadEdges[2]));
            }
        }
        else {
            for (int i = 0; i < numOfEdges; i++) {
                String []ReadEdges = read.nextLine().split(" ");
                graph.setEdgeDijikstra(Integer.parseInt(ReadEdges[0]),
                        Integer.parseInt(ReadEdges[1]),Integer.parseInt(ReadEdges[2]));
            }
        }
    return graph;
    }
    //----------------------------------------------------------------------------------------------
    // Reset Vertices for another iteration
    public static void resetVisitedVertices(Graph graph){
        for (int i = 0; i < graph.getNumberOfVerts(); i++) {
            graph.getVertexList()[i].wasVisited=false;
        }
    }
    //----------------------------------------------------------------------------------------------
    //Reset track of Dijkstra for each vertex
    public static void resetDijPrev(Graph graph){
        for (int i = 0; i < graph.getNumberOfVerts(); i++) {
            graph.getVertexList()[i].dijPrev="";
        }
    }
    //----------------------------------------------------------------------------------------------
    //print array for prims
    public static void printPrimsArray(Edge edges[],long timeTaken){
        int totalWeight = 0;
        for (int i =0;i< edges.length;i++)
            totalWeight += edges[i].weight;
        System.out.println("Total weight of MST by Prim's algorithm (Using unordered Min-Priority queue): "
                + ((double)totalWeight));
        System.out.println("The edges in the MST are:");
        for (int i = 0; i < edges.length; i++) {
            System.out.println("Edge from " + edges[i].ver1 + " to "+edges[i].ver2+" has weight "+(double)edges[i].weight);
        }
        System.out.println("\nRunning time of Prim’s algorithm using unordered Min-Priority Queue is "+timeTaken+" Nano seconds\n");
    }
    //----------------------------------------------------------------------------------------------
    //print heap fpr prim
    public static void printPrimsHeap(Edge edges[],long timeTaken){
        int totalWeight = 0;
        for (int i =0;i< edges.length;i++)
            totalWeight += edges[i].weight;
        System.out.println("Total weight of MST by Prim's algorithm (Using Min-Heap):"
                + ((double)totalWeight));
        System.out.println("The edges in the MST are:");
        for (int i = 0; i < edges.length; i++) {
            System.out.println("Edge from " + edges[i].ver1 + " to "+edges[i].ver2+" has weight "+(double)edges[i].weight);
        }
        System.out.println("\nRunning time of Prim’s algorithm Using Min-Heap is "+timeTaken+" Nano seconds");
    }
    //----------------------------------------------------------------------------------------------

    public static void main(String[] args) throws FileNotFoundException {
        //Here we Are Using 1 file As the input, the User have to change the content of the file first, then Apply the algorithm he wants to use.
        //There is a txt file called Sample input, which have a sample input for each algorithm, You may use it for testing.
        //for horsepool, you may choose between 1 and 9 because the txt has maximum of 9 lines, you may add more text and test it for more than 9 lines

        File userInput = new File("input.txt");
        if (!(userInput.exists())) // file availability checker
        {
            System.out.println(userInput + "does not exist, please try again!");
            System.exit(0);
        }
        Scanner input = new Scanner(System.in);
        Scanner read = new Scanner(userInput);
        File outpuFile = new File("patterns.txt");
        PrintWriter filewriter = new PrintWriter(outpuFile);
        int choice = input.nextInt();

        if(choice == 1) {

        /*
            the following code is for prompting the user to enter:-
            the number of lines to be read, the number of patterns to be generated, and the length of each generated pattern
        */
            System.out.println("How many lines you want to read from the text file(choose from 1 to 9)?: ");
            int linesQuantitiy = input.nextInt();
            System.out.println("How many patterns to be generated?: ");
            int patternsQuantity = input.nextInt();
            System.out.println("What is the length of each pattern?: ");
            int patternLength = input.nextInt();

            // this is a string which the truncated part of the text file, the part we are going to try the algorithms on
            String txt = readAndStore(read);

            //the following code generates patterns, and print them in an output file
            String[] patternsStrings = patternGenerator(patternsQuantity, txt, patternLength);
            for (int i = 0; i < patternsStrings.length; i++) {
                filewriter.println(patternsStrings[i]);
                filewriter.flush();
            }
            System.out.println(patternsQuantity + " Patterns, each of length " + patternLength + " have been generated in a file patterns.txt");
            // the following code applies both horsepool and brute force str matching algorithms and then determines the faster one
            comparison(linesQuantitiy,txt, patternLength, patternsStrings);

        } else if (choice==2) {

            Graph graph = readGraph(read, "Prims");

            //Prims for array
            long startA =System.nanoTime();
            Edge[] edges = PrimsAlgo.findMST(graph, "Array");
            long timeTakenA = System.nanoTime()- startA;
            printPrimsArray(edges, timeTakenA);

            resetVisitedVertices(graph);

            //prims for heap
            long start = System.nanoTime();
            edges = PrimsAlgo.findMST(graph, "Heap");
            long timeTaken = System.nanoTime() - start;
            resetVisitedVertices(graph);

            printPrimsHeap(edges, timeTaken);
        }
           else if(choice == 3) {
            int nVertex = read.nextInt();
            int nEdges = read.nextInt();
            List<Edge> edges = ReadKruskalEdges(nEdges,read);

            long startTime = System.nanoTime();

            Stack<Edge> mst = KruskalAndStack(nVertex,edges);

            long endTime = System.nanoTime();
            PrintKruskal(mst);
            PrintKruskalTime(startTime,endTime);
        }

           else if (choice ==4) {
            Graph graph = readGraph(read, "Dijkstra");
            printDetailsGraph(graph);

            System.out.print("Enter Source vertex: "); //Starting vertex
            int startVer = input.nextInt();
            System.out.println();

             //Dijkstra with array
            long startA = System.nanoTime();
            int [] distances = DijkstraAlgo.Dijkstra(graph, "Array", startVer);
            long endA = System.nanoTime();
            long arrayTime = endA - startA;
            printDijkstra(graph, distances, "Array", startVer);

            //Reset for heap
            resetDijPrev(graph);
            resetVisitedVertices(graph);

            //Dijkstra with heap
            long startH = System.nanoTime();
            distances = DijkstraAlgo.Dijkstra(graph, "Heap", startVer);
            long endH = System.nanoTime(); //
            long heapTime = endH - startH; //
            printDijkstra(graph, distances, "heap", startVer);

            printComparison(heapTime, arrayTime);

        } else if (choice==5) {
            System.out.println("Quiting. :3 Bye");
        }
    }
}