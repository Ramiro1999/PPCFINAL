package com.example.tp3actividad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.LruCache;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    private Button buttonEnviar;
   NetworkImageView imagen;
    private Drawable drawable;
    private Bitmap bitmap;
    private double riesgo;
    private double progreso;
    private String esquema;
    RequestQueue mRequestQueue;
    ImageLoader mImageLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        buttonEnviar = findViewById(R.id.buttonEnviar);

        riesgo=Double.parseDouble(getIntent().getStringExtra("riesgo"));
        progreso=Double.parseDouble(getIntent().getStringExtra("progreso"));
        esquema= getIntent().getStringExtra("esquema");



        mRequestQueue = Volley.newRequestQueue(MainActivity5.this);
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });

        imagen = findViewById(R.id.imagenApi);
        imagen.setImageUrl("http://ppc2021.edit.com.ar/service/api/imagen/"+ riesgo +"/"+ progreso +"/"+ esquema,mImageLoader);


        buttonEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarImagen(imagen);
            }
        });

    }



    private void enviarImagen(ImageView imagen){
        Drawable drawable = imagen.getDrawable();
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