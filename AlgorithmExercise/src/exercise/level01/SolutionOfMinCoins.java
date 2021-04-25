package exercise.level01;

/**
 * @author tangKID
 * 
 */
public class SolutionOfMinCoins {
	
	/**
	 * ��Ǯ�����ٻ�����
	 * @param arr
	 * @param aim
	 * @return
	 */
	public int minCoins(int [] arr, int aim){
		
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		int max = Integer.MAX_VALUE;
		// dp[i][j]�����������ʹ��arr[0...i]coins�������,���j�������С����
		int[][] dp = new int[arr.length][aim+1];
		// dp�ĵ�һ��ȫ����ֵΪ0,�ͳ�ʼ��ֵ��ȣ����Բ���Ҫ����
		/**
		 *  ��һ��forѭ����dp�ĵ�һ�еļ���
		 *  ��ʾֻ��ʹ��arr[0]coin������£��ܹ��ҿ���Ǯ����
		 *  ����j������arr[0]�ı���
		 *  ���ǵ���һ������������ֵΪj-arr[0]=0
		 *  �ڴ�ʱdp[0][j] = max
		 *  Ȼ�� dp[0][j] = dp[0][j-arr[0]] + 1;
		 *  ���ѳ�arr[0]����֮���ֵ��ֵΪ��max���ְ���Ҫ��ŵ�λ�óɹ���ֵ
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
	// ������Ŀ
	public int minCoins03(int[] arr, int aim){
		
		if(arr == null || arr.length == 0 || aim < 0){
			return -1;
		}
		
		int n = arr.length;
		int max = Integer.MAX_VALUE;
		//dp������ʹ��arr[0...i]coins�������(ÿ��ֵ������һ�Ż���)�����j�������С������
		int[][] dp = new int[n][aim+1];
		for (int j = 1; j <= aim; j++) {
			dp[0][j] = max;
		}
		if(arr[0] <= aim){
			dp[0][arr[0]] = 1;
		}
		
		/**
		 * ���մ����ң����ϵ��µļ���
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
		//���ϵ���
		for (int i = 1; i < n; i++) {
			/**
			 * �ڶ���ѭ���ǴӺ���ǰ��,j>j-arr[i],
			 * ��Ϊdp[i][j]��Ҫ�õ���һ����dp[i-1][j-arr[i]]��ֵ��
			 * �Ӻ���ǰ���Ͳ�����ָ��ǵ�����
			 * ����ǰ����dp[j-arr[i]]���Ѿ���dp[i][j-arr[i]]������
			 * 
			 */
			for (int j = aim; j > 0; j--) {
				leftup = max;
				if(j-arr[i] >= 0 && dp[j-arr[i]] != max){
					leftup = dp[j-arr[i]] + 1; //dp[i-1][j-arr[i]] 
				}
				dp[j] = Math.min(leftup, dp[j]); //�����dp[j]����dp[i-1][j]
			}
		}
		return dp[aim]!=max? dp[aim]:-1;
	}
	
	/**
	 * ��Ǯ�ķ�����
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
	 * arr[index....n]��Щ��ֵ��Ǯ���aim
	 * @param arr
	 * @param index
	 * @param aim
	 * @return
	 */
	public int process1(int[] arr, int index, int aim){
		int res = 0;
		if(index== arr.length){
			res = aim==0? 1:0; //ֻ���ڵݹ�ײ���aim=0,�������ǻ�Ǯ��һ�ַ�����
		} else{
			for (int i = 0; arr[index]*i <=aim; i++) {
				res+= process1(arr, index+1, aim- arr[index]*i);
			}
		}
		return res;
	}
	//���仯��������
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