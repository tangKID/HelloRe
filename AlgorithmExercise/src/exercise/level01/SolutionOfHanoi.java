package exercise.level01;
/**
 * 动态规划解法
 * 汉诺塔，实现函数打印最优移动轨迹
 * @author tangKID
 *
 */
public class SolutionOfHanoi {
	
	/**
	 * 算法思路
	 * n-1 A to B
	 * n A to C
	 * n-1 B to C
	 * @param n
	 */
	public void hanoi(int n){
		func(n, "left", "mid", "right");
	}
	
	public void func(int n, String from, String mid, String to){
		if(n == 1){
			System.out.println("move from " + from + " to " + to);
		}else{
			func(n-1, from, to, mid);
			func(1, from, mid, to);
			func(n-1, mid, from, to);
		}
	}
	/**
	 * 进阶题目
	 * 给定数组arr,其中只含1,2,3,代表所有圆盘目前的状态，1代表左柱，2代表中柱，3代表右柱
	 * arr[i]代表第i+1个圆盘的位置。
	 * @return
	 */
	public int step1(int[] arr){
		if(arr == null || arr.length == 0){
			return -1;
		}
		return process(arr, arr.length-1, 1, 2, 3);
	}
	public int process(int[] arr, int i, int from, int mid, int to){
		if(i == -1){
			return 0;
		}
		if(arr[i] != from && arr[i] != to){
			return -1;
		}
		if(arr[i] == from){
			return process(arr, i-1, from, to, mid);
		}else{
			int rest = process(arr, i-1, mid, from, to);
			if(rest == -1){
				return -1;
			}
			return (1 << i) + rest;
		}
	}
	public int step02(int[] arr){
		if(arr == null || arr.length == 0){
			return -1;
		}
		int from = 1;
		int mid = 2;
		int to = 3;
		int i = arr.length - 1;
		int res = 0;
		int temp = 0;
		
		while(i >= 0){
			if(arr[i] != from && arr[i] != to){
				return -1;
			}
			if(arr[i] == to){
				res += 1 << i;
				temp = from;
				from = mid;
			}else{
				temp = to;
				to = mid;
			}
			mid = temp;
			i--;
		}
		return res;
	}
}
