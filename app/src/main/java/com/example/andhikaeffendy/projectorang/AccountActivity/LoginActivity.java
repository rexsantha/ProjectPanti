package com.example.andhikaeffendy.projectorang.AccountActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.andhikaeffendy.projectorang.BottomNavigationActivity;
import com.example.andhikaeffendy.projectorang.MainActivity;
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

public class LoginActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "USER_DATA";

    private String mUsername, mPassword;
    private EditText mEmailET, mPassET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        Button login = (Button) findViewById(R.id.btn_login);
        TextView signUp = (TextView) findViewById(R.id.signup_btn);
        mEmailET = (EditText) findViewById(R.id.email_login);
        mPassET = (EditText) findViewById(R.id.password_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUsername = mEmailET.getText().toString();
                mPassword = mPassET.getText().toString();

                new UserDataAsyncTask().execute();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }

    private void savePreferences(String username, String password){
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("username", username).apply();
        editor.putString("password", password).apply();

        // Commit the edits!
        editor.commit();
    }

    class UserDataAsyncTask extends AsyncTask<URL,Void,String> {
        String BASE_URL="http://ioidev.com/login.php";
        ProgressDialog dialog = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute() {
            dialog.setMessage("Sedang Mencoba Masuk.");
            dialog.show();
            super.onPreExecute();
        }

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
            if(dialog.isShowing()){
                dialog.dismiss();
            }
            if(strings.toLowerCase().equals("sukses")){
                savePreferences(mUsername,mPassword);
                Intent tempIntent = new Intent(LoginActivity.this, BottomNavigationActivity.class);
                tempIntent.putExtra("username", mUsername);
                tempIntent.putExtra("password", mPassword);
                startActivity(tempIntent);
            }else{
                Toast.makeText(getApplicationContext(),strings, Toast.LENGTH_SHORT).show();
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

                String data = URLEncoder.encode("username", "UTF-8")
                        + "=" + URLEncoder.encode(mUsername, "UTF-8");

                data += "&" + URLEncoder.encode("password", "UTF-8") + "="
                        + URLEncoder.encode(mPassword, "UTF-8");

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
