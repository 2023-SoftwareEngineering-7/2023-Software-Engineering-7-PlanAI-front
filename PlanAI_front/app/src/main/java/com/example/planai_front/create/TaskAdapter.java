package com.example.planai_front.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.R;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private static List<Task> taskList;
    private static Task thisTask;

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
        public LinearLayout TaskeditDeleteBtn;
        public Button TaskbtnEdit, TaskbtnDelete;
        public TaskViewHolder(View view) {
            super(view);
            taskRecySummary = view.findViewById(R.id.taskSummaryItemView);
            taskRecyDescription = view.findViewById(R.id.taskDescriptionItemView);
            taskRecyDeadLine = view.findViewById(R.id.taskDeadLineItemView);
            taskRecyDeadLineTime = view.findViewById(R.id.taskDeadLineTimeItemView);
            taskRecyTag = view.findViewById(R.id.taskTagItemView);
            taskRecyPriority = view.findViewById(R.id.taskPriorityItemView);

            TaskeditDeleteBtn  = view.findViewById(R.id.TaskeditDeleteBtn);
            TaskbtnDelete = view.findViewById(R.id.TaskbtnDelete);
            TaskbtnEdit = view.findViewById(R.id.TaskbtnEdit);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 버튼의 가시성을 토글합니다.
                    boolean isVisible = TaskeditDeleteBtn.getVisibility() == View.VISIBLE;
                    TaskeditDeleteBtn.setVisibility(isVisible ? View.GONE : View.VISIBLE);
                }
            });

            // Edit 버튼 클릭 리스너
            TaskbtnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 토스트 메시지 표시
                    Toast.makeText(view.getContext(), "Edit 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            // Edit 버튼 클릭 리스너
            TaskbtnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 토스트 메시지 표시
                    Toast.makeText(view.getContext(), "Delete 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
//                    for (Task tempTask:taskList){
//                        if(tempTask.getSummary().equals(thisTask.getSummary())){
//                            taskList.remove(tempTask);
//                        }
//                    }
                }
            });
        }
    }


}
