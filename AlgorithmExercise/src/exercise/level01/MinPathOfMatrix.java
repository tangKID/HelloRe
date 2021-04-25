package exercise.level01;

/**
 * 
 * @author tangKID
 *
 */
public class MinPathOfMatrix {
	
	public int minPathSum(int[][] m){
		
		if(m==null || m.length==0 || m[0] ==null || m[0].length == 0){
			return 0;
		}
		int [][] dp = new int[m.length][m[0].length];
		dp[0][0] = m[0][0];
		for(int i = 1; i < m.length; i++) {
			dp[0][i] = dp[0][i-1] + m[0][i];
		}
		for(int i = 1; i < m[0].length; i++){
			dp[i][0] = dp[i-1][0] + m[i][0];
		}
		for (int i = 1; i < m.length; i++) {
			for (int j = 1; j < m[0].length; j++) {
				dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1])+ m[i][j];
			}
		}
		return dp[m.length-1][m[0].length-1];
	}
	
	
	public int minPathSum02(int[][] m){
		if(m==null || m.length==0 || m[0] == null || m[0].length == 0){
			return 0;
		}
		int more = Math.max(m.length, m[0].length);
		int less = Math.min(m.length, m[0].length);
		boolean row_more = more == m.length; //行是否大于列
		int[] dp = new int[less]; //辅助数组的大小是行和列中较小的一个
		dp[0] = m[0][0];
		for (int i = 1; i < less; i++) {
			dp[i] = dp[i-1] + (row_more? m[0][i]:m[i][0]); 
		}
		for (int i = 1; i < more; i++) {
			dp[0] = dp[0] + (row_more? m[i][0]:m[0][i]);
			for (int j = 1; j < less; j++) {
				dp[j] = Math.min(dp[j-1], dp[j]) + (row_more? m[i][j]:m[j][i]); 
			}
		}
		return dp[less-1];
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int [][] m ={{1,3,5,9},
					 {8,1,3,4},
					 {5,0,6,1},
					 {8,8,4,0}
					 };
		MinPathOfMatrix mpm = new MinPathOfMatrix();
		//System.out.print(mpm.minPathSum(m));
		System.out.print(mpm.minPathSum02(m));

	}

}
