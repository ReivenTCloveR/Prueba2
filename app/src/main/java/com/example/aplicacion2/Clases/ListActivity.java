package com.example.aplicacion2.Clases;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.aplicacion2.Adapters.AdapterProduct;
import com.example.aplicacion2.Objetos.ProductoAdd;
import com.example.aplicacion2.Objetos.Productos;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbProductos;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    RecyclerView listaProductos;
    ArrayList<Productos> ALProducto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listaProductos = findViewById(R.id.lvProducts);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));

        DbProductos dbProductos = new DbProductos(ListActivity.this);

        ALProducto = new ArrayList<>();

        AdapterProduct adapterProduct = new AdapterProduct(this, dbProductos.mostrarPoduct());
        listaProductos.setAdapter(adapterProduct);
         DbHelper dbhelper = new DbHelper(ListActivity.this);
         SQLiteDatabase db = dbhelper.getWritableDatabase();

         if(db != null){
             Toast.makeText(this, "Lista de Compra", Toast.LENGTH_SHORT).show();
         }else Toast.makeText(this, "Error al cargar la Lista de Compra", Toast.LENGTH_SHORT).show();


    }



    public void IrAdd(View view) {
        Intent i = new Intent(this, ProductoAdd.class);
        startActivity(i);    }


}