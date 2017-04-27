package actors;

/**
 * @author adeelahuma
 * */
public class SPVisitors
{
    private String SRId;
    private Double waitTimeLogged;

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
}
