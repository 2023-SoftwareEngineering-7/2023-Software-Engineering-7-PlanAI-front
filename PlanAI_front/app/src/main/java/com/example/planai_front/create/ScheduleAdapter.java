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
        //holder.idTextView.setText(schedule.getId());
        holder.summaryTextView.setText(schedule.getSummary());
        holder.descriptionTextView.setText(schedule.getDescription());
        holder.collaboratorsTextView.setText(schedule.getCollaborators());
        holder.startTextView.setText(schedule.getStart());
        holder.endTextView.setText(schedule.getEnd());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView summaryTextView,descriptionTextView,
                        collaboratorsTextView,startTextView,endTextView;

        public ScheduleViewHolder(View view) {
            super(view);
            summaryTextView = view.findViewById(R.id.summaryTextView);
            startTextView = view.findViewById(R.id.startTextView);
            endTextView = view.findViewById(R.id.endTextView);
            descriptionTextView = view.findViewById(R.id.descriptionTextView);
            collaboratorsTextView = view.findViewById(R.id.collaboratorsTextView);


        }
    }
}
