package actors;

import util.Utility;
import java.util.*;

/**
 * @author adeelahuma
 * */

public class CentralHub
{
    public static final Double initialTrustScore = 0.5;
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
        CHServiceProvider sp1 = new CHServiceProvider(util.getId(), "BurgerKing", 10,initialTrustScore, null);
        CHServiceProvider sp2 = new CHServiceProvider(util.getId(), "Burger7", 5, initialTrustScore, null);
        CHServiceProvider sp3 = new CHServiceProvider(util.getId(), "Burger21", 5, initialTrustScore, null);
        CHServiceProvider sp4 = new CHServiceProvider(util.getId(), "KFC", 5, initialTrustScore, null);
        CHServiceProvider sp5 = new CHServiceProvider(util.getId(), "Chick-fill-A", 10, initialTrustScore, null);
        CHServiceProvider sp6 = new CHServiceProvider(util.getId(), "Panera", 10, initialTrustScore, null);
        CHServiceProvider sp7 = new CHServiceProvider(util.getId(), "Sweetgreen", 10, initialTrustScore, null);
        CHServiceProvider sp8 = new CHServiceProvider(util.getId(), "BasilLeaf", 10, initialTrustScore, null);
        CHServiceProvider sp9 = new CHServiceProvider(util.getId(), "Busara", 10, initialTrustScore, null);
        CHServiceProvider sp10 = new CHServiceProvider(util.getId(), "MidTown Kabob", 10, initialTrustScore, null);

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
     * get a service provider that matches given waitTime
     * */
    public List<ServiceProvider> queryServiceProvider(Integer waitTime)
    {
        List<ServiceProvider> serviceProviders = new ArrayList<ServiceProvider>();

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
    public void logSPFeedBack(int rating, int actualWaitTime, String SPId)
    {
        CHServiceProvider serviceProvider = serviceProviderMap.get(SPId);
        //serviceProvider.setTrustScore();
        //serviceProvider.setActualWaitTimeAverage();

    }
}