package com.quind.quind.Claim;

public class DataClaime {
    String nama;
    String category;
    String date;
    String number;
    int total;

    public DataClaime(String nama, String category, String date, String number, int total) {
        this.nama = nama;
        this.category = category;
        this.date = date;
        this.number = number;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
