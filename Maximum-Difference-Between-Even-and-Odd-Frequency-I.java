class Solution {
    public int maxDifference(String s) {
    int freq[] = new int[26];
    int oddMax = 0;
    int evenMin = Integer.MAX_VALUE;

    for(int i=0; i<s.length(); i++)
    {
       freq[s.charAt(i)-'a']++; 
    } 
     
     for(int i=0; i<26; i++)
     {
         if(freq[i]%2==1)
         {
            oddMax = Math.max(oddMax, freq[i]);            
         } 
          if(freq[i]%2==0 && freq[i]>0)
          {
              evenMin = Math.min(evenMin, freq[i]);
          }      
         
     } 

     return evenMin!=Integer.MAX_VALUE ? oddMax-evenMin : oddMax;

     

    }
}