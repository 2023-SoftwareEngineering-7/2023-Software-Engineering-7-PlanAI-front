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
    private OnItemClickListener mListener;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_day_main, parent, false);
        return new ScheduleViewHolder(itemView, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.scheduleRecySummary.setText(schedule.getSummary());
        holder.scheduleRecyStartDate.setText(schedule.getStartDate());
        holder.scheduleRecyStartTime.setText(schedule.getStartTime());
        holder.scheduleRecyEndDate.setText(schedule.getEndDate());
        holder.scheduleRedyEndTime.setText(schedule.getEndTime());
        holder.scheduleRecyTag.setText(schedule.getTag());
        holder.scheduleRecyDescription.setText(schedule.getDescription());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleRecySummary, scheduleRecyStartDate, scheduleRecyStartTime, scheduleRecyEndDate, scheduleRedyEndTime, scheduleRecyTag, scheduleRecyDescription;

        public ScheduleViewHolder(View view, final OnItemClickListener listener) {
            super(view);
            scheduleRecySummary = view.findViewById(R.id.scheduleSummaryItemView);
            scheduleRecyStartDate = view.findViewById(R.id.scheduleStartDateItemView);
            scheduleRecyStartTime = view.findViewById(R.id.scheduleStartTimeItemView);
            scheduleRecyEndDate = view.findViewById(R.id.scheduleEndDateItemView);
            scheduleRedyEndTime = view.findViewById(R.id.scheduleEndTimeItemView);
            scheduleRecyTag = view.findViewById(R.id.scheduleTagItemView);
            scheduleRecyDescription = view.findViewById(R.id.scheduleDescriptionItemView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}