package com.example.andhikaeffendy.projectorang;

public class DaftarPanti {
    private String mNamaPanti, mAlamatPanti, mLogo, mTelp, mProfile, mPemilik, mNoHpPemilik;

    public DaftarPanti(String mNamaPanti, String mAlamatPanti, String mLogo, String mProfile, String mTelp,  String mPemilik, String mNoHpPemilik) {
        this.mNamaPanti = mNamaPanti;
        this.mAlamatPanti = mAlamatPanti;
        this.mLogo = mLogo;
        this.mTelp = mTelp;
        this.mProfile = mProfile;
        this.mPemilik = mPemilik;
        this.mNoHpPemilik = mNoHpPemilik;
    }

    public DaftarPanti(String mNamaPanti, String mAlamatPanti, String mLogo, String mTelp,  String mPemilik, String mNoHpPemilik) {
        this.mNamaPanti = mNamaPanti;
        this.mAlamatPanti = mAlamatPanti;
        this.mLogo = mLogo;
        this.mTelp = mTelp;
        this.mProfile = mProfile;
        this.mPemilik = mPemilik;
        this.mNoHpPemilik = mNoHpPemilik;
    }

    public DaftarPanti(String mNamaPanti, String mAlamatPanti, int mLogoPanti){
        this.mNamaPanti = mNamaPanti;
        this.mAlamatPanti = mAlamatPanti;
    }

    public DaftarPanti(String mNamaPanti, String mAlamatPanti, String mLogo) {
        this.mNamaPanti = mNamaPanti;
        this.mAlamatPanti = mAlamatPanti;
        this.mLogo = mLogo;
    }

    public String getmAlamatPanti() {
        return mAlamatPanti;
    }

    public String getmNamaPanti() {
        return mNamaPanti;
    }


    public void setmAlamatPanti(String mAlamatPanti) {
        this.mAlamatPanti = mAlamatPanti;
    }


    public void setmNamaPanti(String mNamaPanti) {
        this.mNamaPanti = mNamaPanti;
    }

    public String getmLogo() {
        return mLogo;
    }

    public String getmTelp() {
        return mTelp;
    }

    public String getmProfile() {
        return mProfile;
    }

    public String getmPemilik() {
        return mPemilik;
    }

    public String getmNoHpPemilik() {
        return mNoHpPemilik;
    }
}
