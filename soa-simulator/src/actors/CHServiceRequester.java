package actors;

/**
 * @author adeelahuma
 * */

public class CHServiceRequester extends ServiceRequester
{
    private Double reputationScore;
    private Double alpha = 1.0 ;          //Number of positive feedback
    private Double beta = 1.0;          //Number of negative feedback
    private Double credibilityScore = 0.0;

    public CHServiceRequester(String serviceRequesterId, String serviceRequesterName, Double reputationScore) {
        super(serviceRequesterId, serviceRequesterName);
        this.reputationScore = reputationScore;
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

    public Double getAlpha() {
        return alpha;
    }

    public void setAlpha(Double alpha) {
        this.alpha = alpha;
    }

    public Double getCredibilityScore() {
        return credibilityScore;
    }

    public void setCredibilityScore(Double credibilityScore) {
        this.credibilityScore = credibilityScore;
    }
}
