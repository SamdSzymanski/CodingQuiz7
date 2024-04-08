import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'getWays' function below.
     *
     * The function is expected to return a LONG_INTEGER.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. LONG_INTEGER_ARRAY c
     */

    public static long getWays(int N, List<Long> Coins) {
        // Initialize amount to N and index to 0
        return countWays(N, Coins, N, 0);
    }

    private static long countWays(long amount, List<Long> coins, long remainingAmount, int index) {
        // Base cases:
        if (remainingAmount == 0) {
            return 1;
        }
        if (remainingAmount < 0 || index >= coins.size()) {
            return 0;
        }

        // Recursive cases:
        long includeCurrentCoin = countWays(amount, coins, remainingAmount - coins.get(index), index);
        long excludeCurrentCoin = countWays(amount, coins, remainingAmount, index + 1);

        return includeCurrentCoin + excludeCurrentCoin;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        String[] cTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Long> c = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cTemp[i]);
            c.add(cItem);
        }

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = Result.getWays(n, c);

        bufferedWriter.write(String.valueOf(ways));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}

//EXPLANATION OF RECURSION: The getWays method takes two parameters, 'N', the amount that needs to be reached, and 'Coins', which is the list of available coins. 
//It then calls the countWays method until the result is found.
//The countWays method returns the number of ways to make change for a given amount using a list of coins.
//It handles the base cases where remainingAmount is either 0, meaning that there is a way to reach the number N with the coins in Coins, or the remainingAmount variable is less than 0, meaning that we've gone over and that there is no way to reach N with the current coins.
//countWays recursively calls itself until all possible coin combinations that sum to N have been found.

