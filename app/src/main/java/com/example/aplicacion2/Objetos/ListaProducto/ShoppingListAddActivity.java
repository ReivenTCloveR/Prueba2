package com.example.aplicacion2.Objetos.ListaProducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.aplicacion2.Adapters.AdapterProductAdd;
import com.example.aplicacion2.Clases.ShoppingListActivity;
import com.example.aplicacion2.MainUsuarioActivity;
import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbProductos;
import com.example.aplicacion2.db.ProductListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ShoppingListAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, ProductListener {

    RecyclerView listaProductos;
    ImageView btnBack;
    FloatingActionButton addProductos;
    SearchView BuscarProductos;
    CheckBox checkBox;
    AdapterProductAdd adapterProductAdd;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_add);

        listaProductos = findViewById(R.id.lv_addProduct);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));
        DbProductos dbProductos = new DbProductos(ShoppingListAddActivity.this);
        adapterProductAdd = new AdapterProductAdd(this, dbProductos.mostrarPoduct(),this);
        listaProductos.setAdapter(adapterProductAdd);

        BuscarProductos =findViewById(R.id.BuscarProductos);
        BuscarProductos.setOnQueryTextListener(this);


        DbHelper dbhelper = new DbHelper(ShoppingListAddActivity.this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();


        //inicializar btns
        btnBack = findViewById(R.id.imgbtnBack);
        addProductos = findViewById(R.id.addButton);
        checkBox = findViewById(R.id.checkBox);

        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingListActivity.class);
            startActivity(i);
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {return false;}

    @Override
    public boolean onQueryTextChange(String txtbusca) {
        adapterProductAdd.filtro(txtbusca);
        return false;
    }

    @Override
    public void onProductChange(ArrayList<Productos> list) {
        Toast.makeText(this, "Agregar", Toast.LENGTH_SHORT).show();
    }
}