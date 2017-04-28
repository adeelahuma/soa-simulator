package actors;

import java.util.ArrayList;
import java.util.List;

/**
 * @author adeelahuma
 * */

public class CHServiceProvider extends ServiceProvider
{
    private Double trustScore;
    private Integer actualWaitTimeAverage;
    private List<SPVisitors> spVisitors = new ArrayList<SPVisitors>();
    private Double alpha = 1.0 ;          //Number of positive feedback
    private Double beta = 1.0;          //Number of negative feedback

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

    public List<SPVisitors> getSpVisitors() {
        return spVisitors;
    }

    public void setSpVisitors(List<SPVisitors> spVisitors) {
        this.spVisitors = spVisitors;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double calculateTrustScore()
    {
        this.trustScore = alpha / (alpha+beta);;
        return trustScore;
    }
}
