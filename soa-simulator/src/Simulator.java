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

        System.out.println("Creating Service Providers...");
        centralHub.createServiceProviders();

        System.out.println("Creating Service Providers...");
        centralHub.createServiceRequesters();

        System.out.println("Tagging Service Providers as malicious...");
        Set<String> maliciousSP = centralHub.pickMaliciousSP(5);

        System.out.println("Starting Simulation...");

        /**
         * Simulation step
         *
         * 1. for each service requester query central hub for service providers matching waitTime == x
         * 2. service requester randomly picks a service provider
         * 3. if (SP is malicious)respond after y = x+10 time else respond in y = x-1 time
         * 4. SR depending on y give feedback to CH
         * 5. CH adjust TS of SP
         * 6.
         * 7.
         * 8.
         * 9.
         * */

        //TODO: Run 100 times for each SR

        for(CHServiceRequester serviceRequester :centralHub.serviceRequesterMap.values())
        {
            /**
             * Step-1: Query central hub service providers with a waitTime=x
             * */
            Integer requestedWaitTime = 5; //FIXME: change it randomly
            List<CHServiceProvider> serviceProviders =  centralHub.queryServiceProvider(requestedWaitTime);

            /**
             * Step-2:
             * */
            int spIndex = util.pickRandomIndex(serviceProviders.size());
            CHServiceProvider servingSP = serviceProviders.get(spIndex);

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
