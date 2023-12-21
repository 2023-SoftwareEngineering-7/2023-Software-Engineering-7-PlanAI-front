package com.example.planai_front.create;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.planai_front.R;
import java.util.List;
public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private static List<Schedule> scheduleList;
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
        return new ScheduleViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        Schedule schedule = scheduleList.get(position);

        holder.scheduleRecySummary.setText(schedule.getSummary());
        // 나머지 텍스트뷰 설정 코드...

        // 버튼 초기 상태를 숨김으로 설정
        holder.btnEdit.setVisibility(View.GONE);
        holder.btnDelete.setVisibility(View.GONE);

        // 아이템 클릭 리스너 설정
        holder.itemView.setOnClickListener(v -> {
            // 버튼의 가시성 토글
            boolean isVisible = holder.editDeleteBtn.getVisibility() == View.VISIBLE;
            holder.editDeleteBtn.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            holder.btnEdit.setVisibility(isVisible ? View.GONE : View.VISIBLE);
            holder.btnDelete.setVisibility(isVisible ? View.GONE : View.VISIBLE);

            // 로그 출력
            Log.d("Server!!", "Item Clicked: " + schedule.getId());
        });

        // 수정 버튼 클릭 리스너
        holder.btnEdit.setOnClickListener(v -> {
            if (listener != null) {
                listener.onEditClick(schedule);
                Log.d("Server!!", "Edit Clicked: " + schedule.getId());
            }
        });

        // 삭제 버튼 클릭 리스너
        holder.btnDelete.setOnClickListener(v -> {
            if (listener != null) {
                listener.onDeleteClick(schedule);
                Log.d("Server!!", "Delete Clicked: " + schedule.getId());
            }
        });
    }


    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleRecySummary, scheduleRecyStartDate, scheduleRecyStartTime, scheduleRecyEndDate, scheduleRedyEndTime, scheduleRecyTag, scheduleRecyDescription;
        public Button btnEdit, btnDelete;
        public LinearLayout editDeleteBtn;

        public ScheduleViewHolder(View view, OnItemClickListener listener) {
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

            editDeleteBtn = view.findViewById(R.id.editDeleteBtn);

            btnEdit.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("Server!!", "Edit Button Clicked: ID - " + scheduleList.get(position).getId());
                    listener.onEditClick(scheduleList.get(position));
                }
            });

            btnDelete.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Log.d("Server!!", "Delete Button Clicked: ID - " + scheduleList.get(position).getId());
                    listener.onDeleteClick(scheduleList.get(position));
                }
            });

            // 버튼을 기본적으로 숨깁니다
            btnEdit.setVisibility(View.GONE);

        }
    }
}
