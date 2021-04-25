package exercise.level01;
/**
 * 动态规划解决斐波那契数列问题
 * @author tangKID
 *
 */
public class FibonacciSequence {
	//方法1,简单递归
	public int fun1(int n){
		if(n==1 || n==2){
			return 1;
		}
		return fun1(n-1) + fun1(n-2);
	}
	//方法2,循环调用
	public int fun2(int n){
		if(n==1 || n==2){
			return 1;
		}
		int temp = 0;
		int pre = 1;
		int prepre = 1;
		for (int i = 3; i <= n; i++) {
			temp = pre + prepre;
			prepre = pre;
			pre = temp;
		}
		return temp;
	}
	//矩阵m的p次方
	public int[][] matrixPower(int[][] m, int p){
		int[][] res = new int[m.length][m[0].length];
		//先把res设为单位矩阵
		for(int i = 0; i< res.length; i++){
			res[i][i] = 1;
		} 
		int[][] tmp = m;
		for (; p!=0 ; p >>= 1) {
			if((p & 1) != 0){
				res = muliMatrix(res, tmp);
			}
			tmp = muliMatrix(tmp, tmp);
		}
		return res;
	}
	//两个矩阵相乘
	public int[][] muliMatrix(int[][] m1, int[][] m2){
		
		int[][] res = new int[m1.length][m2[0].length];
		
		for (int i = 0; i < m1.length; i++) {
			for (int j = 0; j < m2[0].length; j++) {
				for (int k = 0;  k < m2.length; k++) {
					 res[i][j] += m1[i][k] * m2[k][j]; 
				}
			}
		}
		return res;
	}
	//方法3，矩阵相乘
	public int fun3(int n){
		if(n==1 || n==2){
			return 1;
		}
		int[][] base = {{1,1},{1,0}};
		int[][] res = matrixPower(base, n-2);
		
		return res[0][0] + res[0][1];
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FibonacciSequence fs = new FibonacciSequence();
		System.out.println(fs.fun1(20));
		System.out.println(fs.fun2(20));
		System.out.println(fs.fun3(60));
	}

}
