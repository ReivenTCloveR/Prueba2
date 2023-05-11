package com.example.aplicacion2.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;
import com.example.aplicacion2.db.ProductListener;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdapterProductAdd extends RecyclerView.Adapter<AdapterProductAdd.ViewHolderAddProduct>  {

    Context context;
    public static ArrayList<Productos> list;
    public static ArrayList<Productos> originallist;
    ArrayList<Productos> list_apoyo = new ArrayList<>();
    ProductListener productoListener;

    public AdapterProductAdd(Context context, ArrayList<Productos> list, ProductListener productListener) {
        this.context = context;
        AdapterProductAdd.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);
        this.productoListener = productListener;
    }

    @NonNull
    @Override
    public AdapterProductAdd.ViewHolderAddProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_lvproductadd ,parent,false);
        return new ViewHolderAddProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterProductAdd.ViewHolderAddProduct holder, int position) {
        String txtProducto = list.get(position).getNombre();
        holder.MostrarProducto.setText(txtProducto);
        holder.checkBox.setOnClickListener(v -> {
            if (list != null && list.size()>0){
                holder.checkBox.setText(String.valueOf(list.get(position).getCantidad()));
                if (holder.checkBox.isChecked()){
                    list_apoyo.add(list.get(position));
                }else {
                    list_apoyo.remove(list.get(position));
                }
                productoListener.onProductChange(list_apoyo);
            }
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
        CheckBox checkBox;
        ViewHolderAddProduct(View itemView){
            super(itemView);
            MostrarProducto=itemView.findViewById(R.id.NombreLista);
            checkBox = itemView.findViewById(R.id.checkBox);
            itemView.setOnClickListener(view -> {
                //Context context =view.getContext();
               // Intent intent = new Intent(context, ProductoViewActivity.class);
                //TODO tengo que añadir las cosas a la lista
            });
        }
    }
}
