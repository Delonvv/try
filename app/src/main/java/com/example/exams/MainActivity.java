package com.example.exams;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetP getP = new GetP();
        getP.execute();
    }

    class GetP extends AsyncTask<Void, Void, Void> {

        String body;
        @Override
        protected Void doInBackground(Void... params) {

            Document doc_b = null;

            try {
                doc_b = Jsoup.connect("https://api.weather.yandex.ru/v2/forecast?lat=58.0105&lon=56.2502&limit=7")
                        .header("X-Yandex-API-Key","demo_yandex_weather_api_key_ca6d09349ba0")
                        .ignoreContentType(true)
                        .get();
            } catch (Exception e) {
                Log.d("Mylog", e.toString());
            }

            if (doc_b != null) {
                body = doc_b.text();
            } else body = "Ошибка";

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            try {

                    JSONObject jsoupObject = new JSONObject(body);

                    TextView textView = findViewById(R.id.textView);
                    textView.setText((new JSONObject(jsoupObject.get("fact").toString())).get("temp").toString());

                    TextView textView1 = findViewById(R.id.textView3);
                    textView1.setText((new JSONObject((new JSONObject(jsoupObject.get("geo_object").toString())).get("locality").toString()).get("name").toString()));

                     TextView textView3 = findViewById(R.id.textView11);
                textView3.setText((new JSONObject(jsoupObject.get("fact").toString())).get("temp").toString());

                TextView textView4 = findViewById(R.id.textView12);
                textView4.setText((new JSONObject(jsoupObject.get("fact").toString())).get("condition").toString());

                TextView textView100 = findViewById(R.id.textView100);
                textView100.setText((new JSONObject(jsoupObject.get("fact").toString())).get("temp").toString());

                TextView textView101 = findViewById(R.id.textView101);
                textView101.setText((new JSONObject((new JSONObject(jsoupObject.get("forecasts").toString())).get("date").toString()).get("day").toString()));


            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public class GetPogoda {
        public String temp;
        public String name;


    }
}