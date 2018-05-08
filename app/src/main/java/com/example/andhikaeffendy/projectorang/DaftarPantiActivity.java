package com.example.andhikaeffendy.projectorang;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class DaftarPantiActivity extends ListActivity {
    private DaftarPantiAdapter mAdapter;
    private ListView listView;
    private Context context;
    private List<DaftarPanti> daftarPanti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListView listView = (ListView) findViewById(R.id.list_daftar_panti);
        new getDataPanti().execute();
        //getActionBar().show();
    }

    private List<DaftarPanti> DummyData() {
        ArrayList<DaftarPanti> data = new ArrayList<>();
        data.add(new DaftarPanti("Panti Asuhan Anak Nugraha","Jl.Padasuka No.88, Pasirlayung",R.drawable.icon_zakat));
        data.add(new DaftarPanti("Panti Sakinah","Cilengkrang 2 3 no 286 rt 06/03 kelurahan",R.drawable.icon_panti));
        data.add(new DaftarPanti("Panti Asuhan Multazam","Jl.Padasuka No.88, Pasirlayung",R.drawable.icon_donatur));
        data.add(new DaftarPanti("Panti Asuhan Anak Al-Hayat","Jl.Cibatu Raua no.23, Antapani Tengah",R.drawable.icon_infaq));
        data.add(new DaftarPanti("Panti Yatim Piatu Al-Fien","Gg. Sarimanah XIII No.2, Sarijadi, Sukasari",R.drawable.icon_wakaf));

        return data;
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        Toast.makeText(this, " selected" + position, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(DaftarPantiActivity.this, DetailPantiActivity.class);
        intent.putExtra("nama", daftarPanti.get(position).getmNamaPanti());
        intent.putExtra("alamat", daftarPanti.get(position).getmAlamatPanti());
        intent.putExtra("logo", daftarPanti.get(position).getmLogo());
        intent.putExtra("profile", daftarPanti.get(position).getmProfile());
        intent.putExtra("pemilik", daftarPanti.get(position).getmPemilik());
        intent.putExtra("telp", daftarPanti.get(position).getmTelp());
        intent.putExtra("hp", daftarPanti.get(position).getmNoHpPemilik());
        intent.putExtra("cover", daftarPanti.get(position).getmLogo());

        startActivity(intent);
    }

    class getDataPanti extends AsyncTask<URL,Void,String> {
        //Link API perlu diganti
        String BASE_URL="http://ioidev.com/panti.php";

        @Override
        protected String doInBackground(URL... urls) {
            String jsonString = "";
            URL url = null;
            try{
                url = createUrl(BASE_URL);
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            try{
                jsonString = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return jsonString;
        }

        @Override
        protected void onPostExecute(String mJsonData) {
            //DummyData() ganti dengan method getJsonData()
            daftarPanti = getJsonData(mJsonData);
            mAdapter = new DaftarPantiAdapter(DaftarPantiActivity.this, daftarPanti);
            setListAdapter(mAdapter);

        }

        private String makeHttpRequest (URL url) throws IOException{
            String jsonResponse = "";
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            try{
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setReadTimeout(10000);
                urlConnection.setConnectTimeout(15000);
                urlConnection.connect();
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            }catch (IOException e){

            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
                if(inputStream != null){
                    inputStream.close();
                }
            }
            return jsonResponse;
        }

        private URL createUrl(String url) throws MalformedURLException {
            return new URL(url);
        }

        private String readFromStream(InputStream inputStream) throws IOException {
            StringBuilder output = new StringBuilder();
            if(inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while(line != null){
                    output.append(line);
                    line = reader.readLine();
                }
            }
            return  output.toString();
        }

    }

    private ArrayList<DaftarPanti> getJsonData(String mJsonData){
        //Sesuaikan json parser
        ArrayList<DaftarPanti> daftarPanti = new ArrayList<>();

        try {
            JSONObject root = new JSONObject(mJsonData);
            //JSONArray features = root.getJSONArray("daftar");
            JSONArray data = root.getJSONArray("data");
            Log.d("asdasd", mJsonData);
            for(int i=0 ; i<data.length() ; i++){
                //JSONObject properties = hutan.getJSONObject(i).getJSONObject("properties");
                JSONObject properties = data.getJSONObject(i);
                daftarPanti.add(new DaftarPanti(properties.getString("nama_panti"),
                        properties.getString("alamat_panti"),
                        properties.getString("logo_panti"),
                        properties.getString("no_telp_panti"),
                        properties.getString("nama_lengkap_pemilik"),
                        properties.getString("no_hp_pemilik")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return daftarPanti;
    }
}
