package com.example.andhikaeffendy.projectorang.AccountActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.andhikaeffendy.projectorang.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

public class SignUpActivity extends AppCompatActivity {

    private String mFullName, mPassword, mEmail, mConfirmPassword;
    private EditText mFullNameET, mPassET, mEmailET, mConfirmPassET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mFullNameET = findViewById(R.id.full_name);
        mEmailET = findViewById(R.id.email);
        mPassET = findViewById(R.id.password);
        mConfirmPassET = findViewById(R.id.re_password);

        getSupportActionBar().hide();

        Button signUpBtn = (Button) findViewById(R.id.daftar_btn);

        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFullName = mFullNameET.getText().toString();
                mEmail = mEmailET.getText().toString();
                mPassword = mPassET.getText().toString();

                new UserRegisAsyncTask().execute();
            }
        });
        }

    class UserRegisAsyncTask extends AsyncTask<URL,Void,String> {
        String BASE_URL="http://ioidev.com/Registrasi.php";
        @Override
        protected String doInBackground(URL... urls) {
            String serverResponse = "";
            URL url = null;
            try{
                url = createUrl(BASE_URL);
            }catch(MalformedURLException e){
                e.printStackTrace();
            }
            try{
                serverResponse = makeHttpRequest(url);
            } catch (IOException e) {
                e.printStackTrace();
            }

            return serverResponse;
        }

        @Override
        protected void onPostExecute(String strings) {
            Log.d("webrespondd", strings);
            if(strings.toLowerCase().equals("sukses")){
                Toast.makeText(SignUpActivity.this, "Daftar Berhasil", Toast.LENGTH_SHORT).show();
                Intent tempIntent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(tempIntent);
            }else{
                Toast.makeText(SignUpActivity.this, strings, Toast.LENGTH_SHORT).show();
            }

        }

        private String makeHttpRequest (URL add_url) throws IOException{
            String jsonResponse = "";
            try{
                URL url = add_url;
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));

                String data = URLEncoder.encode("nama", "UTF-8")
                        + "=" + URLEncoder.encode(mFullName, "UTF-8");

                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(mPassword, "UTF-8");

                data += "&" + URLEncoder.encode("email", "UTF-8") + "="
                        + URLEncoder.encode(mEmail, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
                Log.d("resp", jsonResponse);
                inputStream.close();
                httpURLConnection.disconnect();
                return jsonResponse;


            }catch (MalformedURLException e){
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
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

}
