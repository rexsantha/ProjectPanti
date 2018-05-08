package com.example.andhikaeffendy.projectorang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RiwayatAdapter extends ArrayAdapter<Riwayat> {
    public RiwayatAdapter(@NonNull Context context, @NonNull List<Riwayat> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.riwayat_fragment, parent, false);
        }
        Riwayat current = getItem(position);
        TextView namaPanti = (TextView) convertView.findViewById(R.id.nama_panti_riwayat);
        TextView jumlahDonasi = (TextView) convertView.findViewById(R.id.jumlah_donasi);
        TextView tipeAtm = (TextView) convertView.findViewById(R.id.tipe_atm);
        TextView namaPenerima = (TextView) convertView.findViewById(R.id.nama_penerima);

        namaPanti.setText(current.getmNamaPanti());
        jumlahDonasi.setText(current.getmJumlahDonasi());
        tipeAtm.setText(current.getmTipeAtm());
        namaPenerima.setText(current.getmNamaPenerima());

        return convertView;
    }
}
