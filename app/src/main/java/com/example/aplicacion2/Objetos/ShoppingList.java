package com.example.aplicacion2.Objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ShoppingList implements Serializable {

    private int id_list;
    private String nombre_list;
    private int cantidad_productos;


    public ShoppingList(){}

    public ShoppingList(int id_list, String nombre_list, int cantidad_productos) {
        this.id_list = id_list;
        this.nombre_list = nombre_list;
        this.cantidad_productos = cantidad_productos;
    }

    public int getId_list() {
        return id_list;
    }

    public void getId_list(int id) {
        this.id_list = id;
    }

    public String getNombre_list() {
        return nombre_list;
    }

    public void getNombre_list(String nombre) {
        this.nombre_list = nombre;
    }

    public int getCantidad_productos() {
        return cantidad_productos;
    }

    public void setCantidad_productos(int cantidad) {
        this.cantidad_productos = cantidad;
    }

}
