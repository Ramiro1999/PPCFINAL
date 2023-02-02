package com.example.tp3actividad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;


public class MainActivity extends AppCompatActivity {
    ActionBar actionBar;
    BottomNavigationView bottomNavigationView;
    private Button GrupoRiesgo;
    private Button InformacionPacientes;
    private Button CargaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Inicio");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GrupoRiesgo=findViewById(R.id.buttonGrupRiesgo);
        InformacionPacientes=findViewById(R.id.buttonInfoPacientes);
        CargaDatos=findViewById(R.id.buttonCargaDatos);
        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C2331")));

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        Menu menu = bottomNavigationView .getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.inicio:

                        break;

                    case R.id.riesgo:
                        Intent intent2 = new Intent(MainActivity.this, MainActivity2.class);
                        startActivityForResult(intent2,1);
                        break;

                    case R.id.info:
                        Intent intent3 = new Intent(MainActivity.this, MainActivity3.class);
                        startActivityForResult(intent3,1);
                        break;
                    case R.id.datos:
                        Intent intent4 = new Intent(MainActivity.this, MainActivity4.class);
                        startActivityForResult(intent4,1);
                        break;

                    default:

                }
                return true;
            }
        });
        GrupoRiesgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent,1);
            }
        });

        InformacionPacientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity3.class);
                startActivityForResult(intent,1);
            }
        });

        CargaDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity4.class);
                startActivityForResult(intent,1);
            }
        });

    }

}