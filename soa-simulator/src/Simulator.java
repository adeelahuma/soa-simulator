import actors.CHServiceProvider;
import actors.CHServiceRequester;
import actors.CentralHub;
import actors.SPVisitors;
import util.Utility;

import java.util.Set;

/**
 * @author adeelahuma
 * */

 public class Simulator
{
    private static CentralHub centralHub = new CentralHub();
    public static void main(String[] args)
    {
        System.out.println("Starting trust based Simulation...");

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
        for(int i = 0; i < 2 ; i++)
        {
            for(CHServiceRequester serviceRequester :centralHub.serviceRequesterMap.values())
            {

                //Integer requestedWaitTime = util.pickRandomWaitTime();
                /**
                 * Query central hub service providers with a waitTime=x
                 * */
                //List<CHServiceProvider> serviceProviders =  centralHub.queryServiceProvider(requestedWaitTime);

                /**
                 * Picking SP based on highest TS
                 * */
                
                //CHServiceProvider servingSP = GetBestServiceProvider_NoTrust();
                CHServiceProvider servingSP = GetBestServiceProvider_WithSPTrustOnly();
                //CHServiceProvider servingSP = GetBestServiceProvider_WithSPTrustAndWTClaims();

                //Picking SP randomly
	            /*int spIndex = util.pickRandomIndex(serviceProviders.size());
	            CHServiceProvider servingSP = serviceProviders.get(spIndex);*/


                /**
                 * fake waitTime for malicious node
                 * */
                Double actualWaitTime = 0.00;
                int feedback = 0;
                if(maliciousSP.contains(servingSP.getId())) //serving SP is malicious
                {
                    actualWaitTime = servingSP.getActualWaitTime();
                    feedback= -1;
                }
                else
                {
                    actualWaitTime = servingSP.getActualWaitTime();//- 1;
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
                        actualWaitTime = servingSP.getActualWaitTime()+ 10;
                    }
                    else
                    {
                        servingSP.setAlpha(servingSP.getAlpha() + 1); // increment positive interaction
                        actualWaitTime = servingSP.getActualWaitTime();
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

                servingSP.getSpVisitors().add(new SPVisitors(serviceRequester.getId(), actualWaitTime, servingSP.getAdvertisedWaitTime(), serviceRequester.getIsMalicious(), serviceRequester.getFeedbacks()));
            }
        }

        for(CHServiceRequester sr : centralHub.serviceRequesterMap.values()){
        	int totalPositiveFB = 0;
        	for(Boolean fb : sr.getFeedbacks()){
        		if(fb) totalPositiveFB++;
        	}
        	System.out.println("SR Name: " + sr.getServiceRequesterName() + ", Is Malicious: " + sr.getIsMalicious() + ", Total Feedbacks: " + sr.getFeedbacks().size()
        						+ ", Reputation Score: " + ((sr.getFeedbacks() != null && sr.getFeedbacks().size() > 0)? totalPositiveFB/sr.getFeedbacks().size() : 0));
        }
        for(CHServiceProvider sp : centralHub.serviceProviderMap.values()){
        	System.out.println("SP Name:" + sp.getServiceProviderName() + ", Is Malicious:" + sp.getIsMalicious()
        						+ ", Trust Score: " + sp.getTrustScore() + ", Wait Time: " + sp.getActualWaitTime());
        	for(SPVisitors spv : sp.getSpVisitors()){
        		System.out.println("\t\tSR Id: " +spv.getSRId() + ", is Malicious: " + spv.getIsMalicious() + ", Wait Time:" + spv.getWaitTimeLogged() 
        							+ ", Advertised Wait Time: " + spv.getAdvertisedWaitTime());
        	}
        }
         System.out.println("Ending trust based Simulation...");

    }
    
    private static CHServiceProvider GetBestServiceProvider_NoTrust(){
    	CHServiceProvider selectedSP = null;
        Double tempLeastWaitTime = - 1.00;
    	for(CHServiceProvider sp : centralHub.serviceProviderMap.values()){
    		
    		if(sp.getAdvertisedWaitTime() < tempLeastWaitTime || tempLeastWaitTime == -1.00){
    			tempLeastWaitTime = sp.getAdvertisedWaitTime();
    			selectedSP = sp;
    		}    	
    	}
    	return selectedSP;
    	
    }
    
    private static CHServiceProvider GetBestServiceProvider_WithSPTrustOnly(){
    	CHServiceProvider selectedSP = null;
    	double tempSpWaitTimeCalculated = -1.00;        
    	for(CHServiceProvider sp : centralHub.serviceProviderMap.values()){
        	double waitTimeWeight = 0.00;
        	double avgWeightedClaimedTime = 0.00;
        	//double actualWaitTime = sp.getActualWaitTime();
        	//double advertisedWaitTime = sp.getAdvertisedWaitTime();
        	//double trustScore = sp.getTrustScore();
        	double waitTimeTS = sp.getAdvertisedWaitTime() * (2.0 - sp.getTrustScore());
        	
            if(waitTimeTS < tempSpWaitTimeCalculated || tempSpWaitTimeCalculated == -1.00)
            {
            	tempSpWaitTimeCalculated = waitTimeTS;
                selectedSP = sp;
            }
    		   	
    	}
    	return selectedSP;    	
    }
    
    private static CHServiceProvider GetBestServiceProvider_WithSPTrustAndWTClaims(){
    	CHServiceProvider selectedSP = null;
    	Double tempSpWaitTimeCalculated = -1.00;        
    	for(CHServiceProvider sp : centralHub.serviceProviderMap.values()){
        	double waitTimeWeight = 0.00;
        	double avgWeightedClaimedTime = 0.00;
        	if(sp.getSpVisitors().size() > 0){
        		//This is not the best way to do because sp.GetSpVisitors() will be iterated twice
        		//but I don't know a better way :(
        		
        		//First get total weighted reputation score for all WTs
        		double totalWeightedReputation = 0.0;
        		int totalCredibility = 0;
        		for(SPVisitors spv : sp.getSpVisitors()){
        			totalCredibility += spv.getFeedbacks().size();
        			if(spv.getFeedbacks().size() < 1)
        				totalCredibility += 1;
        			//determine total positive feedbacks towards the SR
        			int totalPositiveFb = 0; 
        			for(Boolean fb : spv.getFeedbacks()){
        				if (fb)
        					totalPositiveFb++;
        			}
        			int totalFeedbacks = 1;//if there are no feedbacks for this WT then consider it same as 1
        			if(spv.getFeedbacks().size() > 0){
        				totalFeedbacks = spv.getFeedbacks().size();
        			}
        			//determine reputation rating of this WT as beta reputation score (
        			double repRating = (double) totalPositiveFb / (double) totalFeedbacks;
        			totalWeightedReputation += (spv.getFeedbacks().size() < 1 ? 1 : spv.getFeedbacks().size()) * repRating;
        		}
        		//Next determine the weighted sum of WT claimed wait times
        		double totalWeightedClaimedTime = 0.00;
        		for(SPVisitors spv : sp.getSpVisitors()){
        			double test = spv.getWaitTimeLogged();
        			totalWeightedClaimedTime += spv.getWaitTimeLogged() * (spv.getFeedbacks().size() < 1 ? 1: spv.getFeedbacks().size()) / totalWeightedReputation;
        		}
        		
        		avgWeightedClaimedTime = totalWeightedClaimedTime / sp.getSpVisitors().size();
        		
        		waitTimeWeight = 0.5 * totalWeightedReputation/(sp.getSpVisitors().size() * totalCredibility);
        	}
        	//double actualWaitTime = sp.getActualWaitTime();
        	//double advertisedWaitTime = sp.getAdvertisedWaitTime();
        	//double trustScore = sp.getTrustScore();
        	double waitTimeTS = sp.getAdvertisedWaitTime() * (2.0 - sp.getTrustScore());
        	
        	double waitTimeCalculated = ((1- waitTimeWeight) * waitTimeTS) + (waitTimeWeight * avgWeightedClaimedTime);
        	
            if(waitTimeCalculated < tempSpWaitTimeCalculated || tempSpWaitTimeCalculated == -1.00)
            {
            	tempSpWaitTimeCalculated = waitTimeCalculated;
                selectedSP = sp;
            }    		   	
    	}
    	return selectedSP;    	
    }
}
