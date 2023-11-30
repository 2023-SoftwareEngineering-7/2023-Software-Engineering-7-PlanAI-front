package com.example.planai_front.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> taskList;

    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item_day_main, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskRecySummary.setText(task.getSummary());
        holder.taskRecyDescription.setText(task.getDescription());
        holder.taskRecyDeadLine.setText(task.getDeadLineDate());
        holder.taskRecyDeadLineTime.setText(task.getDeadLineTime());
        holder.taskRecyTag.setText(task.getTag());
        holder.taskRecyPriority.setText(task.getPriority());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskRecySummary, taskRecyDescription, taskRecyDeadLine, taskRecyDeadLineTime, taskRecyTag,taskRecyPriority;

        public TaskViewHolder(View view) {
            super(view);
            taskRecySummary = view.findViewById(R.id.taskSummaryItemView);
            taskRecyDescription = view.findViewById(R.id.taskDescriptionItemView);
            taskRecyDeadLine = view.findViewById(R.id.taskDeadLineItemView);
            taskRecyDeadLineTime = view.findViewById(R.id.taskDeadLineTimeItemView);
            taskRecyTag = view.findViewById(R.id.taskTagItemView);
            taskRecyPriority = view.findViewById(R.id.taskPriorityItemView);
        }
    }
}