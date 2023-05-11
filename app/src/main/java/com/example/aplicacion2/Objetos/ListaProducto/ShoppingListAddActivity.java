package com.example.aplicacion2.Objetos.ListaProducto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;


import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.aplicacion2.Adapters.AdapterProductAdd;
import com.example.aplicacion2.Clases.ShoppingListActivity;
import com.example.aplicacion2.MainUsuarioActivity;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;
import com.example.aplicacion2.db.DbProductos;
import com.example.aplicacion2.db.DbTablaProducto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class ShoppingListAddActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    RecyclerView listaProductos;
    ImageView btnBack;
    FloatingActionButton addProductos;
    SearchView BuscarProductos;
    Button AñadirLista;
    AdapterProductAdd adapterProductAdd;

    EditText etNombreLista,etCantidadComprar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list_add);


        etNombreLista = findViewById(R.id.etNombreLista);
        etCantidadComprar = findViewById(R.id.etCantidadComprar);

        listaProductos = findViewById(R.id.lv_addProduct);
        listaProductos.setLayoutManager(new LinearLayoutManager(this));
        DbProductos dbProductos = new DbProductos(ShoppingListAddActivity.this);
        adapterProductAdd = new AdapterProductAdd(this, dbProductos.mostrarPoduct());
        listaProductos.setAdapter(adapterProductAdd);

        BuscarProductos =findViewById(R.id.BuscarProductos);
        BuscarProductos.setOnQueryTextListener(this);


        DbHelper dbhelper = new DbHelper(ShoppingListAddActivity.this);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        //inicializar btns
        btnBack = findViewById(R.id.imgbtnBack);
        addProductos = findViewById(R.id.addButton);


        btnBack.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingListActivity.class);
            startActivity(i);
        });


/*
        AñadirLista.setOnClickListener(v -> {
            DbTablaProducto dbTablaProducto = new DbTablaProducto(ShoppingListAddActivity.this);
            int id_tabla = dbTablaProducto.obtenerUltimaIdListaProductos();
            Bundle extra = getIntent().getExtras();
            int id_producto = extra.getInt("ID_Producto");
            long id_join;

            try {
                id_join = dbTablaProducto.insertarTablaProducto(id_tabla, etNombreLista.toString(), id_producto, Integer.parseInt(etCantidadComprar.getText().toString()));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (id_join > 0) {
                Toast.makeText(this, "SE AÑADIO EL PRODUCTO A SU LISTA", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(this, "ERROR AL AÑADIR EL PRODUCTO", Toast.LENGTH_SHORT).show();
            }


        });*/


        //AñadirListaProducto

        addProductos.setOnClickListener(v -> {
            Intent i = new Intent(this, ShoppingListAddActivity.class);
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


}