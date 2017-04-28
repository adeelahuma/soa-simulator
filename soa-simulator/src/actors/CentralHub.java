package actors;

import util.Utility;
import java.util.*;

/**
 * @author adeelahuma
 * */

public class CentralHub
{
    public static final Double initialTrustScore = 0.5;
    public static final Double waitTimeThreshold = 5.0; // 5 minutes
    public static final Double repScoreThreshold = 0.5; //reputation score of SR
    public static final Double trustDecayFactor = 0.1;

    //added to normalize reputation score between 0 and 1
    private Double maxReputationScore= 1.0;
    private Double minReputationScore= 0.0;

    Utility util = new Utility();

    //key = serviceProviderId
    //value = CH-Service provider
    public Map<String, CHServiceProvider> serviceProviderMap = new HashMap<String, CHServiceProvider>();

    //key = serviceRequesterId
    //value = CH-Service Requester
    public Map<String, CHServiceRequester> serviceRequesterMap = new HashMap<String, CHServiceRequester>();

    /**
     *  create service providers with initial trust score of 0.5
     *  actualWaitTime is updated based on feed back from SPs
     * */
    public void createServiceProviders()
    {
        CHServiceProvider sp1 = new CHServiceProvider(util.getId(), "BurgerKing", 5,initialTrustScore, null);
        CHServiceProvider sp2 = new CHServiceProvider(util.getId(), "Burger7", 5, initialTrustScore, null);
        CHServiceProvider sp3 = new CHServiceProvider(util.getId(), "Burger21", 10, initialTrustScore, null);
        CHServiceProvider sp4 = new CHServiceProvider(util.getId(), "KFC", 10, initialTrustScore, null);
        CHServiceProvider sp5 = new CHServiceProvider(util.getId(), "Chick-fill-A", 10, initialTrustScore, null);
        CHServiceProvider sp6 = new CHServiceProvider(util.getId(), "Panera", 5, initialTrustScore, null);
        CHServiceProvider sp7 = new CHServiceProvider(util.getId(), "Sweetgreen", 5, initialTrustScore, null);
        CHServiceProvider sp8 = new CHServiceProvider(util.getId(), "BasilLeaf", 10, initialTrustScore, null);
        CHServiceProvider sp9 = new CHServiceProvider(util.getId(), "Busara", 5, initialTrustScore, null);
        CHServiceProvider sp10 = new CHServiceProvider(util.getId(), "MidTown Kabob", 5, initialTrustScore, null);

        serviceProviderMap.put(sp1.getId(), sp1);
        serviceProviderMap.put(sp2.getId(), sp2);
        serviceProviderMap.put(sp3.getId(), sp3);
        serviceProviderMap.put(sp4.getId(), sp4);
        serviceProviderMap.put(sp5.getId(), sp5);
        serviceProviderMap.put(sp6.getId(), sp6);
        serviceProviderMap.put(sp7.getId(), sp7);
        serviceProviderMap.put(sp8.getId(), sp8);
        serviceProviderMap.put(sp9.getId(), sp9);
        serviceProviderMap.put(sp10.getId(), sp10);
    }

    public void createServiceRequesters()
    {
        CHServiceRequester sr1 = new CHServiceRequester(util.getId(),"Jane Doe", initialTrustScore);
        CHServiceRequester sr2 = new CHServiceRequester(util.getId(),"Moe Moe", initialTrustScore);
        CHServiceRequester sr3 = new CHServiceRequester(util.getId(),"Humpty Dumpty", initialTrustScore);
        CHServiceRequester sr4 = new CHServiceRequester(util.getId(),"Johny Depp", initialTrustScore);
        CHServiceRequester sr5 = new CHServiceRequester(util.getId(),"John Bracklaw", initialTrustScore);
        CHServiceRequester sr6 = new CHServiceRequester(util.getId(),"Jenna Marvelous", initialTrustScore);
        CHServiceRequester sr7 = new CHServiceRequester(util.getId(),"Rick Roy", initialTrustScore);
        CHServiceRequester sr8 = new CHServiceRequester(util.getId(),"Tommy Boy", initialTrustScore);
        CHServiceRequester sr9 = new CHServiceRequester(util.getId(),"Mimi Lee", initialTrustScore);
        CHServiceRequester sr10 = new CHServiceRequester(util.getId(),"Mona Lisa", initialTrustScore);
        CHServiceRequester sr11 = new CHServiceRequester(util.getId(),"Don Rickles", initialTrustScore);
        CHServiceRequester sr12 = new CHServiceRequester(util.getId(),"Enrique Iglesis", initialTrustScore);
        CHServiceRequester sr13 = new CHServiceRequester(util.getId(),"Bruno Mars", initialTrustScore);
        CHServiceRequester sr14 = new CHServiceRequester(util.getId(),"Charming Charlie", initialTrustScore);
        CHServiceRequester sr15 = new CHServiceRequester(util.getId(),"Damon Salvatore", initialTrustScore);
        CHServiceRequester sr16 = new CHServiceRequester(util.getId(),"Elena Gilbert", initialTrustScore);
        CHServiceRequester sr17 = new CHServiceRequester(util.getId(),"Elena Gilmore", initialTrustScore);
        CHServiceRequester sr18 = new CHServiceRequester(util.getId(),"Morse Gilbert", initialTrustScore);
        CHServiceRequester sr19 = new CHServiceRequester(util.getId(),"James Bond", initialTrustScore);
        CHServiceRequester sr20 = new CHServiceRequester(util.getId(),"Johny Depp", initialTrustScore);
        CHServiceRequester sr21 = new CHServiceRequester(util.getId(),"Jeff Moose", initialTrustScore);
        CHServiceRequester sr22 = new CHServiceRequester(util.getId(),"JJ", initialTrustScore);
        CHServiceRequester sr23 = new CHServiceRequester(util.getId(),"CC", initialTrustScore);
        CHServiceRequester sr24 = new CHServiceRequester(util.getId(),"AH", initialTrustScore);
        CHServiceRequester sr25 = new CHServiceRequester(util.getId(),"BS", initialTrustScore);
        CHServiceRequester sr26 = new CHServiceRequester(util.getId(),"MS", initialTrustScore);
        CHServiceRequester sr27 = new CHServiceRequester(util.getId(),"KK", initialTrustScore);
        CHServiceRequester sr28 = new CHServiceRequester(util.getId(),"LL", initialTrustScore);
        CHServiceRequester sr29 = new CHServiceRequester(util.getId(),"HH", initialTrustScore);
        CHServiceRequester sr30 = new CHServiceRequester(util.getId(),"HM", initialTrustScore);

        serviceRequesterMap.put(sr1.getId(), sr1);
        serviceRequesterMap.put(sr2.getId(), sr2);
        serviceRequesterMap.put(sr3.getId(), sr3);
        serviceRequesterMap.put(sr4.getId(), sr4);
        serviceRequesterMap.put(sr5.getId(), sr5);
        serviceRequesterMap.put(sr6.getId(), sr6);
        serviceRequesterMap.put(sr7.getId(), sr7);
        serviceRequesterMap.put(sr8.getId(), sr8);
        serviceRequesterMap.put(sr9.getId(), sr9);
        serviceRequesterMap.put(sr10.getId(), sr10);
        serviceRequesterMap.put(sr11.getId(), sr11);
        serviceRequesterMap.put(sr12.getId(), sr12);
        serviceRequesterMap.put(sr13.getId(), sr13);
        serviceRequesterMap.put(sr14.getId(), sr14);
        serviceRequesterMap.put(sr15.getId(), sr15);
        serviceRequesterMap.put(sr16.getId(), sr16);
        serviceRequesterMap.put(sr17.getId(), sr17);
        serviceRequesterMap.put(sr18.getId(), sr18);
        serviceRequesterMap.put(sr19.getId(), sr19);
        serviceRequesterMap.put(sr20.getId(), sr20);
        serviceRequesterMap.put(sr21.getId(), sr21);
        serviceRequesterMap.put(sr22.getId(), sr22);
        serviceRequesterMap.put(sr23.getId(), sr23);
        serviceRequesterMap.put(sr24.getId(), sr24);
        serviceRequesterMap.put(sr25.getId(), sr25);
        serviceRequesterMap.put(sr26.getId(), sr26);
        serviceRequesterMap.put(sr27.getId(), sr27);
        serviceRequesterMap.put(sr28.getId(), sr28);
        serviceRequesterMap.put(sr29.getId(), sr29);
        serviceRequesterMap.put(sr30.getId(), sr30);

    }

    /**
     * Randomly pick malicious Service Providers
     * */
    public Set<String> pickMaliciousSP(int numberOfMaliciousNodes)
    {
        Set<String> maliciousSPIds = new HashSet<String>();

        Object[] spKeys = serviceProviderMap.keySet().toArray();

        while(true)
        {
            int index = util.pickRandomIndex(spKeys.length);

            maliciousSPIds.add((String) spKeys[index]);
            if(maliciousSPIds.size() == numberOfMaliciousNodes)
                break;

        }

        return maliciousSPIds;
    }

    /**
     * Randomly pick malicious Service Requester
     * */
    public Set<String> pickMaliciousSR(int numberOfMaliciousNodes)
    {
        Set<String> maliciousSRIds = new HashSet<String>();

        Object[] spKeys = serviceRequesterMap.keySet().toArray();

        while(true)
        {
            int index = util.pickRandomIndex(spKeys.length);

            maliciousSRIds.add((String) spKeys[index]);
            if(maliciousSRIds.size() == numberOfMaliciousNodes)
                break;

        }

        return maliciousSRIds;
    }

    /**
     * get a service provider that matches given waitTime
     * */
    public List<CHServiceProvider> queryServiceProvider(Integer waitTime)
    {
        List<CHServiceProvider> serviceProviders = new ArrayList<CHServiceProvider>();

        for(CHServiceProvider serviceProvider: serviceProviderMap.values())
        {
            if(serviceProvider.getWaitTime().equals(waitTime))
            {
                serviceProviders.add(serviceProvider);
            }
        }
        return serviceProviders;
    }

    /**
     * Service requester gives feedback about service providers  (+1= positive, -1= negative)
     * */
    public void logSPFeedBack(int rating, Integer actualWaitTime, CHServiceProvider serviceProvider)
    {
        serviceProvider.calculateTrustScore();
        serviceProvider.setActualWaitTimeAverage((actualWaitTime+serviceProvider.getWaitTime())/2);
    }

    /**
     *
     * */
    public void logSRFeedBack(int rating, List<SPVisitors> spVisitors, int actualWaitTime)
    {
        for(SPVisitors sp : spVisitors)
        {
            if((sp.getWaitTimeLogged() - actualWaitTime) < waitTimeThreshold ) //trustworthy
            {
                updateSRTrustScore(1,serviceRequesterMap.get(sp.getSRId()));
            }
            else
            {
                updateSRTrustScore(-1,serviceRequesterMap.get(sp.getSRId()));
            }
        }
    }

    /**
     * Increment or decrement reputation rating
     * */
    public void updateSRTrustScore(int rating, CHServiceRequester sr)
    {
        Double tempRepScore = sr.getReputationScore()+rating;

        sr.setReputationScore(normalizeRepScore(tempRepScore));
    }

    /**
     * Trust score of service requester
     * */
    public Boolean isServiceRequesterTrustWorthy(String SPId)
    {
        Boolean trustWorthy = false;

        if(serviceRequesterMap.get(SPId).getReputationScore() >= repScoreThreshold)
        {
            trustWorthy = true;
        }

        return trustWorthy;
    }

    /**
     * Normalize reputation score
     * */
    private Double normalizeRepScore(Double repS)
    {
        if(repS > maxReputationScore)
        {
            maxReputationScore = repS;
        }

        if (repS < minReputationScore)
        {
            minReputationScore = repS;
        }

        return   ( (repS - minReputationScore) / (maxReputationScore - minReputationScore));
    }

}
