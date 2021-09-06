// similiar to subset sum problem
// instead of return true and false we just 
// have to return the number of subsets

/**
* The target sum problem could be solved by
* like the asme approach
* just count number of subset pairs 
* that can produce target_sum (on assigning (+/-) signs to each number) 
*/

import static java.lang.System.*;

public class CountSubsets
{
	public static void main(String args[])
	{
		int set[] = {1,3,4};
		int n = 3;
		int sum = 4;
		out.println(solve(set, n, sum));
	}
	
	static int solve(int set[], int n, int sum) //bottom up
	{
	   int dp[][] = new int[n+1][sum+1];
	   
	   for(int i=0;i<n+1; i++)
		   dp[i][0] = 1;
	   
	   for(int i=1; i<n+1; i++)
	   {
		   for(int j=1; j<sum+1; j++)
		   {
			   if(set[i-1] <= j)
				   dp[i][j] = dp[i-1][j-set[i-1]] + dp[i-1][j];
			   else if(set[i-1] > j)
				   dp[i][j] =  dp[i-1][j];
		   }
	   }
	   
	   return dp[n][sum];
	}
}