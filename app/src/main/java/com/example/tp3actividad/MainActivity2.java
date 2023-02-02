package com.example.tp3actividad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity2 extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    private TextView textView;
    RequestQueue mQueue;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Grupo de riesgo");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        bottomNavigationView = findViewById(R.id.bottomNavigationView2);
        Menu menu = bottomNavigationView .getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C2331")));

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.inicio:
                        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
                        startActivityForResult(intent,1);
                        break;

                    case R.id.riesgo:

                        break;

                    case R.id.info:
                        Intent intent3 = new Intent(MainActivity2.this, MainActivity3.class);
                        startActivityForResult(intent3,1);
                        break;
                    case R.id.datos:
                        Intent intent4 = new Intent(MainActivity2.this, MainActivity4.class);
                        startActivityForResult(intent4,1);
                        break;

                    default:

                }
                return true;
            }
        });
        Button calcular = findViewById(R.id.button3);
        textView = findViewById(R.id.textView);
        mQueue= Volley.newRequestQueue(MainActivity2.this);
        TraerString();
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
                startActivityForResult(intent,3);

            }
        });


    }

    private void TraerString(){

        String URL = "https://ppc2021.edit.com.ar/service/api/info";
        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);


                        String texto = jsonObject.getString("result");
                        textView.setText("EL TEXTO QUE PROVIENE DE LA API ES: ".concat(texto));

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.getMessage());
            }
        });
        mQueue.add(request);
    }

}