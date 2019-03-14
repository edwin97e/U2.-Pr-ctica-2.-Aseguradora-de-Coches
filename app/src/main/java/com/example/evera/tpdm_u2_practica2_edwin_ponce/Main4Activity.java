package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {
    RadioButton consultarP,consultarS;
    TextView lbl1,lbl2,lbl3,lbl4,lbl5;
    Button btnConsultar,btnRegresar,btnEliminar;
    EditText parametro;
    Propietario[] propietarios;
    Seguro[] seguros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        consultarP = findViewById(R.id.conP);
        consultarS = findViewById(R.id.conS);
        lbl1=findViewById(R.id.lbl11);
        lbl2=findViewById(R.id.lbl12);
        lbl3=findViewById(R.id.lbl13);
        lbl4=findViewById(R.id.lbl14);
        lbl5=findViewById(R.id.lbl15);
        lbl5.setVisibility(View.INVISIBLE);
        parametro = findViewById(R.id.buscartelefonoid);
        btnConsultar = findViewById(R.id.btnconsultar);
        btnRegresar = findViewById(R.id.regresarbtn);
        btnEliminar = findViewById(R.id.btneliminar);
        cambiarEtiquetas();
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                consultar();
            }
        });
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar();
            }
        });
    }

    public void consultar(){
        if(consultarP.isChecked()){
            Propietario propietario = new Propietario(this);
            propietarios = propietario.consultar("TELEFONO",parametro.getText().toString());
            if(propietarios==null){
                lbl1.setText("Telefono: no Hay Resultados");
            }else{
                for (int i=0;i<propietarios.length;i++){
                    Propietario temp= propietarios[i];
                    lbl1.setText("Telefono: "+temp.getTelefono());
                    lbl2.setText("Nombre: "+temp.getNombre());
                    lbl3.setText("Domicilio: "+temp.getDomicilio());
                    lbl4.setText("Fecha: "+temp.getFecha());
                }
            }
        }else if(consultarS.isChecked()){
            Seguro seguro = new Seguro(this);
            seguros = seguro.consultar("IDSEGURO",parametro.getText().toString());
            if(seguros==null){
                lbl1.setText("Telefono: no Hay Resultados");
            }else{
                for (int i=0;i<propietarios.length;i++){
                    Seguro temp= seguros[i];
                    lbl1.setText("ID SEGURO: "+temp.getIdSeguro());
                    lbl2.setText("Descripcion: "+temp.getDescripcion());
                    lbl3.setText("Fecha: "+temp.getFecha());
                    lbl4.setText("Tipo: "+temp.getTipo());
                    lbl5.setText("Telefono: "+temp.getTelefono());
                }
            }
        }else{
            Toast.makeText(this,"Selecciona Una Opcion de Busqueda", Toast.LENGTH_LONG).show();
        }
    }


    public void eliminar(){
        if(consultarP.isChecked()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atencion");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String mensaje="";
                    Propietario propietario = new Propietario(Main4Activity.this);
                    boolean resultado = propietario.eliminar(parametro.getText().toString());
                    if (resultado) {
                        mensaje="Se elimino Correctamente";
                    }else {
                        mensaje="Error al Elimnar";
                    }
                    Toast.makeText(Main4Activity.this,mensaje,Toast.LENGTH_LONG).show();
                }
            });
            alerta.show();
        }else if(consultarS.isChecked()){
            AlertDialog.Builder alerta = new AlertDialog.Builder(this);
            alerta.setTitle("Atencion");
            alerta.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String mensaje="";
                    Seguro seguro = new Seguro(Main4Activity.this);
                    boolean resultado = seguro.eliminar(parametro.getText().toString());
                    if (resultado) {
                        mensaje="Se elimino Correctamente";
                    }else {
                        mensaje="Error al Elimnar";
                    }
                    Toast.makeText(Main4Activity.this,mensaje,Toast.LENGTH_LONG).show();
                }
            });
            alerta.show();
        }else{
            Toast.makeText(Main4Activity.this,"Tiene Que Seleccionar Una Opcion Para Eliminar",Toast.LENGTH_LONG).show();
        }
    }

    public void cambiarEtiquetas(){
        if(consultarP.isChecked()){
            lbl1.setText("Telefono:");
            lbl2.setText("Nombre:");
            lbl3.setText("Domicilio:");
            lbl4.setText("Fecha:");
            lbl5.setVisibility(View.INVISIBLE);
        }else if(consultarS.isChecked()){
            lbl1.setText("ID SEGURO:");
            lbl2.setText("Descripcion:");
            lbl3.setText("Fecha:");
            lbl4.setText("Tipo:");
            lbl5.setVisibility(View.VISIBLE);
            lbl5.setText("Telefono:");
        }
    }

}
