package com.example.quera.View;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quera.R;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<String> classNames;
    private LayoutInflater inflater;

    public RecyclerViewAdapter(Context context, ArrayList<String> names) {
        this.classNames = names;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.recycler_view_row, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.rowTextView.setText(classNames.get(position));
    }

    @Override
    public int getItemCount() {
        return classNames.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView rowTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            rowTextView = itemView.findViewById(R.id.rowTextView);
        }
    }
}
