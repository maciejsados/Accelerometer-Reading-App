package com.example.rent.accelerometerreadingapp;

import android.content.Context;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;


public class SensorEventMenager {




    private static final String FILE_NAME = "sensorevent.txt";

    public static final SensorEventMenager instance = new SensorEventMenager();


    private SensorEventMenager() {
    }

    private FileOutputStream fos;
    private PrintWriter printWriter;

    public void OpenFile(Context context) {

        try {
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            printWriter = new PrintWriter(new OutputStreamWriter(fos));

        } catch (FileNotFoundException e) {
            Toast.makeText(context, "File not found.", Toast.LENGTH_SHORT).show();
        }

    }

    public void saveToFile(float x, float y, float z){
        printWriter.print(x);
        printWriter.print(";");
        printWriter.print(y);
        printWriter.print(";");
        printWriter.println(z);


    }

    public void close(){
        printWriter.close();


    }

}
