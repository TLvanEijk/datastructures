package javaapplication1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

class SoccerLeague {
    // Count the number of occurences if the string Search in the String file name

    Map<String, Integer> Soccer_score = new TreeMap<>();

    // Main method
    public static void main(String[] args) {
        System.out.println("in first class");
        if (args.length < 1) {
            System.out.println("Usage: java ScanFileInteractive <file to search>");
            System.exit(-1);
        }
        // Save the file name as a string
        String file = args[0];

        // Create an instance of the SoccerLeague class
        SoccerLeague soccerFile = new SoccerLeague();

        //add the results to the treemap
        try {

            //call methods to calculate the points for each team
            soccerFile.getResults(file);

            //sort map in descending order of values
            LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
            soccerFile.Soccer_score.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

            //print each entry
            for (Map.Entry<String, Integer> pair : reverseSortedMap.entrySet()) {
                System.out.println(pair.getKey() + ":" + pair.getValue());
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

    }

    public void getResults(String file) throws IOException {

        // Chain a FileReader to a BufferedReader to a Scanner
        try (FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                Scanner s = new Scanner(br)) {
            s.useDelimiter("\n");
            int i = 0;
            String[] results = new String[5];
            while (s.hasNext()) {
                String lineToParse = s.next().trim();
                results[i] = lineToParse;
                i++;
            }
            splitArray1(results);
        }

    }

    public void splitArray1(String[] results) {
        int i = 0;
        String[] result;
        while (i < results.length) {
            result = results[i].split(", ");
            splitArray2(result[0], result[1]);
            i++;
        }
    }

    public void splitArray2(String firstTeam, String secondTeam) {

        String[] team1 = firstTeam.split(" ");
        String[] team2 = secondTeam.split(" ");

        //get points and add to map
        String t1Name = team1[0];
        String t2Name = team2[0];
        int t1Score = Integer.parseInt(team1[1]);
        int t2Score = Integer.parseInt(team2[1]);
        int count = 0;
        if (t1Score == t2Score) {            
            if (this.Soccer_score.containsKey(t1Name)) {
                count = this.Soccer_score.get(t1Name);
                this.Soccer_score.put(t1Name, count + 1);
            } else {
                this.Soccer_score.put(t1Name, 1);
            }
            if (this.Soccer_score.containsKey(t2Name)) {
                count = this.Soccer_score.get(t2Name);
                this.Soccer_score.put(t2Name, count + 1);
            } else {
                this.Soccer_score.put(t2Name, 1);
            }
        }
        else if (t1Score > t2Score) {
           
            if (this.Soccer_score.containsKey(t1Name)) {
                count = this.Soccer_score.get(t1Name);
                this.Soccer_score.put(t1Name, count + 3);
            } else {
                this.Soccer_score.put(t1Name, 3);

            }
             if (!(this.Soccer_score.containsKey(t2Name))) {
                this.Soccer_score.put(t2Name, 0);
            }
            
        } 
        else {
            
            if (this.Soccer_score.containsKey(t2Name)) {
                count = this.Soccer_score.get(t2Name);
                this.Soccer_score.put(t2Name, count + 3);
            } else {
                this.Soccer_score.put(t2Name, 3);

            }
             if (!(this.Soccer_score.containsKey(t1Name))) {
                this.Soccer_score.put(t1Name, 0);            }

        }
    }
}