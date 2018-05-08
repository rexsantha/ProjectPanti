package com.example.andhikaeffendy.projectorang;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class RiwayatFragment extends android.support.v4.app.Fragment {
    public RiwayatFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.list_item, container, false);

        ArrayList<Riwayat> data = new ArrayList<>();
        data.add(new Riwayat("Panti Asuhan Anak Nugraha","Rp. 100.000,-","Bank Bca - 525 569 8521","Indah Permata"));
        data.add(new Riwayat("Panti Asuhan Anak Nugraha","Rp. 100.000,-","Bank Bca - 525 569 8521","Indah Permata"));

        ListView list = view.findViewById(R.id.list_riwayat);
        RiwayatAdapter adapter = new RiwayatAdapter(getActivity(), data);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*Button btnUploadBukti = (Button) view.findViewById(R.id.btn_bukti_bayar);
                btnUploadBukti.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
                    }
                });*/
            }
        });

        list.setAdapter(adapter);

        return view;
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        int PICK_IMAGE = 1;
        if (requestCode == PICK_IMAGE)
        {
            if (resultCode == Activity.RESULT_OK)
            {
                if (data != null)
                {
                    try
                    {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());

                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                }
            } else if (resultCode == Activity.RESULT_CANCELED)
            {
                Toast.makeText(getActivity(), "Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
