import actors.CHServiceProvider;
import actors.CHServiceRequester;
import actors.CentralHub;
import util.Utility;

import java.util.List;
import java.util.Set;

/**
 * @author adeelahuma
 * */

 public class Simulator
{
    private static CentralHub centralHub = new CentralHub();
    private static Utility util = new Utility();

    public static void main(String[] args)
    {
        System.out.println("Starting Simulation...");

        //TODO: Run 100 times for each SR

        Integer requestedWaitTime = 5;

        /**
         * Simulation runs for all SR
         *  *SP are restaurants
         *  *SR are people
         *  Pre-Simulation Step:
         *      *creating SP
         *      *creating SR
         *      *picking x malicious nodes randomly
         *  For all SR do following:
         *  Step 1: SR queries SP based on "waitTime" criteria
         *  Step 2: SR picks SR based on highest score
         *  Step 3: If malicious node fake waitTime
         *  Step 4: log feedback about SP, other SR's if evaluating SR is trustworthy else ignore
         * */

        System.out.println("Creating Service Providers...");
        centralHub.createServiceProviders();

        System.out.println("Creating Service Providers...");
        centralHub.createServiceRequesters();

        System.out.println("Tagging Service Providers as malicious...");
        Set<String> maliciousSP = centralHub.pickMaliciousSP(5);


        for(CHServiceRequester serviceRequester :centralHub.serviceRequesterMap.values())
        {
            /**
             * Step-1: Query central hub service providers with a waitTime=x
             * */
            List<CHServiceProvider> serviceProviders =  centralHub.queryServiceProvider(requestedWaitTime);

            /**
             * Step-2:
             * */
            //Picking SP based on highest TS
            Double tempSpTrustSCore = 0.0;
            CHServiceProvider pickedSP = null;
            for(CHServiceProvider sp : serviceProviders)
            {
                if(sp.getTrustScore()> tempSpTrustSCore)
                {
                    tempSpTrustSCore = sp.getTrustScore();
                    pickedSP = sp;

                }
            }
            CHServiceProvider servingSP = pickedSP;

            //Picking SP randomly
            /*int spIndex = util.pickRandomIndex(serviceProviders.size());
            CHServiceProvider servingSP = serviceProviders.get(spIndex);*/


            /**
             * Step-3:
             * */
            int actualWaitTime = 0;
            int feedback = 0;
            if(maliciousSP.contains(servingSP.getId())) //serving SP is malicious
            {
                actualWaitTime = servingSP.getWaitTime()+10;
                feedback= -1;
            }
            else
            {
                actualWaitTime = servingSP.getWaitTime()-1;
                feedback =1;
            }

            /**
             * Step-4: If Service requester is trust-worthy log the feedback about service provider
             * and service requesters
             * */
            if(serviceRequester.getTrustScore() >= CentralHub.repScoreThreshold)
            {
                 //log feedback about SP
                 centralHub.logSPFeedBack(feedback, actualWaitTime, servingSP);
                //log feedback about SR
                centralHub.logSRFeedBack(feedback, servingSP.getSpVisitors(), actualWaitTime);

            }
        }


         System.out.println("Ending Simulation...");

    }

}
