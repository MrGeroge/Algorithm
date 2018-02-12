package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

/**
 * Created by George on 2017/12/2.
 */
public class MaxPointsOnALine {
    public static void main(String[] args){
        MaxPointsOnALine mp=new MaxPointsOnALine();
        Point[] points=new Point[]{new Point(1,1),new Point(1,1),new Point(1,1)};
        System.out.println(mp.maxPoints(points));
    }
    public int maxPoints(Point[] points) {
        if(points.length<=2){
            return points.length;
        }
        else { //至少三个点
            int max = 2;
            for (int i = 0; i < points.length; i++) {
                int same = 0;
                int temp = 1;
                for (int j = i + 1; j < points.length; j++) { //i,j构成直线
                    if (points[i].x == points[j].x && points[i].y == points[j].y) {//i,j为相同点
                        same++;
                    } else {
                        temp = 2;
                        for (int t = j + 1; t < points.length; t++) { //判断j后有多少点在同一直线上
                            if ((points[i].y - points[j].y) * (points[j].x - points[t].x) == (points[i].x - points[j].x) * (points[j].y - points[t].y)) {//斜率相同，三点共线
                                temp++;
                            }
                        }
                    }
                    if (max < same + temp) {
                        max = same + temp;
                    }
                    temp = 1;
                }
            }
            return max;
        }
    }
}
class Point {
      int x;
      int y;
      Point() { x = 0; y = 0; }
      Point(int a, int b) { x = a; y = b; }
  }
