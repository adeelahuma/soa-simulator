package actors;

/**
 * @author adeelahuma
 * */
public class SPVisitors
{
    private String SRId;
    private Integer waitTimeLogged;

    public String getSRId() {
        return SRId;
    }

    public void setSRId(String SRId) {
        this.SRId = SRId;
    }

    public Integer getWaitTimeLogged() {
        return waitTimeLogged;
    }

    public void setWaitTimeLogged(Integer waitTimeLogged) {
        this.waitTimeLogged = waitTimeLogged;
    }

    public SPVisitors(String SRId, Integer waitTimeLogged) {
        this.SRId = SRId;
        this.waitTimeLogged = waitTimeLogged;
    }
}
