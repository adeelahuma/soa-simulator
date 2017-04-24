package actors;

/**
 * @author adeelahuma
 * */

public class CHServiceProvider extends ServiceProvider
{
    private Double trustScore;
    private Integer actualWaitTimeAverage;

    public Double getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(Double trustScore) {
        this.trustScore = trustScore;
    }

    public Integer getActualWaitTimeAverage() {
        return actualWaitTimeAverage;
    }

    public void setActualWaitTimeAverage(Integer actualWaitTimeAverage) {
        this.actualWaitTimeAverage = actualWaitTimeAverage;
    }

    public CHServiceProvider(String id, String name, Integer waitTime, Double trustScore, Integer actualWaitTimeAverage) {
        super(id, name, waitTime);
        this.trustScore = trustScore;
        this.actualWaitTimeAverage = actualWaitTimeAverage;
    }
}
