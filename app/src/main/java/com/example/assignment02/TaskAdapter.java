package com.example.assignment02;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyVH> {
    private Context context;
    ArrayList<modelTask> data;

    public TaskAdapter(Context context, ArrayList<modelTask> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public TaskAdapter.MyVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.MyVH holder, int position) {



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyVH extends RecyclerView.ViewHolder {
        TextView std_rollnum,std_sabaq, std_sabaqi, std_manzil;
        public MyVH(@NonNull View itemView) {
            super(itemView);


        }
    }
}
