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

    // 생성자, 스케줄 리스트 초기화
    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    // 뷰홀더 생성, 레이아웃 인플레이트
    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_item_day_main, parent, false);
        return new ScheduleViewHolder(itemView);
    }

    // 데이터 바인딩, 스케줄 정보 뷰에 설정
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);
        holder.summaryTextView.setText(schedule.getSummary());
        holder.descriptionTextView.setText(schedule.getDescription());
        holder.collaboratorsTextView.setText(schedule.getCollaborators());
        holder.startTextView.setText(schedule.getStartDate());
        holder.endTextView.setText(schedule.getEndDate());
    }

    // 아이템 개수 반환
    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    // 뷰홀더 정의, 스케줄 정보 표시할 뷰 참조
    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView summaryTextView, descriptionTextView, collaboratorsTextView, startTextView, endTextView;

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
