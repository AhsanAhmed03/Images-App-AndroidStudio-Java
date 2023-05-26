package com.getimages;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerImg;
    List<String> imagesPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagesPath = getImgpath();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewImg);
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        AdapterClass adapterClass = new AdapterClass(this,imagesPath);
        recyclerView.setAdapter(adapterClass);


    }

    private List<String> getImgpath(){

        List<String> pathImg = new ArrayList<>();

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,projection,
                null,null,null);
        if (cursor != null){
            while (cursor.moveToNext()){
                @SuppressLint("Range") String imagePath = cursor.getString
                        (cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                pathImg.add(imagePath);
            }

        }
        return pathImg;
    }
}