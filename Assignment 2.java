package analysis_assignment1;

import java.util.ArrayList;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//class Pair<K, V> {
//    private K one;
//    private V two;
//
//    public Pair(K one, V two) {
//        this.one = one;
//        this.two = two;
//    }
//    public String toString() {
//        return "(" + one + ", " + two + ")";
//    }
//}

public class Divide_and_conquer_approach {
	
	

    public static String[] sequenceAlignment(String x, String y, Map<Character, Map<Character, Double>> scoreMatrix) {
        int m = x.length();
        int n = y.length();

        double[][] resultMatrix = new double[m + 1][n + 1];
        String[][] directionMatrix = new String[m + 1][n + 1];

        // Initialization
        for (int i = 0; i <= m; i++) {
            Arrays.fill(resultMatrix[i], 0);
            Arrays.fill(directionMatrix[i], "");
        }
        
      // first row 
      for (int j = 1; j < m+1; j++) {
    	  resultMatrix[0][j]=resultMatrix[0][j-1]+scoreMatrix.get('-').get(x.charAt(j-1));
      }
      
      //first column
      for (int j = 1; j < n+1; j++) {
    	  resultMatrix[j][0]=resultMatrix[j-1][0]+scoreMatrix.get(y.charAt(j-1)).get('-');
      }
//    for (int i = 0; i <= m; i++) {
//    for (int j = 0; j <= n; j++) {
//    System.out.print(resultMatrix[i][j]+" ");
//    }
//    System.out.println();
//    }
//    
        // Dynamic programming to fill resultMatrix and directionMatrix
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                double match = resultMatrix[i - 1][j - 1] + scoreMatrix.get(x.charAt(i - 1)).get(y.charAt(j - 1));
                double up = resultMatrix[i - 1][j] + scoreMatrix.get(x.charAt(i - 1)).get('-');
                double left = resultMatrix[i][j - 1] + scoreMatrix.get('-').get(y.charAt(j - 1));

                double maxScore = Math.max(match, Math.max(up, left));
                resultMatrix[i][j] = maxScore;

                if (maxScore == match) {
                    directionMatrix[i][j] = "diagonal";
                } else if (maxScore == up) {
                    directionMatrix[i][j] = "up";
                } else {
                    directionMatrix[i][j] = "left";
                }
            }
        }
//        for (int i = 0; i <= m; i++) {
//            for (int j = 0; j <= n; j++) {
//            System.out.print(resultMatrix[i][j]+" ");
//            }
//            System.out.println();
//            }
//            

        // Traceback to get aligned sequences
        StringBuilder alignedX = new StringBuilder();
        StringBuilder alignedY = new StringBuilder();
        int i = m;
        int j = n;

        while (i > 0 || j > 0) {
            if (directionMatrix[i][j].equals("diagonal")) {
                alignedX.insert(0, x.charAt(i - 1));
                alignedY.insert(0, y.charAt(j - 1));
                i--;
                j--;
            } else if (directionMatrix[i][j].equals("up")) {
                alignedX.insert(0, x.charAt(i - 1));
                alignedY.insert(0, '-');
                i--;
            } else {
                alignedX.insert(0, '-');
                alignedY.insert(0, y.charAt(j - 1));
                j--;
            }
        }

        // Calculate alignment score
        double alignmentScore = 0;
        for (int k = 0; k < alignedX.length(); k++) {
            alignmentScore += scoreMatrix.get(alignedX.charAt(k)).get(alignedY.charAt(k));
        }

        return new String[]{alignedX.toString(), alignedY.toString(), Double.toString(alignmentScore)};
    }

    
    public static void main(String[] args) {
    	

    	String seqA = "TCCCAGTTATGTCAGGGGACACGAGCATGCAGAGAC";
    	String seqB = "AATTGCCGCCGTCGTTTTCAGCAGTTATGTCAGATC";

        
        Map<Character, Map<Character, Double>> scoringMatrix = new HashMap<>();
        scoringMatrix.put('A', new HashMap<>(Map.of('A', 1.0, 'G', -0.8, 'T', -0.2, 'C', -2.3, '-', -0.6)));
        scoringMatrix.put('G', new HashMap<>(Map.of('A', -0.8, 'G', 1.0, 'T', -1.1, 'C', -0.7, '-', -1.5)));
        scoringMatrix.put('T', new HashMap<>(Map.of('A', -0.2, 'G', -1.1, 'T', 1.0, 'C', -0.5, '-', -0.9)));
        scoringMatrix.put('C', new HashMap<>(Map.of('A', -2.3, 'G', -0.7, 'T', -0.5, 'C', 1.0, '-', -1.0)));
        scoringMatrix.put('-', new HashMap<>(Map.of('A', -0.6, 'G', -1.5, 'T', -0.9, 'C', -1.0, '-', 0.0)));
    
        String[] output = sequenceAlignment(seqA, seqB, scoringMatrix);
        System.out.println("Sequence X: " + output[0]);
        System.out.println("Sequence Y: " + output[1]);
        System.out.println("Alignment Score: " + output[2]);
//    	Scanner sc = new Scanner (System.in);
//    	String x = sc.nextLine();
//        String[] characters  = x.split(" ");
//        printArray(characters);
//        String input1 = "ATGCC";
//        String input2 = "TACGCA";
//        
//
//        double[][] scoreMatrix = {
//                {1.0, -0.8, -0.2, -2.3, -0.6},
//                {-0.8, 1.0, -1.1, -0.7, -1.5},
//                {-0.2, -1.1, 1.0, -0.5, -0.9},
//                {-2.3, -0.7, -0.5, 1.0, -1.0},
//                {-0.6, -1.5, -0.9, -1.0, Double.NaN}
//        };
//        
//        Map<Pair<String, String>, Double> map = new HashMap<>();
//        for (int i=0;i<characters.length;i++)
//        {
//        	for (int j=0;j<characters.length;j++)
//        	{
//        		map.put(new Pair<>(characters[i],characters[j]), scoreMatrix[i][j]);
//        	}
//        }
//
//        int m = input1.length();
//        int n = input2.length();
//
//        double[][] resultMatrix = new double[m + 1][n + 1];
//        resultMatrix[0][0]=0;
//        // first row 
//        for (int j = 1; j < m; j++) {
//        	System.out.println(map.get(new Pair <>('-',input1.charAt(j))));
//         //   resultMatrix[0][j] = (Double)(resultMatrix[0][j-1]+((Double)map.get(new Pair <>("-",input1.charAt(j)))));
//        }
//        printMatrix(resultMatrix);
////        for (int i = 1; i <= m; i++) {
////            resultMatrix[i][0] = resultMatrix[0][i-1]+map.get(new Pair <>(characters[i],"-"));
////        }
////
////        // Filling the DP matrix
//////        for (int i = 1; i <= m; i++) {
//////            for (int j = 1; j <= n; j++) {
//////                double match = dp[i - 1][j - 1] + scoreMatrix1[getIndex(input1.charAt(i - 1))][getIndex(input2.charAt(j - 1))];
//////                double delete = dp[i - 1][j] + getGapPenalty(input1.charAt(i - 1));
//////                double insert = dp[i][j - 1] + getGapPenalty(input2.charAt(j - 1));
//////                dp[i][j] = Math.max(match, Math.max(delete, insert));
//////            }
//////        }
////        
////        // implemet recurence relation 
////      for (int i = 1; i <= n; i++) {
////      for (int j = 1; j <= m; j++) {
////    	  double cost =map.get(new Pair<>(input2.charAt(i),input1.charAt(j)));
////    	  resultMatrix[i][j]= Math.max(Math.max(resultMatrix[i-1][j-1]+cost,resultMatrix[i-1][j]+cost),resultMatrix[i][j-1]+cost);
////          }
////      }
////      printMatrix(resultMatrix);
////      
//      }
//    static void printArray(String[] characters) {
//        for (int i = 0; i < characters.length; i++) {
//            System.out.print(characters[i] + " ");
//        }
//        System.out.println(); // Move to the next line after printing the array
//    }
//    static void printHashMap(Map<Pair<String, String>, Double> map) {
//        for (Map.Entry<Pair<String, String>, Double> entry : map.entrySet()) {
//            Pair<String, String> key = entry.getKey();
//            Double value = entry.getValue();
//            System.out.println("Key: " + key + ", Value: " + value);
//        }
//    }
// }
//        static void printMatrix(double[][] resultMatrix) {
//        	for (int i = 0; i < resultMatrix.length; i++) {
//        		for (int j = 0; j < resultMatrix[i].length; j++) {
//        			System.out.print(resultMatrix[i][j] + " ");
//        		}
//        		System.out.println(); // Move to the next line after each row
//        	}
//        }
//
    }
}
