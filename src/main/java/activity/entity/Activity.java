package activity.entity;

import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;

@DynamoDbBean
@RegisterForReflection
public class Activity {

    private String id;
    private String date;
    private String summary;
    private BigDecimal fatigueATL;
    private BigDecimal fitnessCTL;
    private BigDecimal formTSB;
    private BigDecimal restingHeartRate;

    public Activity() {}

    public Activity(String id, String date, String summary, BigDecimal fatigueATL, BigDecimal fitnessCTL, BigDecimal formTSB, BigDecimal restingHeartRate) {
        this.id = id;
        this.date = date;
        this.summary = summary;
        this.fatigueATL = fatigueATL;
        this.fitnessCTL = fitnessCTL;
        this.formTSB = formTSB;
        this.restingHeartRate = restingHeartRate;
    }

    @DynamoDbPartitionKey
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public BigDecimal getFatigueATL() {
        return fatigueATL;
    }

    public void setFatigueATL(BigDecimal fatigueATL) {
        this.fatigueATL = fatigueATL;
    }

    public BigDecimal getFitnessCTL() {
        return fitnessCTL;
    }

    public void setFitnessCTL(BigDecimal fitnessCTL) {
        this.fitnessCTL = fitnessCTL;
    }

    public BigDecimal getFormTSB() {
        return formTSB;
    }

    public void setFormTSB(BigDecimal formTSB) {
        this.formTSB = formTSB;
    }

    public BigDecimal getRestingHeartRate() {
        return restingHeartRate;
    }

    public void setRestingHeartRate(BigDecimal restingHeartRate) {
        this.restingHeartRate = restingHeartRate;
    }
}
