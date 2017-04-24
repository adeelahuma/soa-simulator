package actors;

/**
 * @author adeelahuma
 * */
public class ServiceRequester
{
    private String id;
    private String serviceRequesterName;

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
}
