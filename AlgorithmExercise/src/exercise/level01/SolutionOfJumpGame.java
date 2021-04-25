package exercise.level01;

/**
 * ̰�Ĳ���
 * ��Ծ��Ϸ2
 * @author tangKID
 *����: [2,3,1,1,4]
 *���: 2
 *����: �������һ��λ�õ���С��Ծ���� 2��
 *���±�Ϊ 0 �����±�Ϊ 1 ��λ�ã��� 1 ����Ȼ���� 3 ��������������һ��λ�á�
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
	 * ��̬�滮
	 * @return
	 */
	public static int jump03(int[] arr){
		if(arr == null || arr.length == 0){
			return 0;
		}
		int jump = 0; // ����Ŀǰ���˶��ٲ�
		int cur = 0;  // ������jump������Զ�ܵ����λ��
		int next = 0; // ������jump+1������Զ�ܵ����λ��
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
