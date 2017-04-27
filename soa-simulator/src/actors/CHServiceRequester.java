package actors;

/**
 * @author adeelahuma
 * */

public class CHServiceRequester extends ServiceRequester
{
    private Double trustScore;
    private Double reputationScore;

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
}
