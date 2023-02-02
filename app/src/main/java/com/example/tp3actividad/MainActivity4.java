package com.example.tp3actividad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import static java.lang.Double.parseDouble;

public class MainActivity4 extends AppCompatActivity {
    ActionBar actionBar;
    private Button buttonEnviar;
    Switch aSwitch;
    private EditText text1;
    private EditText text2;
    private EditText text3;
    private boolean estado;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Carga de datos");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        aSwitch = findViewById(R.id.switch1);
        text1 = findViewById(R.id.editText1);
        text2 = findViewById(R.id.editText2);
        text3 = findViewById(R.id.editText3);


        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C2331")));
        bottomNavigationView = findViewById(R.id.bottomNavigationView4);

        Menu menu = bottomNavigationView.getMenu();
        MenuItem menuItem = menu.getItem(3);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.inicio:
                        Intent intent1 = new Intent(MainActivity4.this, MainActivity.class);
                        startActivityForResult(intent1,1);
                        break;

                    case R.id.riesgo:
                        Intent intent2 = new Intent(MainActivity4.this, MainActivity2.class);
                        startActivityForResult(intent2,1);
                        break;

                    case R.id.info:
                        Intent intent4 = new Intent(MainActivity4.this, MainActivity3.class);
                        startActivityForResult(intent4,1);
                        break;

                    case R.id.datos:

                        break;

                    default:

                }
                return true;
            }
        });

        SharedPreferences preferences1 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        text1.setText(preferences1.getString("paciente", ""));
        SharedPreferences preferences2 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        text2.setText(preferences2.getString("recurrente", ""));
        SharedPreferences preferences3 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        text3.setText(preferences3.getString("progreso", ""));
        SharedPreferences preferences4 = getSharedPreferences("datos", Context.MODE_PRIVATE);
        buttonEnviar=findViewById(R.id.button);


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text1.getText().toString().equals("") || text2.getText().toString().equals("") ||  text3.getText().toString().equals("")) {
                    Toast.makeText(MainActivity4.this, "Por favor, complete los campos", Toast.LENGTH_SHORT).show();
                }else {
                    if(verificarRiesgoYProgreso(Double.parseDouble(text2.getText().toString()),Double.parseDouble(text3.getText().toString()))) {
                        Intent intent = new Intent(MainActivity4.this, MainActivity5.class);
                        intent.putExtra("riesgo",text2.getText().toString());
                        intent.putExtra("progreso",text3.getText().toString());
                        intent.putExtra("esquema",verificarBooleano(estado));
                        startActivityForResult(intent, 1);
                        GuardarDatos(v);
                    }
                    else {
                        Toast.makeText(MainActivity4.this, "Por favor, ingrese rangos validos", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    public void GuardarDatos(View view){
        SharedPreferences preferencesBoton = getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencesBoton.edit();
        editor.putString("paciente", text1.getText().toString());
        editor.putString("recurrente", text2.getText().toString());
        editor.putString("progreso", text3.getText().toString());
        editor.commit();
    }

    private String verificarBooleano(boolean b){
        if(b){
            return "true";
        }else{
            return "false";
        }
    }

    public boolean verificarRiesgoYProgreso(double riesgo, double progreso){
        if(riesgo > 0.0 & riesgo<=10.0 & progreso > 0.0 & progreso <=10.0){
            return true;
        }else{
            return false;
        }

    }

    public void onSwitchClick(View view) {
        if(aSwitch.isChecked()){
            estado=true;
        }else{
            estado=false;
        }
    }
}