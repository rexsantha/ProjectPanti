package com.example.andhikaeffendy.projectorang;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailPantiActivity extends AppCompatActivity {

    private Intent extras;
    private TextView mNamaPantiTV, mProfilePantiTV, mPemilikTV, mAlamatPantiTV;
    private ImageView mCoverIV;
    private String mNamaPanti, mAlamatPanti, mProfilePanti, mNoTelpPanti, mHpPemilik, mCoverImage, mPemilik;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_panti);

        initialize();
        getIntentExtra();

        Button btnDonasi = (Button) findViewById(R.id.btn_donasi_awal);
        btnDonasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPantiActivity.this, DonasiActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initialize(){
        mNamaPantiTV = findViewById(R.id.nama_panti);
        mAlamatPantiTV = findViewById(R.id.alamat_panti);
        mPemilikTV= findViewById(R.id.pemilik);
        mCoverIV = findViewById(R.id.cover_panti);

        extras = getIntent();
    }

    private void getIntentExtra(){
        mNamaPanti = extras.getStringExtra("nama");
        mAlamatPanti = extras.getStringExtra("alamat");
        mProfilePanti = extras.getStringExtra("profile");
        mCoverImage = "http://" + extras.getStringExtra("cover");
        mPemilik = extras.getStringExtra("pemilik");

        setData(mNamaPanti, mAlamatPanti, mCoverImage, mPemilik);
    }

    private void setData(String namaPanti, String alamatPanti, String coverImage, String pemilik){
        mNamaPantiTV.setText(namaPanti);
        mAlamatPantiTV.setText(alamatPanti);
        mPemilikTV.setText(pemilik);
        Picasso.with(DetailPantiActivity.this).load(coverImage).into(mCoverIV);
        Log.d("coverimage", coverImage);
    }
}
