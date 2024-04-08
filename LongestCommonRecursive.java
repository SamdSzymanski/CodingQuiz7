public static List<Integer> longestCommonSubsequence(List<Integer> a, List<Integer> b) {
        int m = a.size();
        int n = b.size();
        return longestCommonSubsequenceRecursive(a, b, m, n);
    }
    
    private static List<Integer> longestCommonSubsequenceRecursive(List<Integer> a, List<Integer> b, int m, int n) {
        if (m == 0 || n == 0) {
            return new ArrayList<>();
        }
        
        if (a.get(m - 1).equals(b.get(n - 1))) {
            List<Integer> lcs = longestCommonSubsequenceRecursive(a.subList(0, m - 1), b.subList(0, n - 1), m - 1, n - 1);
            lcs.add(a.get(m - 1));
            return lcs;
        } else {
            List<Integer> lcs1 = longestCommonSubsequenceRecursive(a, b.subList(0, n - 1), m, n - 1);
            List<Integer> lcs2 = longestCommonSubsequenceRecursive(a.subList(0, m - 1), b, m - 1, n);
            return lcs1.size() > lcs2.size() ? lcs1 : lcs2;
        }
    }
}
//RECURSIVE EXPLANATION: The preceding code recursively iterates through possible combinations of subsequences until one doesn't equal the other,
// it returns the longest of these subsequences. To get around manipulating the original function, a helper recursive function is implemented
// which handles base cases where the size of either array is 0. The integers m and n are used to create sublists in the original array to analyze
// the substrings more efficiently.
