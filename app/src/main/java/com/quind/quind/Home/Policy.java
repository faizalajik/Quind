package com.quind.quind.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Policy implements Serializable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("policy_number")
    @Expose
    private String policyNumber;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("insured_item")
    @Expose
    private String insuredItem;
    @SerializedName("item_description")
    @Expose
    private String itemDescription;
    @SerializedName("insurance_type")
    @Expose
    private String insuranceType;
    @SerializedName("premium_per_month")
    @Expose
    private Integer premiumPerMonth;
    @SerializedName("payment_due_date")
    @Expose
    private Integer paymentDueDate;
    @SerializedName("limit_per_year")
    @Expose
    private Integer limitPerYear;
    @SerializedName("balance")
    @Expose
    private Integer balance;
    @SerializedName("deposit")
    @Expose
    private Object deposit;
    @SerializedName("document_url")
    @Expose
    private Object documentUrl;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("expired_at")
    @Expose
    private Object expiredAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInsuredItem() {
        return insuredItem;
    }

    public void setInsuredItem(String insuredItem) {
        this.insuredItem = insuredItem;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public Integer getPremiumPerMonth() {
        return premiumPerMonth;
    }

    public void setPremiumPerMonth(Integer premiumPerMonth) {
        this.premiumPerMonth = premiumPerMonth;
    }

    public Integer getPaymentDueDate() {
        return paymentDueDate;
    }

    public void setPaymentDueDate(Integer paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Integer getLimitPerYear() {
        return limitPerYear;
    }

    public void setLimitPerYear(Integer limitPerYear) {
        this.limitPerYear = limitPerYear;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Object getDeposit() {
        return deposit;
    }

    public void setDeposit(Object deposit) {
        this.deposit = deposit;
    }

    public Object getDocumentUrl() {
        return documentUrl;
    }

    public void setDocumentUrl(Object documentUrl) {
        this.documentUrl = documentUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Object expiredAt) {
        this.expiredAt = expiredAt;
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
}
