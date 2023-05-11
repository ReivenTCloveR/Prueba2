package com.example.aplicacion2.db;

import com.example.aplicacion2.Objetos.Productos.Productos;

import java.util.ArrayList;

public interface ProductListener {
    void onProductChange(ArrayList<Productos> list);

}
