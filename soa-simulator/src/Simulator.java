import actors.CHServiceProvider;
import actors.CHServiceRequester;
import actors.CentralHub;
import actors.SPVisitors;
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
        System.out.println("Starting trust based Simulation...");

        //TODO: Run 100 times for each SR


        /**
         * Simulation runs for all SR
         *  *SP are restaurants
         *  *SR are people
         *  Pre-Simulation Step:
         *      *creating SP
         *      *creating SR
         *      *picking x malicious SP randomly
         *      #picking y malicious SR randomly
         *  For all SR do following:
         *  Step 1: Randomly pick wait time
         *  Step 2: SR queries SP based on "waitTime" criteria
         *  Step 3: SR picks SR based on highest trust score
         *  Step 4: If malicious SP fake waitTime
         *  Step 5: If malicious SR collude feedback ( reverse feedback and log wrong waitTime)
         *  Step 6: log feedback about SP, other SR's if evaluating SR is trustworthy else ignore
         * */

        System.out.println("Creating Service Providers...");
        centralHub.createServiceProviders();

        System.out.println("Creating Service Providers...");
        centralHub.createServiceRequesters();

        System.out.println("Tagging Service Providers as malicious...");
        Set<String> maliciousSP = centralHub.pickMaliciousSP(5);

        System.out.println("Tagging Service Requesters as malicious...");
        Set<String> maliciousSR = centralHub.pickMaliciousSR(5);

        /**
         * Run Simulation x times
         * */
        for(int i = 0; i < 10 ; i++)
        {
            for(CHServiceRequester serviceRequester :centralHub.serviceRequesterMap.values())
            {

                Integer requestedWaitTime = util.pickRandomWaitTime();
                /**
                 * Query central hub service providers with a waitTime=x
                 * */
                List<CHServiceProvider> serviceProviders =  centralHub.queryServiceProvider(requestedWaitTime);

                /**
                 * Picking SP based on highest TS
                 * */

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
                 * fake waitTime for malicious node
                 * */
                Integer actualWaitTime = 0;
                int feedback = 0;
                if(maliciousSP.contains(servingSP.getId())) //serving SP is malicious
                {
                    actualWaitTime = servingSP.getWaitTime()+ 10;
                    feedback= -1;
                }
                else
                {
                    actualWaitTime = servingSP.getWaitTime();//- 1;
                    feedback = 1;
                }

                /**
                 *  For malicious SR collude information ( reverse feedback and log wrong waitTime)
                 * */
                if(maliciousSR.contains(serviceRequester.getId()))
                {
                    if(feedback == 1)
                    {
                        servingSP.setBeta(servingSP.getBeta()+ 1); // increment negative interaction
                        actualWaitTime = servingSP.getWaitTime()+ 10;
                    }
                    else
                    {
                        servingSP.setAlpha(servingSP.getAlpha() + 1); // increment positive interaction
                        actualWaitTime = servingSP.getWaitTime();
                    }

                }
                else
                {
                    if(feedback == 1)
                    {
                        servingSP.setAlpha(servingSP.getAlpha() + 1); // increment positive interaction
                    }
                    else
                    {
                        servingSP.setBeta(servingSP.getBeta()+ 1); // increment negative interaction
                    }
                }

                /**
                 * If Service requester is trust-worthy log the feedback about service provider
                 * and service requester
                 * */
                if(serviceRequester.getReputationScore() >= CentralHub.repScoreThreshold)
                {
                    //log feedback about SP
                    centralHub.logSPFeedBack(feedback, actualWaitTime, servingSP);
                    //log feedback about SR
                    centralHub.logSRFeedBack(feedback, servingSP.getSpVisitors(), actualWaitTime);
                }

                servingSP.getSpVisitors().add(new SPVisitors(serviceRequester.getId(), actualWaitTime));
            }
        }

         System.out.println("Ending trust based Simulation...");

    }

}
