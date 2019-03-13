import java.util.LinkedList;

/**
 * @Author: 张健
 * @CreateDate: 2019-03-13 15:07
 * @Version: 1.0
 * https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 */
public class LeetCode3 {

    private static int lengthOfLongestSubstring(String s) {
        LinkedList<Character> sets = new LinkedList<Character>();
        int j = 0;  // 保存上次个最大值
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 如果已经含有该元素  将set中截止到c(包括c)的元素删除
            if (sets.contains(c)) {
                // 如果set的size大于j,说明此次的长度大于上次长度
                if (sets.size() > j) {
                    j = sets.size();
                }

                // iterator换成这种while快了5ms
                while (!sets.get(0).equals(c)) {
                    sets.remove(0);
                }
                sets.remove(0);
                sets.add(c);

                /*for (Iterator iter = sets.iterator(); iter.hasNext(); ) {
                    if (!iter.next().equals(c)) {
                        iter.remove();
                    } else {
                        iter.remove();
                        sets.add(c);
                        break;
                    }*/

            } else {
                sets.add(c);
            }
        }
//        return j > sets.size() ? j : sets.size();
        // 三元运算符换成这种快了1ms  但max底层也是三元运算符......
        return Math.max(sets.size(), j);
    }

    public class Solution {
        public int lengthOfLongestSubstring(String s) {
            int n = s.length(), ans = 0;
            int[] index = new int[128]; // current index of character
            // try to extend the range [i, j]
            for (int j = 0, i = 0; j < n; j++) {
                i = Math.max(index[s.charAt(j)], i);
                ans = Math.max(ans, j - i + 1);
                index[s.charAt(j)] = j + 1;
            }
            return ans;
        }
    }
}
