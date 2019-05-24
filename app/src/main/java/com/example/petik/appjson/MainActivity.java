package com.example.petik.appjson;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TextView textHasilJSON;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button btnJson = findViewById(R.id.btnJSON);

        btnJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();
            }
        });
    }

    private void uraiJSON(){
        String url = "https://api.myjson.com/bins/y8kak";

        JsonArrayRequest request = new JsonArrayRequest (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    JSONArray jsonArray = response.getJSONArray(Integer.parseInt("anggota"));
                    for (int i = 0; i < jsonArray.length(); i++) {
                    JSONArray anggota = jsonArray.getJSONObject(1);

                    String Kota = anggota.getString("Kota");
                    String siang = anggota.getString("siang");
                    String malam = anggota.getString("malam");
                    String dini_hari = anggota.getString("dini_hari");
                    String suhu = anggota.getString("suhu");
                    String Kelembapan = anggota.getString("Kelelembapan");


                    textHasilJSON.append(Kota+", " +siang+ ", " + malam + ", "
                            +dini_hari +"," + suhu+ ","+ Kelembapan+"\n\n");
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }
}
