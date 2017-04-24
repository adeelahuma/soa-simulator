package actors;

/**
 * @author adeelahuma
 * */
public class ServiceProvider
{
    private String id;
    private String serviceProviderName;
    private Integer waitTime;

    public ServiceProvider(String id, String name, Integer waitTime)
    {
        this.id = id;
        this.serviceProviderName = name;
        this.waitTime = waitTime;
    }

    public ServiceProvider() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public Integer getWaitTime() {
        return waitTime;
    }

    public void setWaitTime(Integer waitTime) {
        this.waitTime = waitTime;
    }
}
