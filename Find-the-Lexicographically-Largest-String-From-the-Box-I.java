class Solution {
    public String answerString(String word, int numFriends) {
        if(numFriends == 1) return word;
        
        char max = ' ';

        String ans = "";

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) > max) {
                max = word.charAt(i);
            }
        }

        for(int i = 0; i < word.length(); i++) {
            if(word.charAt(i) == max) {
                String temp = word.substring(i, Math.min(word.length(), i + word.length() - (numFriends - 1)));

                if(temp.compareTo(ans) > 0) {
                    ans = temp;
                }
            }
        }

        return ans;
    }
}