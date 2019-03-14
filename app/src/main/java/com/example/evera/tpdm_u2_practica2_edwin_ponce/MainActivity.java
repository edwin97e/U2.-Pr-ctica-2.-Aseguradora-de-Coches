package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    ListView Lista;
    Propietario[] propietarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Lista = findViewById(R.id.ListaP);
    }
    protected void onStart() {
        Propietario propietario = new Propietario(this);
        propietarios = propietario.consultar();
        String[] telefononombre=null;
        if(propietarios==null){
            telefononombre = new String[1];
            telefononombre[0]="No Hay Proyectos";
        }else{
            telefononombre = new String[propietarios.length];
            for (int i=0;i<propietarios.length;i++){
                Propietario temp= propietarios[i];
                telefononombre[i]=temp.getTelefono()+"\n"+temp.getNombre();
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,telefononombre);
        Lista.setAdapter(adaptador);
        super.onStart();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menup,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Insertar:
                Intent insertar = new Intent(this, Main3Activity.class);
                startActivity(insertar);
                break;
            case R.id.Eliminar:
                break;
            case R.id.Actualizar:

                break;

            case R.id.Consultar:
                Intent Consulta = new Intent(this, Main4Activity.class);
                startActivity(Consulta);
                break;

            case R.id.Seguros:
                Intent Seguros = new Intent(this, Main2Activity.class);
                startActivity(Seguros);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}