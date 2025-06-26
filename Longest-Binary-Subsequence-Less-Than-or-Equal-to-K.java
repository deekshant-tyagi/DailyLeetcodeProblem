class Solution {
    public int longestSubsequence(String s, int k) {
        int len = 0;
        for(int i = 0; i < s.length(); i++) {
            int ind = s.length() - i - 1;
            if(s.charAt(ind) == '0') {
                len++;
            } else {
                if(Math.pow(2, i) <= k) {
                    k = (int)k - (int)Math.pow(2, i);
                    len++;
                }
            }
        }
        return len;
    }
}