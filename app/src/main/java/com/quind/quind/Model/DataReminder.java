package com.quind.quind.Model;

import java.io.Serializable;

public class DataReminder implements Serializable {
    String category, total, tanggal, waktu, note,monthly;
    int id ;
    public DataReminder() {

    }

    public DataReminder(int id,String category, String total, String tanggal, String waktu, String note,String monthly) {
        this.id = id ;
        this.category = category;
        this.total = total;
        this.tanggal = tanggal;
        this.waktu = waktu;
        this.note = note;
        this.monthly = monthly;

    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getWaktu() {
        return waktu;
    }

    public void setWaktu(String waktu) {
        this.waktu = waktu;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
