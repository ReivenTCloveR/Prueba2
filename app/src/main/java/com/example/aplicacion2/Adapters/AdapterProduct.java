package com.example.aplicacion2.Adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.aplicacion2.Objetos.ProductoView;
import com.example.aplicacion2.Objetos.Productos;
import com.example.aplicacion2.R;

import java.util.ArrayList;

public class AdapterProduct extends RecyclerView.Adapter<AdapterProduct.ViewHolder> {

    Context context;
    public static ArrayList<Productos> list;


    public AdapterProduct(Context context, ArrayList<Productos> list) {
        this.context = context;
        AdapterProduct.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.design_lvproduct,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull AdapterProduct.ViewHolder holder, int position) {
        String txtProducto = list.get(position).getNombre();
        int txtPrecio = list.get(position).getPrecio();
        String txtUbicacion = list.get(position).getUbicacion();
        int txtCantidad = list.get(position).getCantidad();



        holder.MostrarProducto.setText(txtProducto);
        holder.MostrarPrecio.setText(String.valueOf(txtPrecio));
        holder.MostrarUbicacion.setText(txtUbicacion);
        holder.MostrarCantidad.setText(String.valueOf(txtCantidad));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView MostrarProducto, MostrarPrecio, MostrarUbicacion, MostrarCantidad;
        ViewHolder(View itemView){
            super(itemView);
            MostrarProducto=itemView.findViewById(R.id.MostrarProducto);
            MostrarPrecio=itemView.findViewById(R.id.MostrarPrecio);
            MostrarUbicacion=itemView.findViewById(R.id.MostrarUbicacion);
            MostrarCantidad=itemView.findViewById(R.id.MostrarCantidad);

            itemView.setOnClickListener(view -> {
                Context context =view.getContext();
                Intent intent = new Intent(context, ProductoView.class);
                intent.putExtra("ID",list.get(getAdapterPosition()).getId());
                context.startActivities(new Intent[]{intent});
            });
        }

    }


}
