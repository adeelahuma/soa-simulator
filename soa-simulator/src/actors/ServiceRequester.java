package actors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adeelahuma
 * */
public class ServiceRequester
{
    private String id;
    private String serviceRequesterName;
    private Boolean isMalicious = false;
    private List<Boolean> feedbacks;

    public ServiceRequester(String serviceRequesterId, String serviceRequesterName) {
        this.id = serviceRequesterId;
        this.serviceRequesterName = serviceRequesterName;
    }

    public ServiceRequester() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceRequesterName() {
        return serviceRequesterName;
    }

    public void setServiceRequesterName(String serviceRequesterName) {
        this.serviceRequesterName = serviceRequesterName;
    }
    
    public void setIsMalicious(Boolean isMal){
    	this.isMalicious = isMal;
    }
    
    public Boolean getIsMalicious(){
    	return isMalicious;
    }
    
    public void setFeedbacks(List<Boolean> fdbacks){
    	this.feedbacks = fdbacks;
    }
    
    public List<Boolean> getFeedbacks(){
    	if (feedbacks == null){
    		feedbacks = new ArrayList<Boolean>();
    	}
    	return feedbacks;
    }
}
