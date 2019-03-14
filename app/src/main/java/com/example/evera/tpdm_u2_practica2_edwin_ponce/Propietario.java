package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Propietario {
    private String telefono;
    private String nombre;
    private String Domicilio;
    private String fecha;
    private CocheDB base;
    String error;

    public Propietario(Activity activity){
        base = new CocheDB(activity,"aseguradora",null,1);
    }

    public Propietario(String telefono, String nombre, String domicilio, String fecha) {
        this.telefono = telefono;
        this.nombre = nombre;
        Domicilio = domicilio;
        this.fecha = fecha;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return Domicilio;
    }

    public void setDomicilio(String domicilio) {
        Domicilio = domicilio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean insertar(Propietario propietario){
        try{
            SQLiteDatabase Tinsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("TELEFONO",propietario.getTelefono());
            datos.put("NOMBRE",propietario.getNombre());
            datos.put("DOMICILIO",propietario.getDomicilio());
            datos.put("FECHA",propietario.getFecha());
            long resultado = Tinsertar.insert("PROPIETARIO",null,datos);
            Tinsertar.close();
            if(resultado ==-1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(String TELEFONO) {
        int resultado=0;
        try {
            SQLiteDatabase Teliminar = base.getWritableDatabase();
            String telefono[] = {TELEFONO};
            resultado = Teliminar.delete("PROPIETARIO", "TELEFONO=?", telefono);
            Teliminar.close();
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public boolean actualizar(Propietario propietario){
        try {
            SQLiteDatabase Tactualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            String telefono[] = {propietario.getTelefono()};
            datos.put("NOMBRE",propietario.getNombre());
            datos.put("DOMICILIO",propietario.getDomicilio());
            datos.put("FECHA",propietario.getFecha());
            long resultado = Tactualizar.update("PROPIETARIO",datos, "TELEFONO=?", telefono);
            Tactualizar.close();
            if (resultado == -1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public Propietario[] consultar(){
        SQLiteDatabase Tconsultar = base.getReadableDatabase();
        Propietario[] result = null;
        Cursor c = Tconsultar.rawQuery("SELECT * FROM PROPIETARIO",null);
        if(c.moveToFirst()){
            result = new Propietario[c.getColumnCount()];
            for (int i=0;c.moveToNext();i++){
                String tel= c.getString(0);
                String nom= c.getString(1);
                String dom= c.getString(2);
                String fecha= c.getString(3);
                result[i]= new Propietario(tel,nom,dom,fecha);
            }
        }
        return result;
    }

    public Propietario[] consultar(String columna, String clave){
        Propietario[] resultado= null;
        try{
            SQLiteDatabase transacciónConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM PROPIETARIO WHERE TELEFONO='"+clave+"'";
            if(columna.startsWith("NOMBRE")){
                SQL = "SELECT * FROM PROPIETARIO WHERE NOMBRE='"+clave+"'";
            }
            if(columna.startsWith("DOMICILIO")){
                SQL = "SELECT * FROM PROPIETARIO WHERE DOMICILIO='"+clave+"'";
            }
            if(columna.startsWith("FECHA")){
                SQL = "SELECT * FROM PROPIETARIO WHERE FECHA='"+clave+"'";
            }
            Cursor c = transacciónConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Propietario[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos]=new Propietario(c.getString(0),c.getString(1),c.getString(2),
                            c.getString(3));
                    pos++;
                }while (c.moveToNext());
            }
            transacciónConsulta.close();
        }catch (SQLiteException e) {
            return null;
        }
        return resultado;
    }
}
