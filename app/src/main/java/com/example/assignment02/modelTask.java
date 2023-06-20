package com.example.assignment02;

public class modelTask {

    private String sabaq,sabaqi,manzil,rollNum;

    public modelTask(String sabaq, String sabaqi, String manzil, String rollNum) {
        this.sabaq = sabaq;
        this.sabaqi = sabaqi;
        this.manzil = manzil;
        this.rollNum = rollNum;
    }

    public String getSabaq() {
        return sabaq;
    }

    public void setSabaq(String sabaq) {
        this.sabaq = sabaq;
    }

    public String getSabaqi() {
        return sabaqi;
    }

    public void setSabaqi(String sabaqi) {
        this.sabaqi = sabaqi;
    }

    public String getManzil() {
        return manzil;
    }

    public void setManzil(String manzil) {
        this.manzil = manzil;
    }

    public String getRollNum() {
        return rollNum;
    }

    public void setRollNum(String rollNum) {
        this.rollNum = rollNum;
    }
}
