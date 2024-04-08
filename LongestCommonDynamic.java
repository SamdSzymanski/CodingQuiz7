import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'longestCommonSubsequence' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER_ARRAY a
     *  2. INTEGER_ARRAY b
     */

    public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
    // Write your code here
    //Initialize 2 arrays that have an equal size to the lists given
        int[] arr1 = new int[a.size()];
        int[] arr2 = new int[b.size()];
        
        //Nested loops copy all elements in the given lists to the new arrays
        for(int i=0;i<a.size();i++){
            arr1[i]=a.get(i);
        }
        
        for(int i=0;i<b.size();i++){
            arr2[i]=b.get(i);
        }
        
        int n = arr1.length;
        int m = arr2.length;
        //2d array stores the lengths of the LCS for various subproblems
        int[][] dp = new int[n+1][m+1];


        //Either one of the sequences is empty, the length of the subsequence is 0
        for(int i=0;i<=n;i++){
            for(int j=0;j<=m;j++){
                if(i==0||j==0){
                    dp[i][j]=0;
                }
            }
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(arr1[i-1]==arr2[j-1]){
                    dp[i][j]= 1+dp[i-1][j-1];
                }else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        int i=n,j=m;
        while(i>0 && j>0){
            if(arr1[i-1]==arr2[j-1]){
                res.add(arr1[i-1]);
                i--;
                j--;
            }else{
                if(dp[i][j-1]>dp[i-1][j]){
                    j--;
                }else i--;
            }
        }
        //CRUCIAL, returns the characters in the correct order 
        Collections.reverse(res);
        return res;
    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int m = Integer.parseInt(firstMultipleInput[1]);

        String[] aTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> a = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int aItem = Integer.parseInt(aTemp[i]);
            a.add(aItem);
        }

        String[] bTemp = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        List<Integer> b = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            int bItem = Integer.parseInt(bTemp[i]);
            b.add(bItem);
        }

        List<Integer> result = Result.longestCommonSubsequence(a, b);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write(" ");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
