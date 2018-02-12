package leetcode;

import java.util.ArrayList;

/**
 * Created by George on 2017/12/5.
 */


public class PalindromePartitioning {

    public ArrayList<ArrayList<String>> partition(String s) {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        if (s.length() == 0) {
            return result;
        }
        if (s.length() == 1) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(s);
            result.add(list);
            return result;
        }

        for (int i = 1; i <= s.length(); i++) {

            String temp = s.substring(0, i);
            //如果是回文串
            if (isPalindrome(temp)) {
                //如果已经到头了
                if (i == s.length()) {

                    ArrayList<String> list = new ArrayList<String>();
                    list.add(temp);
                    result.add(list);

                } else {//如果没到头

                    ArrayList<ArrayList<String>> list = partition(s.substring(i));
                    for (int j = 0; j < list.size(); j++) {

                        ArrayList<String> r = new ArrayList<String>();
                        r.add(temp);
                        r.addAll(list.get(j));

                        result.add(r);

                    }
                }


            } else {//如果不是回文串

                continue;
            }


        }

        return result;

    }

    public boolean isPalindrome(String s) {
        if (s.equals("") || s.length() == 0) {
            return false;
        } else {
            int low = 0;
            int high = s.length() - 1;
            boolean flag = true;
            while (low <= high) {
                if (s.charAt(low) == s.charAt(high)) {
                    low++;
                    high--;
                    continue;
                } else {
                    flag = false;
                    break;
                }
            }
            return flag;
        }
    }
}
