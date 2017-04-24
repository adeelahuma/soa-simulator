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
}
