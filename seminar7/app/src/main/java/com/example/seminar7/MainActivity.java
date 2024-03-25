package com.example.seminar7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL("http://dataservice.accuweather.com/forecasts/v1/daily/1day/272938?apikey=xYuQbKwATGkXORfdk7pSHgZerpZYGRML&language=en-us&metric=true");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String linie;
                    StringBuilder builder = new StringBuilder();
                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }
                    JSONObject object = new JSONObject(builder.toString());
                    JSONArray array = object.getJSONArray("DailyForecasts");
                    JSONObject primulObiect = array.getJSONObject(0);
                    JSONObject temperatura = primulObiect.getJSONObject("Temperature");
                    JSONObject minim = temperatura.getJSONObject("Minimum");
                    JSONObject maxim = temperatura.getJSONObject("Maximum");
                    Double tempMin = minim.getDouble("Value");
                    Double tempMax = maxim.getDouble("Value");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = findViewById(R.id.textAfisat);
                            tv.setText("Minim:"+tempMin + " - Maxim: "+tempMax);
                        }
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void cautaOras(View view) {
        String requestLink = "http://dataservice.accuweather.com/locations/v1/cities/search?apikey=xYuQbKwATGkXORfdk7pSHgZerpZYGRML&q=";
        EditText et = findViewById(R.id.introduceOrasul);
        requestLink = requestLink  + et.getText();
        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.myLooper());
        String finalRequestLink = requestLink;
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(finalRequestLink);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    InputStream is = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                    String linie;
                    StringBuilder builder = new StringBuilder();
                    while ((linie = reader.readLine()) != null) {
                        builder.append(linie);
                    }
                    String key;
                    try {
                        String jsonResponse = builder.toString();
                        JSONArray jsonArray = new JSONArray(jsonResponse);
                        JSONObject jsonObject = jsonArray.getJSONObject(0);
                        key = jsonObject.getString("Key");
                    } catch (JSONException e) {
                        throw new RuntimeException("JSON parsing error: " + e.getMessage());
                    }
                    Spinner spinner = findViewById(R.id.spinnerOptions);
                    String newRequestFirst = "http://dataservice.accuweather.com/forecasts/v1/daily/1day/";
                    String newRequestLast = "?apikey=xYuQbKwATGkXORfdk7pSHgZerpZYGRML&language=en-us&metric=";
                    String newRequest = newRequestFirst+key+newRequestLast;
                    RadioGroup rg = findViewById(R.id.radioGroupTemperature);
                    int id = rg.getCheckedRadioButtonId();
                    if (id == R.id.radioButtonCelsius) {
                        newRequest = newRequest + "true";
                    }
                    else {
                        newRequest = newRequest + "false";
                    }
                    URL url2 = new URL(newRequest);
                    HttpURLConnection conn2 = (HttpURLConnection) url2.openConnection();
                    InputStream is2 = conn2.getInputStream();
                    BufferedReader reader2 = new BufferedReader(new InputStreamReader(is2));
                    String linie2;
                    StringBuilder builder2 = new StringBuilder();
                    while ((linie2 = reader2.readLine()) != null) {
                        builder2.append(linie2);
                    }
                    JSONObject object = new JSONObject(builder2.toString());
                    JSONArray array = object.getJSONArray("DailyForecasts");
                    JSONObject primulObiect = array.getJSONObject(0);
                    JSONObject temperatura = primulObiect.getJSONObject("Temperature");
                    JSONObject minim = temperatura.getJSONObject("Minimum");
                    JSONObject maxim = temperatura.getJSONObject("Maximum");
                    Double tempMin = minim.getDouble("Value");
                    Double tempMax = maxim.getDouble("Value");
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            TextView tv = findViewById(R.id.textAfisat);
                            tv.setText("Minim:"+tempMin + " - Maxim: "+tempMax);
                        }
                    });
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}