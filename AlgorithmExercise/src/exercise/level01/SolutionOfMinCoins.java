package exercise.level01;

/**
 * @author tangKID
 * 
 */
public class SolutionOfMinCoins {
	
	/**
	 * 换钱的最少货币数
	 * @param arr
	 * @param aim
	 * @return
	 */
	public int minCoins(int [] arr, int aim){
		
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		int max = Integer.MAX_VALUE;
		// dp[i][j]代表可以任意使用arr[0...i]coins的情况下,组成j所需的最小张数
		int[][] dp = new int[arr.length][aim+1];
		// dp的第一列全部赋值为0,和初始化值相等，所以不需要处理。
		/**
		 *  第一个for循环，dp的第一行的计算
		 *  表示只能使用arr[0]coin的情况下，能够找开的钱数。
		 *  所有j必须是arr[0]的倍数
		 *  考虑到第一个符合条件的值为j-arr[0]=0
		 *  在此时dp[0][j] = max
		 *  然后 dp[0][j] = dp[0][j-arr[0]] + 1;
		 *  即把除arr[0]倍数之外的值赋值为了max，又把需要存放的位置成功赋值
		 */
		for (int j = 1; j <= aim; j++) {
			dp[0][j] = max;	
		   /*
			 if(j-arr[0] >= 0 && dp[0][j-arr[0]] != max){
				
				dp[0][j] = dp[0][j-arr[0]] + 1;
			 }
		   */
			if(arr[0]!=0 && j % arr[0]==0){
				dp[0][j] = dp[0][j-arr[0]] + 1;
			}
		}
		//
		int left = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				left = max;
				if(arr[i]!=0 && j % arr[i] == 0){
					left = dp[i][j-arr[i]]+1;
				}
				dp[i][j] = Math.min(left, dp[i-1][j]);
			}
		}
		return dp[arr.length-1][aim] != max?dp[arr.length-1][aim]:-1;
	}
	
	public int minCoins02(int[] arr, int aim){
		
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		int max = Integer.MAX_VALUE;
		int[] dp = new int[aim+1];
		
		for	(int j = 1; j <=aim; j++) {
			dp[j] = max;
			if(arr[0]!=0 && j%arr[0]==0){
				
				dp[j] = dp[j-arr[0]] + 1;
			}
		}
		int left = 0;
		for (int i = 1; i < arr.length; i++) {
			
			for (int j = 1; j <= aim; j++) {
				
				left = max;
				if(arr[i] != 0 && j % arr[i] == 0){
					left = dp[j - arr[i]] + 1; //dp[i][j-arr[i]]
				}
				dp[j] = Math.min(left, dp[j]);
			}
		}
		return dp[aim] != max? dp[aim]:-1;
	}
	// 进阶题目
	public int minCoins03(int[] arr, int aim){
		
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		//dp在任意使用arr[0...i]coins的情况下(每个值仅代表一张货币)，组成j所需的最小张数。
		int[][] dp = new int[n][aim+1];
		for (int j = 1; j <= aim; j++) {
			dp[0][j] = max;
		}
		if(arr[0] <= aim){
			dp[0][arr[0]] = 1;
		}
		
		/**
		 * 按照从左到右，从上到下的计算
		 */
		int leftup = 0;
		for (int i = 1; i < n; i++) {
			for (int j = 1; j <= aim; j++) {
				leftup = max;
				if(j-arr[i] >=0 && dp[i-1][j-arr[i]] != max){
					leftup = dp[i-1][j-arr[i]]+1;
				}
				dp[i][j] = Math.min(leftup, dp[i-1][j]);
			}
		}
		return dp[n-1][aim]!= max? dp[n-1][aim]:-1;
	}
	
	public int minCoins04(int[] arr, int aim){
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		int[] dp = new int[aim+1];
		for (int j = 1; j <=aim; j++) {
			dp[j] = max;
		}
		if(arr[0]<=aim){
			dp[arr[0]]  = 1;
		}
		int leftup = 0;
		//从上到下
		for (int i = 1; i < n; i++) {
			/**
			 * 第二个循环是从后往前的,j>j-arr[i],
			 * 因为dp[i][j]需要用到上一个的dp[i-1][j-arr[i]]的值，
			 * 从后往前，就不会出现覆盖的问题
			 * 而从前往后，dp[j-arr[i]]就已经被dp[i][j-arr[i]]覆盖了
			 * 
			 */
			for (int j = aim; j > 0; j--) {
				leftup = max;
				if(j-arr[i] >= 0 && dp[j-arr[i]] != max){
					leftup = dp[j-arr[i]] + 1; //dp[i-1][j-arr[i]] 
				}
				dp[j] = Math.min(leftup, dp[j]); //这里的dp[j]还是dp[i-1][j]
			}
		}
		return dp[aim]!=max? dp[aim]:-1;
	}
	
	/**
	 * 换钱的方法数
	 * @param arr
	 * @param aim
	 * @return
	 */
	public int exchangeCoins(int[] arr, int aim){
		if(arr == null || arr.length==0 || aim < 0){
			return -1;
		}
		
		return process1(arr, 0, aim);
	}
	/**
	 * arr[index....n]这些面值的钱组成aim
	 * @param arr
	 * @param index
	 * @param aim
	 * @return
	 */
	public int process1(int[] arr, int index, int aim){
		int res = 0;
		if(index== arr.length){
			res = aim==0? 1:0; //只有在递归底部，aim=0,才能算是换钱的一种方法。
		} else{
			for (int i = 0; arr[index]*i <=aim; i++) {
				res+= process1(arr, index+1, aim- arr[index]*i);
			}
		}
		return res;
	}
	//记忆化搜索方法
	public int exchangeCoins02(int[] arr, int aim){
		if(arr == null || arr.length==0 || aim < 0){
			return -1;
		}
		int[][] map = new int[arr.length+1][aim+1];
		return process02(arr,0,aim,map);
	}
	
	public int process02(int[] arr, int index, int aim, int[][] map){
		int res = 0;
		if(index == arr.length){
			res= aim == 0? 1:0;
		}else{
			int mapValue = 0;
			for (int i = 0; arr[index]*i <= aim ; i++) {
				mapValue = map[index+1][aim - arr[index]*i];
				if(mapValue!=0){
					res += mapValue == -1? 0:mapValue;
				}else{
					res += process02(arr, index+1, aim - arr[index]*i, map);
				}
			}
		}
		map[index][aim] = res == 0 ? -1:res;
		return res;
	}
	/**
	 *  
	 * @param arr
	 * @param aim
	 * @return
	 */
	public int exchangeCoins03(int[] arr, int aim){
		if(arr==null || arr.length==0 || aim < 0){
			return -1;
		}
		int[][] dp = new int[arr.length][aim+1];
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] =1;
		}
		for (int j = 1; arr[0]*j <= aim; j++) {
			dp[0][arr[0]*j] = 1;
		}
		int num = 0;
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				num = 0;
				for (int k = 0; j-arr[i] *k >=0; k++) {
					num += dp[i-1][j - arr[i] * k];
				}
				dp[i][j] = num;
			}
		}
		return dp[arr.length-1][aim];
	}
	 
	public int exchangeCoins04(int[] arr, int aim){
		if(arr==null || arr.length ==0 || aim<0){
			
			return 0;
		}
		int[][] dp =new int[arr.length][aim+1];
		for (int i = 0; i < arr.length; i++) {
			dp[i][0] = 1;
		}
		for (int j = 1; arr[0]*j <= aim; j++) {
			dp[0][arr[0] * j] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				dp[i][j] = dp[i-1][j];
				dp[i][j] += j - arr[i] >=0 ? dp[i][j-arr[i]]:0;
			}
		}
		return dp[arr.length-1][aim];
	}
	
	public int exchangeCoins05(int[] arr, int aim){
		if(arr==null || arr.length ==0 || aim<0){
			
			return 0;
		}
		int[] dp = new int[aim+1];
		for(int j = 0; arr[0]*j <= aim; j++){
			dp[arr[0]*j] = 1;
		}
		for (int i = 1; i < arr.length; i++) {
			for (int j = 1; j <= aim; j++) {
				dp[j] += j - arr[i] >= 0 ? dp[j-arr[i]] : 0;
			}
		}
		return dp[aim];
	}
	
	public static void main(String[] args) {
		int[] arr = {5,2,5,3};
		int aim = 15;
		SolutionOfMinCoins s = new SolutionOfMinCoins();
		System.out.println(s.minCoins04(arr, aim));
	}
}