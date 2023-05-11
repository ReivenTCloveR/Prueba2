package com.example.aplicacion2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion2.Objetos.ListaProducto.ShoppingListAddActivity;
import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.DbHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterProductAdd extends RecyclerView.Adapter<AdapterProductAdd.ViewHolderAddProduct>  {

    Context context;
    public static ArrayList<Productos> list;
    public static ArrayList<Productos> originallist;
    ArrayList<Productos> list_apoyo = new ArrayList<>();


    public AdapterProductAdd(Context context, ArrayList<Productos> list) {
        this.context = context;
        AdapterProductAdd.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);

    }

    @NonNull
    @Override
    public AdapterProductAdd.ViewHolderAddProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_lvproductadd ,parent,false);
        return new ViewHolderAddProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductAdd.ViewHolderAddProduct holder, int position) {

        String nombreProducto = list.get(position).getNombre();
        holder.MostrarProducto.setText(nombreProducto);

        holder.AnadirLista.setOnClickListener(v -> {
            int idProducto = list.get(position).getId();
            int cantidadComprar = Integer.parseInt(holder.cantidadComprarEditText.getText().toString().trim());

            // Llamamos al método insertarTablaProducto del DBHelper
            DbHelper db = new DbHelper(context);
            id_join = .(id_tabla, etNombreLista.toString(),

            db.dbTablaProducto.insertarTablaProducto(id_tabla, etNombreLista.toString(), idProducto, cantidadComprar);

            // Cerramos la conexión con la base de datos
            db.close();
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void filtro (String txtBuscar){
        int longitud = txtBuscar.length();
        if ( longitud== 0){
            list.clear();
            list.addAll(originallist);
        }else {
            List<Productos> colleccion =  list.stream()
                    .filter(i->i.getTipo().toLowerCase().contains(txtBuscar.toLowerCase()))
                    .collect(Collectors.toList());
            list.clear();
            list.addAll(colleccion);
        }
        notifyDataSetChanged();
    }



    public static class ViewHolderAddProduct extends RecyclerView.ViewHolder {
        TextView MostrarProducto;
        EditText cantidadComprarEditText;
        Button AnadirLista;
        Intent intent;
        ViewHolderAddProduct(View itemView){
            super(itemView);
            MostrarProducto=itemView.findViewById(R.id.NombreLista);
            cantidadComprarEditText=itemView.findViewById(R.id.etCantidadComprar);
            AnadirLista=itemView.findViewById(R.id.AñadirLista);

        }
    }
}
