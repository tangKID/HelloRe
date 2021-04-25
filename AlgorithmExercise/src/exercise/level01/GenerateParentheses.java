package exercise.level01;

import java.util.ArrayList;
import java.util.List;

/**
 * Given n pairs of parentheses, 
 * write a function to generate all combinations of well-formed parentheses.
 * @author tangKID
 */
public class GenerateParentheses {
	
	/**
	 * Input: n = 3
	 * Output: ["((()))","(()())","(())()","()(())","()()()"]
	 * 
	 * Input: n = 4
	 * Output: ["(((())))","((()()))","((())())","((()))()","(()(()))",
	 * "(()()())","(()())()","(())(())","(())()()","()((()))",
	 * "()(()())","()(())()","()()(())","()()()()"]
	 * @param n
	 * @return List<String>
	 */
	 public List<String> generateParenthesis(int n) {
	        List<String> combinations = new ArrayList<String>();
	        generateAll(new char[2 * n], 0, combinations);
	        return combinations;
	    }

    public void generateAll(char[] current, int pos, List<String> result) {
        if (pos == current.length) {
            if (valid(current))
                result.add(new String(current));
        } else {
            current[pos] = '(';
            generateAll(current, pos+1, result);
            current[pos] = ')';
            generateAll(current, pos+1, result);
        }
    }

    public boolean valid(char[] current) {
        int balance = 0;
        for (char c: current) {
            if (c == '(') balance++;
            else balance--;
            if (balance < 0) return false;
        }
        return (balance == 0);
    }
	public static void main(String[] args) {
		GenerateParentheses gp = new GenerateParentheses();
		List<String> pa_list  = gp.generateParenthesis(3);
		for (int i = 0; i < pa_list.size(); i++) {
			
			System.out.println(pa_list.get(i));
			
		}
	}

}
