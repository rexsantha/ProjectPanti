package com.example.andhikaeffendy.projectorang;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class DaftarPantiAdapter extends ArrayAdapter<DaftarPanti> {

    private Context context;

    public DaftarPantiAdapter(@NonNull Context context, @NonNull List<DaftarPanti> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_daftar_panti,parent,false);
        }
        DaftarPanti current = getItem(position);
        TextView namaPanti = (TextView) convertView.findViewById(R.id.nama_panti);
        TextView alamatPanti = (TextView) convertView.findViewById(R.id.alamat_panti);
        ImageView logoPanti = (ImageView) convertView.findViewById(R.id.logo_panti);

        namaPanti.setText(current.getmNamaPanti());
        alamatPanti.setText(current.getmAlamatPanti());
        Picasso.with(context).load("http://"+current.getmLogo()).into(logoPanti);

        return convertView;
    }
}
