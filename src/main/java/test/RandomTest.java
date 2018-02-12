package test;

import org.junit.Test;

import java.util.Random;

/**
 * Created by George on 2018/1/9.
 */
public class RandomTest {
    @Test
    public void testRandom(){
        int max=2;
        int min=0;

        Random random = new Random();

        int r = random.nextInt(max)%(max-min+1) + min;
        return;
    }
}
