package util;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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


    public void logOutputForGraph(StringBuffer sb_m, StringBuffer sb_nm)
    {
        ///spit out to file for graphs
        String writeFileName_M = "./trust_score_M.csv";
        String writeFileName_NM = "./trust_score_NM.csv";

        try
        {

            FileWriter fWriter = null;
            BufferedWriter writer = null;

            ///write file for Malicious node
            fWriter = new FileWriter(writeFileName_M);
            writer = new BufferedWriter(fWriter);

            writer.write("time_index,TS");
            writer.newLine();

            writer.write(sb_m.toString());
            writer.close();
            ////

            ///write file for Non- Malicious node
            fWriter = new FileWriter(writeFileName_NM);
            writer = new BufferedWriter(fWriter);

            writer.write("time_index,TS");
            writer.newLine();

            writer.write(sb_nm.toString());
            writer.close();
            ////

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        ///
    }
}
