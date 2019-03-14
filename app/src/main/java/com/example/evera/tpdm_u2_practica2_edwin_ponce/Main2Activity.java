package com.example.evera.tpdm_u2_practica2_edwin_ponce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main2Activity extends AppCompatActivity {
    ListView Lista;
    Seguro[] seguros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Lista = findViewById(R.id.ListaP);
    }

    @Override
    protected void onStart() {
        Seguro seguro = new Seguro(this);
        seguros = seguro.consultar();
        String[] descripciontipo=null;
        if(seguros==null){
            descripciontipo = new String[1];
            descripciontipo[0]="No Hay Proyectos";
        }else{
            descripciontipo = new String[seguros.length];
            for (int i=0;i<seguros.length;i++){
                Seguro temp= seguros[i];
                descripciontipo[i]=temp.getDescripcion()+"\n"+temp.getTipo();
            }
        }
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(Main2Activity.this,android.R.layout.simple_list_item_1,descripciontipo);
        Lista.setAdapter(adaptador);
        super.onStart();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.Insertar:
                Intent insertar= new Intent(this,Main3Activity.class);
                startActivity(insertar);
                break;
            case R.id.Eliminar:
                break;
            case R.id.Actualizar:

                break;

            case R.id.Consultar:
                break;

            case R.id.Propietarios:
                Intent Propietarios= new Intent(this,MainActivity.class);
                startActivity(Propietarios);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
