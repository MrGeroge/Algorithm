package leetcode;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by George on 2017/12/4.
 */
public class SingleNumberLi {
    public int singleNumber(int[] A) {
        Map<Integer,Integer> map=new HashMap<Integer,Integer>();
        if(A.length==0){
            return 0;
        }
        for(Integer i:A){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }
            else{
                map.put(i,1);
            }
        }
        for(Map.Entry entry:map.entrySet()){
            if((Integer)entry.getValue()==1){
                return (Integer) entry.getKey();
            }
            else{
                continue;
            }
        }
        return 0;
    }
}
