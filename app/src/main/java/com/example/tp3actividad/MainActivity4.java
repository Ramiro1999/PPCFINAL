package com.example.tp3actividad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    private Button buttonEnviar;
    private RadioButton Si;
    private RadioButton No;
    private EditText text1;
    private EditText text2;
    private EditText text3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        Si = findViewById(R.id.radioButtonSi);
        No = findViewById(R.id.radioButtonNo);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);


        buttonEnviar=findViewById(R.id.button);
        Si.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(No.isChecked()){
                    No.setChecked(false);
                }
            }
        });

        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Si.isChecked()){
                    Si.setChecked(false);
                }
            }
        });

        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getText().toString().equals("") || text2.getText().toString().equals("") ||  text3.getText().toString().equals("")) {
                    Toast.makeText(MainActivity4.this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                    startActivityForResult(intent, 1);
                }
            }
        });
    }
}