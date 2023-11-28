package com.example.planai_front.create;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.R;

import java.util.List;

public class CombinedAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // View Type 상수 정의
    private static final int VIEW_TYPE_SCHEDULE = 0;
    private static final int VIEW_TYPE_TASK = 1;

    private List<CalendarItem> itemList; // Task와 Schedule을 포함하는 리스트

    public CombinedAdapter(List<CalendarItem> itemList) {
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // View Type에 따라 다른 뷰홀더 생성
        if (viewType == VIEW_TYPE_SCHEDULE) {
            View itemView = inflater.inflate(R.layout.schedule_item_day_main, parent, false);
            return new ScheduleViewHolder(itemView);
        } else if (viewType == VIEW_TYPE_TASK) {
            View itemView = inflater.inflate(R.layout.task_item_day_main, parent, false);
            return new TaskViewHolder(itemView);
        }

        throw new IllegalArgumentException("Invalid view type");
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position < itemList.size()) {
            CalendarItem item = itemList.get(position);

            if (item != null) {
                // 뷰홀더에 데이터 바인딩
                if (holder instanceof ScheduleViewHolder && item instanceof Schedule) {
                    ((ScheduleViewHolder) holder).bind((Schedule) item);
                } else if (holder instanceof TaskViewHolder && item instanceof Task) {
                    ((TaskViewHolder) holder).bind((Task) item);
                }
            } else {
                // item이 null일 때 처리
                Log.e("CombinedAdapter", "Item at position " + position + " is null");
            }
        } else {
            // position이 itemList의 범위를 벗어날 때 처리
            Log.e("CombinedAdapter", "Invalid position: " + position);
        }

    }

    @Override
    public int getItemViewType(int position) {
        CalendarItem item = itemList.get(position);

        // 아이템의 클래스 타입에 따라 View Type 지정
        if (item instanceof Schedule) {
            return VIEW_TYPE_SCHEDULE;
        } else if (item instanceof Task) {
            return VIEW_TYPE_TASK;
        }

        throw new IllegalArgumentException("Invalid item type");
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Schedule 아이템을 표시하는 뷰홀더
    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView summaryItemText, descriptionItemText, tagItemText, startItemText, endItemText;

        public ScheduleViewHolder(View view) {
            super(view);
            summaryItemText = view.findViewById(R.id.scheduleSummaryItemView);
            startItemText = view.findViewById(R.id.scheduleStartDateItemView);
            endItemText = view.findViewById(R.id.scheduleEndDateItemView);
            descriptionItemText = view.findViewById(R.id.scheduleDescriptionItemView);
            tagItemText = view.findViewById(R.id.scheduleTagItemView);
        }

        // Schedule 데이터를 뷰에 바인딩
        public void bind(Schedule schedule) {
            summaryItemText.setText(schedule.getSummary());
            descriptionItemText.setText(schedule.getDescription());
            tagItemText.setText(schedule.getTag());
            startItemText.setText(schedule.getStartDate());
            endItemText.setText(schedule.getEndDate());
        }
    }

    // Task 아이템을 표시하는 뷰홀더
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView taskSummaryItemView, taskDescriptionItemText, taskDeadLineItemText, taskTagItemText, taskPriorityItemText;

        public TaskViewHolder(View view) {
            super(view);
            taskSummaryItemView = view.findViewById(R.id.taskSummaryItemView);
            taskDescriptionItemText = view.findViewById(R.id.taskDescriptionItemView);
            taskDeadLineItemText = view.findViewById(R.id.taskDeadLineItemView);
            taskTagItemText = view.findViewById(R.id.taskTagItemView);
            taskPriorityItemText = view.findViewById(R.id.taskPriorityItemView);
        }

        // Task 데이터를 뷰에 바인딩
        public void bind(Task task) {
            taskSummaryItemView.setText(task.getSummary());
            taskDescriptionItemText.setText(task.getDescription());
            taskDeadLineItemText.setText(task.getDeadLineDate());
            taskTagItemText.setText(task.getTag());
            taskPriorityItemText.setText(task.getPriority());
        }
    }
}