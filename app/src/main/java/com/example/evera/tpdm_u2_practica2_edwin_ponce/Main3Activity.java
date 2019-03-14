package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {
    RadioButton insertarP,insertarS;
    TextView lbl1,lbl2,lbl3,lbl4,lbl5;
    EditText txt1,txt2,txt3,txt4,txt5;
    Button btnInsertar,btnRegresar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        insertarP = findViewById(R.id.insP);
        insertarS = findViewById(R.id.insS);
        lbl1=findViewById(R.id.lbl1);
        lbl2=findViewById(R.id.lbl2);
        lbl3=findViewById(R.id.lbl3);
        lbl4=findViewById(R.id.lbl4);
        lbl5=findViewById(R.id.lbl5);
        txt1=findViewById(R.id.txt1);
        txt2=findViewById(R.id.txt2);
        txt3=findViewById(R.id.txt3);
        txt4=findViewById(R.id.txt4);
        txt5=findViewById(R.id.txt5);
        btnInsertar = findViewById(R.id.btnInsertar);
        btnRegresar = findViewById(R.id.btnRegresar);
        cambiarEtiquetas();

        btnInsertar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                insertar();
            }
        });

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void insertar() {
        try {
            String mensaje = "";
            boolean respuesta=false;
            if(insertarP.isChecked()){
                Propietario p = new Propietario(this);
                respuesta = p.insertar(new Propietario(txt1.getText().toString(),txt2.getText().toString(),
                        txt3.getText().toString(),txt4.getText().toString()));
            }else if(insertarS.isChecked()){
                Seguro s = new Seguro(this);
                respuesta = s.insertar(new Seguro(txt1.getText().toString(),txt2.getText().toString(),
                        txt3.getText().toString(),txt4.getText().toString(),txt5.getText().toString()));
            }
            if (respuesta) {
                mensaje = "Se inserto Correctamente";
            } else {
                mensaje = "Error no se pudo Insertar";
            }
            AlertDialog.Builder alerta =new AlertDialog.Builder(this);
            alerta.setTitle("ATENCION").setMessage(mensaje).setPositiveButton("ok",null).show();
        }catch (Exception e){

        }
    }

    public void cambiarEtiquetas(){
        if(insertarP.isChecked()){
            lbl1.setText("Telefono:");
            lbl2.setText("Nombre:");
            lbl3.setText("Domicilio:");
            lbl4.setText("Fecha:");
            lbl5.setVisibility(View.INVISIBLE);
            txt5.setVisibility(View.INVISIBLE);
        }else if(insertarS.isChecked()){
            lbl1.setText("ID SEGURO:");
            lbl2.setText("Descripcion:");
            lbl3.setText("Fecha:");
            lbl4.setText("Tipo:");
            lbl5.setVisibility(View.VISIBLE);
            txt5.setVisibility(View.VISIBLE);
            lbl5.setText("Telefono:");
        }
    }
}
