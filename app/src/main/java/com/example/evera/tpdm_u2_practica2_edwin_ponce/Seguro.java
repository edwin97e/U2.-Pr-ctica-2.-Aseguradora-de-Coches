package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class Seguro {
    private String IdSeguro;
    private String Descripcion;
    private String fecha;
    private String tipo;
    private String telefono;
    private CocheDB base;
    String error;

    public Seguro(Activity activity){
        base = new CocheDB(activity,"aseguradora",null,1);
    }
    public Seguro(String idSeguro, String descripcion, String fecha, String tipo, String telefono) {
        IdSeguro = idSeguro;
        Descripcion = descripcion;
        this.fecha = fecha;
        this.tipo = tipo;
        this.telefono = telefono;
    }

    public String getIdSeguro() {
        return IdSeguro;
    }

    public void setIdSeguro(String idSeguro) {
        IdSeguro = idSeguro;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean insertar(Seguro seguro){
        try{
            SQLiteDatabase Tinsertar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            datos.put("IDSEGURO",seguro.getIdSeguro());
            datos.put("DESCRIPCION",seguro.getDescripcion());
            datos.put("FECHA",seguro.getFecha());
            datos.put("TIPO",seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());
            long resultado = Tinsertar.insert("SEGURO",null,datos);
            Tinsertar.close();
            if(resultado ==-1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public boolean eliminar(String ID) {
        int resultado=0;
        try {
            SQLiteDatabase Teliminar = base.getWritableDatabase();
            String id[] = {ID};
            resultado = Teliminar.delete("SEGURO", "IDSEGURO=?", id);
            Teliminar.close();
        } catch (SQLiteException e) {
            error = e.getMessage();
            return false;
        }
        return resultado>0;
    }

    public boolean actualizar(Seguro seguro){
        try {
            SQLiteDatabase Tactualizar = base.getWritableDatabase();
            ContentValues datos = new ContentValues();
            String id[] = {seguro.getIdSeguro()};
            datos.put("DESCRIPCION",seguro.getDescripcion());
            datos.put("FECHA",seguro.getFecha());
            datos.put("TIPO",seguro.getTipo());
            datos.put("TELEFONO",seguro.getTelefono());
            long resultado = Tactualizar.update("SEGURO",datos, "TELEFONO=?", id);
            Tactualizar.close();
            if (resultado == -1) return false;
        }catch (SQLiteException e){
            error = e.getMessage();
            return false;
        }
        return true;
    }

    public Seguro[] consultar(){
        SQLiteDatabase Tconsultar = base.getReadableDatabase();
        Seguro[] result = null;
        Cursor c = Tconsultar.rawQuery("SELECT * FROM SEGURO",null);
        if(c.moveToFirst()){
            result = new Seguro[c.getColumnCount()];
            for (int i=0;c.moveToNext();i++){
                String id= c.getString(0);
                String desc= c.getString(1);
                String fecha= c.getString(2);
                String tipo= c.getString(3);
                String tel= c.getString(4);

                result[i]= new Seguro(id,desc,fecha,tipo,tel);
            }
        }
        return result;
    }

    public Seguro[] consultar(String columna, String clave){
        Seguro[] resultado= null;
        try{
            SQLiteDatabase transacciónConsulta = base.getReadableDatabase();
            String SQL = "SELECT * FROM SEGURO WHERE IDSEGURO='"+clave+"'";
            if(columna.startsWith("DESCRIPCION")){
                SQL = "SELECT * FROM SEGURO WHERE DESCRIPCION='"+clave+"'";
            }
            if(columna.startsWith("FECHA")){
                SQL = "SELECT * FROM SEGURO WHERE FECHA='"+clave+"'";
            }
            if(columna.startsWith("TIPO")){
                SQL = "SELECT * FROM SEGURO WHERE TIPO='"+clave+"'";
            }
            if(columna.startsWith("TELEFONO")){
                SQL = "SELECT * FROM SEGURO WHERE TELEFONO='"+clave+"'";
            }
            Cursor c = transacciónConsulta.rawQuery(SQL, null);
            if(c.moveToFirst()){
                resultado = new Seguro[c.getCount()];
                int pos = 0;
                do{
                    resultado[pos]=new Seguro(c.getString(0),c.getString(1),c.getString(2),
                            c.getString(3),c.getString(4));
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
