package exercise.level01;

/**
 * 子序列问题
 * @author tangKID
 *
 */
public class SolutionOfLIS {
	/**
	 * 最长递增子序列 
	 * @param arr
	 * @return
	 */
	public int[] getdp1(int[] arr){
		//dp[i]的含义是以arr[i]为结尾的最长递增子序列的长度
		int[] dp = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++){
				if(arr[i] > arr[j]){
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
		return dp;
	}
	public int[] generateLIS(int[] arr, int[] dp){
		int len = 0;
		int index = 0;
		for (int i = 0; i < dp.length; i++) {
			if(dp[i] > len){
				len = dp[i];
				index = i;
			}
		}
		int[] lis = new int[len];
		lis[--len] = arr[index];
		for (int i = index; i >= 0; i--) {
			if(arr[i] < arr[index] && dp[i] == dp[index]-1){
				lis[--len] = arr[i];
				index = i;
			}
		}
		return lis;
	}
	//主方法
	public int[] lis1(int[] arr){
		if(arr == null || arr.length == 0){
			return null;
		}
		int[] dp = getdp1(arr);
		
		return generateLIS(arr, dp);
	}
	
	public int[] getdp2(int[] arr){
		int[] dp = new int[arr.length];
		int[] ends = new int[arr.length];
		ends[0] = arr[0];
		int right = 0;
		int l = 0;
		int r = 0;
		int m = 0;
		for (int i = 1; i < ends.length; i++) {
			l = 0;
			r = right;
			while(l <= r){
				m = (l + r) / 2;
				if(arr[i] < ends[m]){
					l = m+1;
				}else{
					r = m-1;
				}
			}
			right = Math.max(right, l);
			ends[l] = arr[i];
			dp[i] = l + 1;
		}
		return dp;
	}
	/**
	 * 最长公共子序列
	 * @param str1
	 * @param str2
	 * @return
	 */
	public int[][] getdp(char[] str1, char[] str2){
		//dp[i][j]是指以str1[0...i]str2[0...j]的最长公共子序列长度
		int[][] dp = new int[str1.length][str2.length];
		dp[0][0] = str1[0] == str2[0] ? 1:0;
		//第一列
		for (int i = 1; i < str2.length; i++) {
			dp[0][i] = Math.max(dp[0][i-1], str1[0] == str2[i] ? 1:0); 
		}
		//第一行
		for (int i = 0; i < str1.length; i++) {
			dp[i][0] = Math.max(dp[i-1][0], str1[i] == str2[0] ? 1:0);
		}
		//对于其他位置的(i,j)
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				if(str1[i] == str2[j]){
					dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
				}
			}
		}
		return dp;
	}
	public String lcase(String str1, String str2){
		if(str1 == null || str2 == null || str1.equals("") || str2.equals("")){
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp = getdp(chs1, chs2); //获取二维表
		int m = chs1.length - 1;
		int n = chs2.length - 1;
		char[] res = new char[dp[m][n]];
		int index = res.length-1;
		while(index >= 0){
			if(n > 0 && dp[m][n] == dp[m][n-1]){
				n--;
			}else if(m > 0 && dp [m][n] == dp[m-1][n]){
				m--;
			}else{
				res[index--] = chs1[m];
				m--;
				n--;
			}
		}
		return String.valueOf(res);
	} 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
