package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CocheDB  extends SQLiteOpenHelper {
    public CocheDB(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE PROPIETARIO(TELEFONO VARCHAR(20) PRIMARY KEY NOT NULL," +
                "NOMBRE VARCHAR(200)NOT NULL," +
                "DOMICILIO VARCHAR(200)," +
                "FECHA VARCHAR(200))");
        db.execSQL("CREATE TABLE SEGURO (IDSEGURO VARCHAR(20) PRIMARY KEY NOT NULL," +
                " DESCRIPCION VARCHAR(200)," +
                " FECHA DATE," +
                " TIPO VARCHAR(200)," +
                " TELEFONO VARCHAR(20)NOT NULL, " +
                " TELEFONO VARCHAR(20) FOREIGN KEY(TELEFONO) REFERENCES(PROPIETARIO))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
