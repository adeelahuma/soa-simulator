package actors;

/**
 * @author adeelahuma
 * */

public class CHServiceRequester extends ServiceRequester
{
    private Double reputationScore;

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
}
