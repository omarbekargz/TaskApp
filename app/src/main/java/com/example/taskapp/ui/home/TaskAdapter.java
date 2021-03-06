package com.example.taskapp.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapp.R;
import com.example.taskapp.interfaces.OnItemClickListener;
import com.example.taskapp.ui.models.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.HomeHolder> {

    private List<Task> list = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setList(List<Task> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    public TaskAdapter() {

    }

    @NonNull
    @Override
    public HomeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_task, parent, false);
        return new HomeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeHolder holder, int position) {
        holder.bind(list.get(position));
        if (position % 2 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#88AAA5"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#6DBDA0"));
        }

        holder.itemView.setOnClickListener(v -> onItemClickListener.onClick(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addItems(List<Task> tasks) {
        list.addAll(tasks);
        notifyDataSetChanged();
    }

    public void addItem(Task task) {
        list.add(task);
        notifyItemInserted(list.size());
    }


    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void removeItem(int position) {
        this.list.remove(position);
        notifyItemRemoved(position);
    }

    public Task getItem(int position) {
        return list.get(position);
    }

    public void updateItem(Task task, int position) {
        list.set(position, task);
        notifyItemChanged(position);
    }


    public class HomeHolder extends RecyclerView.ViewHolder {
        private TextView title, date;

        public HomeHolder(@NonNull View itemView) {
            super(itemView);

            //longClick
            itemView.setOnLongClickListener(v -> {
                onItemClickListener.onLongClick(getAdapterPosition());
                return true;
            });
            //init
            title = itemView.findViewById(R.id.task_tv_title);
            date = itemView.findViewById(R.id.tv_date);

        }

        public void bind(Task task) {
            title.setText(task.getTitle());
            date.setText(task.getCreatedAd());

        }
    }

}
