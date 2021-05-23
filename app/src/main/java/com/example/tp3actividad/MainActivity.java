package com.example.tp3actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button GrupoRiesgo;
    private Button InformacionPacientes;
    private Button CargaDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GrupoRiesgo=findViewById(R.id.buttonGrupRiesgo);
        InformacionPacientes=findViewById(R.id.buttonInfoPacientes);
        CargaDatos=findViewById(R.id.buttonCargaDatos);


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