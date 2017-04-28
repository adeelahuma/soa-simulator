package util;

import java.util.Random;
import java.util.UUID;

/**
 * @author adeelahuma
 * */

public class Utility
{
    public String getId()
    {
        return UUID.randomUUID().toString();
    }

    public int pickRandomIndex(int length)
    {
        return new Random().nextInt(length);
    }

    /**
     * Randomly pick wait time 5 or 10
     * */
    public Integer pickRandomWaitTime()
    {
        return new Random().nextBoolean() ? 5: 10;
    }
}
