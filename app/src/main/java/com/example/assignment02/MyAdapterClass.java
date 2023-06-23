package com.example.assignment02;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.util.ArrayList;


public class MyAdapterClass extends Adapter<MyAdapterClass.MyViewHolder> {
    private Context context;
    ArrayList<Model> data;

    public MyAdapterClass(Context context, ArrayList<Model> data) {
        this.context=context;
        this.data = data;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singlerow, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.std_rollnum.setText(data.get(position).getRollNum());
        holder.std_name.setText(data.get(position).getName());
        holder.std_age.setText(data.get(position).getAge());
        holder.std_class.setText(data.get(position).getClss());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launch the new activity here
                Intent intent = new Intent(context, SabaqActivity.class);
                intent.putExtra("rollnum",data.get(position).getRollNum());
                intent.putExtra("name", data.get(position).getName());
                intent.putExtra("age", data.get(position).getAge());
                intent.putExtra("stdclass", data.get(position).getClss());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView  std_rollnum,std_name, std_age, std_class;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            std_rollnum=itemView.findViewById(R.id.displayRollNum);
            std_name=itemView.findViewById(R.id.displayname);
            std_age=itemView.findViewById(R.id.displayage);
            std_class=itemView.findViewById(R.id.displayclass);


        }
    }
}