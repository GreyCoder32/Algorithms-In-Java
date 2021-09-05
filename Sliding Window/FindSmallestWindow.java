/*
* Very Important
****************
* Time complexity  : O(n)
* Space complexity : O(n)
****************
Variable size sliding window
* Note: Not working with all the test case
* at leet code (Concept is fine for F2F Interview)
*****
https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
*/

import static java.lang.System.*;
import java.util.*;

public class FindSmallestWindow
{
	static int findMinSubSing(String s, String t)
	{
		int m = s.length();
		int n = t.length();
		int i=0, j=0;
		int minLength = Integer.MAX_VALUE;
		int windowBound[] = new int[2];
		
		Map<Character, Integer> map = new HashMap<>();
		
		/*
		* Count the frequency of all the 
		* charcaters in second string and
		* store it in map 
		*/
		
		for(int k=0; k<n; k++)
		{
			char ch = t.charAt(k);
			map.put(ch, map.getOrDefault(ch, 0)+1);
		}			
		
		/*
		* Initialize the count with
		* the map size
		* Here count represents the number of unique charcaters
		* in the map
		*/		
		int count = map.size();		
				
		while(j<m)
		{
			char ch = s.charAt(j);
			
			if(map.containsKey(ch))
			{
				/*
				* If map cotains charcter then decrease 
				* the frequency by 1
				* At the same time check if frequency
				* of a charcater becomes 0 decrease the count
				**********
				* frequency of character 0 means the the charcater
				* has been found in that window in the same amount
				* as in second string
				*/
				
				map.replace(ch, map.get(ch)-1);
				if(map.get(ch) == 0)
					count--;
				
				/*
				* Count becomes 0  means we got a window
				* containing all the characters as in 
				* the second string
				***************
				* new gative frequency means there are 
				* extra characters in the string
				*/
				if(count == 0)
				{
					/*
					* Start optimization of the window length
					* by removing uneccessary characters
					*/
					while(count == 0)
					{
						/*
						* Maintain the length of minimum window
						*/
						minLength = Math.min(minLength, j-i+1);
						windowBound[0] = i;
						windowBound[1] = j;
						ch = s.charAt(i);
						
						if(map.containsKey(ch))
						{
							boolean isZero = map.get(ch) == 0?
							true : false;	
							map.replace(ch, map.get(ch)+1);
							boolean isNonZero = map.get(ch) != 0?
								true : false;	
							
							/*
							* When the frequency of a character
							* in the map changes from 0 to non zero
							* increase the count (means this window no longer
							* possible candidate for the solution)
							*/							
							if(isZero && isNonZero)
								count++;								
						}
						
						i++;
					}
				}
	 		}
			
			j++;
		}
		
		/*
		* print sub-string it self
		*/
		
		out.println(s.substring(windowBound[0],windowBound[1]+1));
		
		/*
		* If minLength is -Inf means 
		* minLength is not updated (no window present
		* that can satishfy the given criteria)
		*/
		
		return minLength == Integer.MAX_VALUE ? 0 : minLength;
	}
					
	
	public static void main(String [] args)
	{
		String s = "TOTMTAPTAT";
		String t = "ATT";
		
		out.println(findMinSubSing(s, t));
	}
}

