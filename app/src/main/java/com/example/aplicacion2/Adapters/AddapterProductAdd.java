package com.example.aplicacion2.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.aplicacion2.Objetos.Productos.ProductoViewActivity;
import com.example.aplicacion2.Objetos.Productos.Productos;
import com.example.aplicacion2.R;

import java.util.ArrayList;

public class AddapterProductAdd extends RecyclerView.Adapter<AddapterProductAdd.ViewHolderAddProduct> {

    Context context;
    public static ArrayList<Productos> list;
    public static ArrayList<Productos> originallist;

    public AddapterProductAdd(Context context, ArrayList<Productos> list ) {
        this.context = context;
        AddapterProductAdd.list = list;
        originallist = new ArrayList<>();
        originallist.addAll(list);
    }

    @NonNull
    @Override
    public AddapterProductAdd.ViewHolderAddProduct onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_lvproductadd ,parent,false);
        return new AddapterProductAdd.ViewHolderAddProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddapterProductAdd.ViewHolderAddProduct holder, int position) {
        String txtProducto = list.get(position).getNombre();
        holder.MostrarProducto.setText(txtProducto);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolderAddProduct extends RecyclerView.ViewHolder {
        TextView MostrarProducto;
        ViewHolderAddProduct(View itemView){
            super(itemView);
            MostrarProducto=itemView.findViewById(R.id.NombreLista);

            itemView.setOnClickListener(view -> {
                Context context =view.getContext();
                Intent intent = new Intent(context, ProductoViewActivity.class);

            });
        }
    }
}
