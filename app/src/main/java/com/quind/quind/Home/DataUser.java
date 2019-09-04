package com.quind.quind.Home;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataUser implements Parcelable {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("id_card_number")
    @Expose
    private String idCardNumber;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("phone_number")
    @Expose
    private String phoneNumber;
    @SerializedName("place_of_birth")
    @Expose
    private String placeOfBirth;
    @SerializedName("date_of_birth")
    @Expose
    private String dateOfBirth;
    @SerializedName("reset_password_token")
    @Expose
    private Object resetPasswordToken;
    @SerializedName("reset_password_token_sent_at")
    @Expose
    private String resetPasswordTokenSentAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("avatar")
    @Expose
    private Avatar avatar;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("insurance_type")
    @Expose
    private List<String> insuranceType = null;

    protected DataUser(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        name = in.readString();
        email = in.readString();
        idCardNumber = in.readString();
        gender = in.readString();
        address = in.readString();
        phoneNumber = in.readString();
        placeOfBirth = in.readString();
        dateOfBirth = in.readString();
        resetPasswordTokenSentAt = in.readString();
        createdAt = in.readString();
        updatedAt = in.readString();
        city = in.readString();
    }

    public static final Creator<DataUser> CREATOR = new Creator<DataUser>() {
        @Override
        public DataUser createFromParcel(Parcel in) {
            return new DataUser(in);
        }

        @Override
        public DataUser[] newArray(int size) {
            return new DataUser[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Object getResetPasswordToken() {
        return resetPasswordToken;
    }

    public void setResetPasswordToken(Object resetPasswordToken) {
        this.resetPasswordToken = resetPasswordToken;
    }

    public String getResetPasswordTokenSentAt() {
        return resetPasswordTokenSentAt;
    }

    public void setResetPasswordTokenSentAt(String resetPasswordTokenSentAt) {
        this.resetPasswordTokenSentAt = resetPasswordTokenSentAt;
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

    public Avatar getAvatar() {
        return avatar;
    }

    public void setAvatar(Avatar avatar) {
        this.avatar = avatar;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public List<String> getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(List<String> insuranceType) {
        this.insuranceType = insuranceType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(name);
        dest.writeString(email);
        dest.writeString(idCardNumber);
        dest.writeString(gender);
        dest.writeString(address);
        dest.writeString(phoneNumber);
        dest.writeString(placeOfBirth);
        dest.writeString(dateOfBirth);
        dest.writeString(resetPasswordTokenSentAt);
        dest.writeString(createdAt);
        dest.writeString(updatedAt);
        dest.writeString(city);
    }
}
