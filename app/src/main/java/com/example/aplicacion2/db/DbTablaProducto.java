package com.example.aplicacion2.db;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.aplicacion2.Objetos.ListaProducto.ShoppingList;
import com.example.aplicacion2.Objetos.Productos.Productos;

import java.util.ArrayList;

public class DbTablaProducto extends DbHelper{
    Context context;
    SQLiteDatabase db;
    public DbTablaProducto(@Nullable Context context) {
        super(context);
        this.context = context;
    }

//Insertar
    public long insertarTablaProducto(String nombreLista) {

        long id = 0;

        try {

            DbHelper dbhelper = new DbHelper(context);
            this.db = dbhelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombreLista", nombreLista);


            id = db.insert(TABLE_LIST_PRODUCTOS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    //mostrar
    @SuppressLint("Recycle")
    public ArrayList<ShoppingList> mostrarTablaProducto(){


        DbHelper dbhelper = new DbHelper(context);
        this.db = dbhelper.getWritableDatabase();

        ArrayList<ShoppingList> listshoppingList = new ArrayList<>();
        ShoppingList shoppingList;
        Cursor cursorListaProducto;

        cursorListaProducto = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTO, null);

        if(cursorListaProducto.moveToFirst()){
            do{
                shoppingList = new ShoppingList();
                shoppingList.setId_list(cursorListaProducto.getInt(0));
                shoppingList.setNombre_list(cursorListaProducto.getString(1));

                listshoppingList.add(shoppingList);
            }while (cursorListaProducto.moveToNext());
        }
        return listshoppingList;
    }


}
