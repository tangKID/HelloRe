package exercise.level01;
/**
 * 最长公共子串
 * @author tangKID
 *
 */
public class SolutionOfLCST {
	
	public int[][] getdp(char[] str1, char[] str2){
		
		//dp[i][j]是指以str1[i]str2[j]当做公共子串的最后一个字符情况下，最长公共子串的长度
		int[][] dp =new int[str1.length][str2.length];
		
		for (int i = 0; i < str1.length; i++) {
			if(str1[i] == str2[0]){
				dp[i][0] = 1;
			}
		}
		for (int j = 0; j < str2.length; j++) {
			if(str1[0] == str2[j]){
				dp[0][j] = 1;
			}
		}
		for (int i = 1; i < str1.length; i++) {
			for (int j = 1; j < str2.length; j++) {
				if(str1[i] == str2[j]){
					dp[i][j] = dp[i-1][j-1] + 1;
				}
			}
		}
		return dp;
	}
	
	public String lcts1(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() == 0 || str2.length() ==0){
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int[][] dp =getdp(chs1, chs2);
		int end = 0;
		int max = 0;
		for (int i = 0; i < chs1.length; i++) {
			for (int j = 0; j < chs2.length; j++) {
				if(dp[i][j] > max){
					end = i;
					max = dp[i][j];
				}
			}
		}
		return str1.substring(end - max + 1, end + 1);
	}
	public String lcts2(String str1, String str2){
		if(str1 == null || str2 == null || str1.length() == 0 || str2.length() ==0){
			return "";
		}
		char[] chs1 = str1.toCharArray();
		char[] chs2 = str2.toCharArray();
		int row = 0; //斜线开始位置的行
		int col = chs2.length -1; //斜线开始位置的列
		int end = 0; //记录最大长度
		int max = 0; // 最大长度，子串结尾位置
		while(row < chs1.length){
			int i = row;
			int j = col;
			int len = 0;
			//从i,j开始向右下方遍历
			while(i < chs1.length && j < chs2.length){
				if(chs1[i] != chs2[j]){
					len = 0;
				}else{
					len++;
				}
				if(len > max){
					max = len;
					end = i;
				}
				i++;
				j++;
			}
			if(col > 0){ //列左移
				col--;
			}else{ //行下移
				row++;
			}
		}
		return str1.substring(end - max +1, end + 1);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
