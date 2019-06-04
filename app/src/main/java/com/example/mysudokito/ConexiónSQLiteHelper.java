package com.example.mysudokito;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.renderscript.ScriptIntrinsicYuvToRGB;

import com.example.mysudokito.utilidades.Utilidades;

public class ConexiónSQLiteHelper extends SQLiteOpenHelper {



    public ConexiónSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_PUNTUACIONES);
        System.out.println(db.getVersion());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
           // db.execSQL("ALTER TABLE puntuaciones ADD COLUMN fecha STRING DEFAULT 0 ");
          //  db.execSQL("ALTER TABLE puntuaciones ADD COLUMN puntuacion_segundos STRING DEFAULT 0 ");
          //  db.execSQL("ALTER TABLE puntuaciones ADD COLUMN puntuacion_minutos STRING DEFAULT 0 ");
           // db.execSQL("DELETE FROM puntuaciones");
            System.out.println("CAMBIO DE BASE DE DATOS //////////////////////////////////////////////////////////////////////////////////////");
        }



    }


}
