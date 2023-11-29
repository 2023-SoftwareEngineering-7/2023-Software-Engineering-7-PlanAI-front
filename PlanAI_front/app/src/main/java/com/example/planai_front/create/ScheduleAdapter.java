package com.example.planai_front.create;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.planai_front.R;

import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private List<Schedule> scheduleList;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_day_main, parent, false);
        return new ScheduleViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.scheduleRecySummary.setText(schedule.getSummary());
        holder.scheduleRedyStartDate.setText(schedule.getStartDate());
        holder.scheduleRecyEndDate.setText(schedule.getEndDate());
        holder.scheduleRecyTag.setText(schedule.getTag());
        holder.scheduleRecyDescription.setText(schedule.getDescription());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleRecySummary, scheduleRedyStartDate, scheduleRecyEndDate, scheduleRecyTag, scheduleRecyDescription;

        public ScheduleViewHolder(View view) {
            super(view);
            scheduleRecySummary = view.findViewById(R.id.scheduleSummaryView);
            scheduleRedyStartDate = view.findViewById(R.id.scheduleStartDateView);
            scheduleRecyEndDate = view.findViewById(R.id.scheduleEndDateView);
            scheduleRecyTag = view.findViewById(R.id.scheduleTagItemView);
            scheduleRecyDescription = view.findViewById(R.id.scheduleDescriptionView);
        }
    }
}