package exercise.level01;

/**
 * 贪心策略
 * 跳跃游戏2
 * @author tangKID
 *输入: [2,3,1,1,4]
 *输出: 2
 *解释: 跳到最后一个位置的最小跳跃数是 2。
 *从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
 */
public class SolutionOfJumpGame {
	//method01
	public int jump(int[] nums) {
		int count = 0;
		if(nums.length == 1){
			return count;
		}
		for (int i = nums.length-1; i >= 0 ; i--) {
			
			for (int j = 0; j < i; j++) {
				int len = i - j;
				int maxlen = nums[j];
				if(maxlen > len && len >1){
					i -= (len -1);
					break;
				}
			}
			count++;
		}
		return count;
	}
	//methode02
	public int jump02(int[] nums){
		int count = 0;
		int maxDis = 0;
		int end = 0;
		if(nums.length == 1){
			return count;
		}
		for (int i = 0; i < nums.length-1; i++) {
			maxDis = Math.max(maxDis, i+nums[i]);
			if(i==end){
				end = maxDis;
				count++;
			}
		}
		return count;
	}
	/**
	 * 动态规划
	 * @return
	 */
	public static int jump03(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int jump = 0; // 代表目前跳了多少步
		int cur = 0;  // 代表跳jump步，最远能到达的位置
		int next = 0; // 代表跳jump+1步，最远能到达的位置
		for (int i = 0; i < arr.length; i++) {
			if(cur < i){
				jump++;
				cur = next;
			}
			next = Math.max(next, i + arr[i]);
		}
		return jump;
	} 
	public static void main(String[] args) {
		int[] arr = {5,1,1,1,1,1,1};
		System.out.print(jump03(arr));
	}
}
