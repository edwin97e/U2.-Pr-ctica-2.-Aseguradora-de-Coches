package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Main5Activity extends AppCompatActivity {
    RadioButton consultarP,consultarS;
    TextView lbl21,lbl22,lbl23,lbl24;
    EditText txt21,txt22,txt23,txt24,parametro;
    Button btnBuscar,btnActualizar,btnRegresar;
    Propietario[] propietarios;
    Seguro[] seguros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        consultarP = findViewById(R.id.conP);
        consultarS = findViewById(R.id.conS);
        lbl21=findViewById(R.id.lbl21);
        lbl22=findViewById(R.id.lbl22);
        lbl23=findViewById(R.id.lbl23);
        lbl24=findViewById(R.id.lbl24);
        txt21=findViewById(R.id.txt21);
        txt22=findViewById(R.id.txt22);
        txt22=findViewById(R.id.txt23);
        txt22=findViewById(R.id.txt24);
        btnBuscar = findViewById(R.id.btnCons);
        btnActualizar = findViewById(R.id.btnActu);
        btnRegresar = findViewById(R.id.btnRegresar1);
        parametro  = findViewById(R.id.txtparametro);
        cambiarEtiquetas();
        btnBuscar.setOnClickListener(new View.OnClickListener(){
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
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void cambiarEtiquetas(){
        if(consultarP.isChecked()){
            lbl21.setText("Nombre:");
            lbl22.setText("Domicilio:");
            lbl23.setText("Fecha:");
            txt21.setText("");
            txt22.setText("");
            txt23.setText("");
            lbl24.setVisibility(View.INVISIBLE);
            txt24.setVisibility(View.INVISIBLE);

        }else if(consultarS.isChecked()){
            lbl21.setText("Descripcion:");
            lbl22.setText("Fecha:");
            lbl23.setText("Tipo:");
            lbl24.setVisibility(View.VISIBLE);
            txt24.setVisibility(View.VISIBLE);
            lbl24.setText("Telefono:");
            txt21.setText("");
            txt22.setText("");
            txt23.setText("");
            txt24.setText("");
        }
    }


    private void actualizar() {
        if(consultarP.isChecked()){
            Propietario p = new Propietario(this);
            String mensaje;
            boolean respuesta = p.actualizar(new Propietario("",txt21.getText().toString(),txt22.getText().toString(),txt23.getText().toString()));
            if (respuesta) {
                mensaje = "se actualizo correctamente el propietario ";
            } else {
                mensaje = "Error algo salio mal: " +p.error;
            }
            Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        }else if(consultarS.isChecked()){
            if(consultarP.isChecked()){
                Seguro s = new Seguro(this);
                String mensaje;
                boolean respuesta = s.actualizar(new Seguro("",txt21.getText().toString(),txt22.getText().toString(),txt23.getText().toString(),txt24.getText().toString()));
                if (respuesta) {
                    mensaje = "se actualizo correctamente el propietario ";
                } else {
                    mensaje = "Error algo salio mal: " +s.error;
                }
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
            }
        }

    }
    public void consultar(){
        if(consultarP.isChecked()){
            cambiarEtiquetas();
            Propietario propietario = new Propietario(this);
            propietarios = propietario.consultar("TELEFONO",parametro.getText().toString());
            if(propietarios==null){
                lbl21.setText("Nombre: no Hay Resultados");
                txt21.setText("no Hay Resultados");
            }else{
                for (int i=0;i<propietarios.length;i++){
                    Propietario temp= propietarios[i];
                    txt21.setText(temp.getNombre());
                    txt22.setText(temp.getDomicilio());
                    txt23.setText(temp.getFecha());
                }
            }
        }else if(consultarS.isChecked()){
            cambiarEtiquetas();
            Seguro seguro = new Seguro(this);
            seguros = seguro.consultar("IDSEGURO",parametro.getText().toString());
            if(seguros==null){
                lbl21.setText("Descripcion: no Hay Resultados");
                txt21.setText("no Hay Resultados");
            }else{
                for (int i=0;i<propietarios.length;i++){
                    Seguro temp= seguros[i];
                    txt21.setText(temp.getDescripcion());
                    txt22.setText(temp.getFecha());
                    txt23.setText(temp.getTipo());
                    txt24.setText(temp.getTelefono());
                }
            }
        }else{
            Toast.makeText(this,"Selecciona Una Opcion de Busqueda", Toast.LENGTH_LONG).show();
        }
    }
}
