package activity.entity;

import activity.boundary.ActivityInputDTO;
import io.quarkus.runtime.annotations.RegisterForReflection;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

import java.math.BigDecimal;
import java.util.UUID;

@DynamoDbBean
@RegisterForReflection
public class Activity {

    private String id;
    private String date;
    private String summary;
    private String classification;
    private String success;
    private BigDecimal fatigueATL;
    private BigDecimal fitnessCTL;
    private BigDecimal formTSB;
    private BigDecimal restingHeartRate;

    public Activity() {}

    public Activity(ActivityInputDTO dto) {
        this.id = UUID.randomUUID().toString();
        this.date = dto.date().toString();
        this.summary = dto.summary();
        this.classification = dto.classification();
        this.success = dto.success();
        this.fatigueATL = dto.fatigueATL();
        this.fitnessCTL = dto.fitnessCTL();
        this.formTSB = dto.formTSB();
        this.restingHeartRate = dto.restingHeartRate();
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
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
