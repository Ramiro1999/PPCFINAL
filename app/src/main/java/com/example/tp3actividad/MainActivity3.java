package com.example.tp3actividad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {

    private Button botonImagen1;
    private Button botonImagen2;
    private Button botonImagen3;
    private Button botonImagen4;

    private ImageView imagen1;
    private ImageView imagen2;
    private ImageView imagen3;
    private ImageView imagen4;
    ActionBar actionBar;
    private Drawable drawable;
    private Bitmap bitmap;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTitle("Información para pacientes");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        botonImagen1 = findViewById(R.id.buttonImg1);
        botonImagen2 = findViewById(R.id.buttonImg2);
        botonImagen3 = findViewById(R.id.buttonImg3);
        botonImagen4 = findViewById(R.id.buttonImg4);


        imagen1 = findViewById(R.id.imageView1);
        imagen2 = findViewById(R.id.imageView2);
        imagen3 = findViewById(R.id.imageView3);
        imagen4 = findViewById(R.id.imageView4);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1C2331")));

        bottomNavigationView = findViewById(R.id.bottomNavigationView3);

        Menu menu = bottomNavigationView .getMenu();
        MenuItem menuItem = menu.getItem(2);
        menuItem.setChecked(true);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {
                    case R.id.inicio:
                        Intent intent1 = new Intent(MainActivity3.this, MainActivity.class);
                        startActivityForResult(intent1,1);
                        break;

                    case R.id.riesgo:
                        Intent intent2 = new Intent(MainActivity3.this, MainActivity2.class);
                        startActivityForResult(intent2,1);
                        break;

                    case R.id.info:

                        break;
                    case R.id.datos:
                        Intent intent4 = new Intent(MainActivity3.this, MainActivity4.class);
                        startActivityForResult(intent4,1);
                        break;

                    default:

                }
                return true;
            }
        });


        botonImagen1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(imagen1);
            }
        });

        botonImagen2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(imagen2);
            }
        });

        botonImagen3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(imagen3);
            }
        });

        botonImagen4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(imagen4);
            }
        });

    }


    private void enviarImagen(ImageView imagen){
        drawable = imagen.getDrawable();
        bitmap = ((BitmapDrawable)drawable).getBitmap();

        try {
            File file = new File(getApplicationContext().getExternalCacheDir(), File.separator + "imagen.jpg");
            FileOutputStream fOut = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            fOut.flush();
            fOut.close();
            file.setReadable(true,false);
            final Intent intent = new Intent(Intent.ACTION_SEND);
            Uri photoUri = FileProvider.getUriForFile(getApplicationContext(), BuildConfig.APPLICATION_ID + ".provider",file);

            intent.putExtra(Intent.EXTRA_STREAM,photoUri);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.setType("image/jpg");
            Intent chooser = Intent.createChooser(intent, "Compartir via");
            List<ResolveInfo> resInfoList = this.getPackageManager().queryIntentActivities(chooser, PackageManager.MATCH_DEFAULT_ONLY);

            for (ResolveInfo resolveInfo : resInfoList) {
                String packageName = resolveInfo.activityInfo.packageName;
                this.grantUriPermission(packageName, photoUri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            }
            startActivity(chooser);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }







    /*private void compartirImagen(ImageView image) throws IOException {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        drawable = (BitmapDrawable) image.getDrawable();
        bitmap = drawable.getBitmap();
        File file = new File(getExternalCacheDir()+"/"+"Una linda imagen"+".png");
        Intent intent;
        try{
            FileOutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,10,outputStream);
            outputStream.flush();
            outputStream.close();
            intent = new Intent(Intent.ACTION_SEND);
            intent.setType("image/*");
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        startActivity(Intent.createChooser(intent,"Compartir imagen por : "));
    }

     */
