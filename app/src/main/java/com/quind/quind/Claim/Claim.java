package com.quind.quind.Claim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Claim {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("policy_id")
    @Expose
    private Integer policyId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("requirements_accepted_at")
    @Expose
    private String requirementsAcceptedAt;
    @SerializedName("on_process_at")
    @Expose
    private Object onProcessAt;
    @SerializedName("success_or_rejected_at")
    @Expose
    private Object successOrRejectedAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("policy_number")
    @Expose
    private String policyNumber;
    @SerializedName("insurance_type")
    @Expose
    private String insuranceType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPolicyId() {
        return policyId;
    }

    public void setPolicyId(Integer policyId) {
        this.policyId = policyId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequirementsAcceptedAt() {
        return requirementsAcceptedAt;
    }

    public void setRequirementsAcceptedAt(String requirementsAcceptedAt) {
        this.requirementsAcceptedAt = requirementsAcceptedAt;
    }

    public Object getOnProcessAt() {
        return onProcessAt;
    }

    public void setOnProcessAt(Object onProcessAt) {
        this.onProcessAt = onProcessAt;
    }

    public Object getSuccessOrRejectedAt() {
        return successOrRejectedAt;
    }

    public void setSuccessOrRejectedAt(Object successOrRejectedAt) {
        this.successOrRejectedAt = successOrRejectedAt;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

}
