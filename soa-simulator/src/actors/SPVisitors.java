package actors;

import java.util.List;

/**
 * @author adeelahuma
 * */
public class SPVisitors
{
    private String SRId;
    private Double waitTimeLogged;
    private Double waitTimeAdvertised;
    private Boolean isMalicious = false;
    private List<Boolean> feedbacks;

    public String getSRId() {
        return SRId;
    }

    public void setSRId(String SRId) {
        this.SRId = SRId;
    }

    public Double getWaitTimeLogged() {
        return waitTimeLogged;
    }

    public void setWaitTimeLogged(Double waitTimeLogged) {
        this.waitTimeLogged = waitTimeLogged;
    }

    public SPVisitors(String SRId, Double waitTimeLogged, Double waitTimeAdver, Boolean isMal, List<Boolean> srFeedbacks) {
        this.SRId = SRId;
        this.waitTimeLogged = waitTimeLogged;
        this.waitTimeAdvertised = waitTimeAdver;
        isMalicious = isMal;
        feedbacks = srFeedbacks;
    }
    
    public Boolean getIsMalicious(){
    	return isMalicious;
    }
    
    public List<Boolean> getFeedbacks(){
    	return feedbacks;
    }

    public Double getAdvertisedWaitTime() {
        return waitTimeAdvertised;
    }
}
