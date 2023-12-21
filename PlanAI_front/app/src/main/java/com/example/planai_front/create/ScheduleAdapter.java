package com.example.planai_front.create;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.planai_front.R;
import java.util.List;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private List<Schedule> scheduleList;
    private OnItemClickListener listener;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    public interface OnItemClickListener {
        void onEditClick(Schedule schedule); // 수정 버튼 클릭 이벤트
        void onDeleteClick(Schedule schedule); // 삭제 버튼 클릭 이벤트
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
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
        Log.d("Server!!","position:"+position);
        holder.scheduleRecySummary.setText(schedule.getSummary());
        holder.scheduleRecyStartDate.setText(schedule.getStartDate());
        holder.scheduleRecyStartTime.setText(schedule.getStartTime());
        holder.scheduleRecyEndDate.setText(schedule.getEndDate());
        holder.scheduleRedyEndTime.setText(schedule.getEndTime());
        holder.scheduleRecyTag.setText(schedule.getTag());
        holder.scheduleRecyDescription.setText(schedule.getDescription());

        Log.d("Server!!","holderokay");
        Log.d("Server!!","ID: "+schedule.getId());

            Log.d("Server!!","ID: "+schedule.getId());
        holder.itemView.setOnClickListener(v -> {
            boolean isVisible = holder.btnEdit.getVisibility() == View.VISIBLE;
            holder.btnEdit.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            holder.btnDelete.setVisibility(isVisible ? View.GONE : View.VISIBLE);

        holder.btnEdit.setOnClickListener(v -> listener.onEditClick(schedule)); // 수정 버튼 클릭 이벤트 처리
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(schedule)); // 삭제 버튼 클릭 이벤트 처리
        });
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleRecySummary, scheduleRecyStartDate, scheduleRecyStartTime, scheduleRecyEndDate, scheduleRedyEndTime, scheduleRecyTag, scheduleRecyDescription;
        public Button btnEdit, btnDelete;

        public ScheduleViewHolder(View view) {
            super(view);
            scheduleRecySummary = view.findViewById(R.id.scheduleSummaryItemView);
            scheduleRecyStartDate = view.findViewById(R.id.scheduleStartDateItemView);
            scheduleRecyStartTime = view.findViewById(R.id.scheduleStartTimeItemView);
            scheduleRecyEndDate = view.findViewById(R.id.scheduleEndDateItemView);
            scheduleRedyEndTime = view.findViewById(R.id.scheduleEndTimeItemView);
            scheduleRecyTag = view.findViewById(R.id.scheduleTagItemView);
            scheduleRecyDescription = view.findViewById(R.id.scheduleDescriptionItemView);

            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);

            // 버튼을 기본적으로 숨깁니다
            btnEdit.setVisibility(View.GONE);
            btnDelete.setVisibility(View.GONE);
        }
    }
}
