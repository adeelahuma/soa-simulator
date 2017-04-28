package actors;

/**
 * @author adeelahuma
 * */

public class CHServiceRequester extends ServiceRequester
{
    private Double trustScore;
    private Double reputationScore;
    private Double alpha;          //Number of positive feedback
    private Double beta;          //Number of negative feedback

    public CHServiceRequester(String serviceRequesterId, String serviceRequesterName, Double trustScore) {
        super(serviceRequesterId, serviceRequesterName);
        this.trustScore = trustScore;
    }

    public Double getTrustScore() {
        return trustScore;
    }

    public void setTrustScore(Double trustScore) {
        this.trustScore = trustScore;
    }

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getReputationScore() {
        return reputationScore;
    }

    public void setReputationScore(Double reputationScore) {
        this.reputationScore = reputationScore;
    }

    public Double getBeta() {
        return beta;
    }

    public void setBeta(Double beta) {
        this.beta = beta;
    }

    public Double calculateTrustScore()
    {
        return alpha / (alpha+beta);
    }
}
