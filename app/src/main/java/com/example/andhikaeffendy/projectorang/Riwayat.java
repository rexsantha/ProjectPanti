package com.example.andhikaeffendy.projectorang;

public class Riwayat {

    private String mNamaPantiRiwayat, mJumlahDonasi, mTipeAtm, mNamaPenerima;

    public Riwayat(String mNamaPantiRiwayat, String mJumlahDonasi, String mTipeAtm, String mNamaPenerima){
        this.mNamaPantiRiwayat = mNamaPantiRiwayat;
        this.mJumlahDonasi = mJumlahDonasi;
        this.mTipeAtm = mTipeAtm;
        this.mNamaPenerima = mNamaPenerima;
    }

    public String getmNamaPanti() {
        return mNamaPantiRiwayat;
    }

    public String getmJumlahDonasi() {
        return mJumlahDonasi;
    }

    public String getmNamaPenerima() {
        return mNamaPenerima;
    }

    public String getmTipeAtm() {
        return mTipeAtm;
    }

    public void setmNamaPanti(String mNamaPanti) {
        this.mNamaPantiRiwayat = mNamaPanti;
    }

    public void setmJumlahDonasi(String mJumlahDonasi) {
        this.mJumlahDonasi = mJumlahDonasi;
    }

    public void setmNamaPenerima(String mNamaPenerima) {
        this.mNamaPenerima = mNamaPenerima;
    }

    public void setmTipeAtm(String mTipeAtm) {
        this.mTipeAtm = mTipeAtm;
    }
}
