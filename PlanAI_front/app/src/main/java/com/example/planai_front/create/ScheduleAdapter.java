package com.example.planai_front.create;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleAdapter.ScheduleViewHolder> {
    private static List<Schedule> scheduleList;
    private static Schedule thisSchedule;

    public ScheduleAdapter(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onEditClick(Schedule schedule);
        void onDeleteClick(Schedule schedule);
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
        thisSchedule = scheduleList.get(position);
        holder.scheduleRecySummary.setText(thisSchedule.getSummary());
        holder.scheduleRecyStartDate.setText(thisSchedule.getStartDate());
        holder.scheduleRecyStartTime.setText(thisSchedule.getStartTime());
        holder.scheduleRecyEndDate.setText(thisSchedule.getEndDate());
        holder.scheduleRecyEndTime.setText(thisSchedule.getEndTime());
        holder.scheduleRecyTag.setText(thisSchedule.getTag());
        holder.scheduleRecyDescription.setText(thisSchedule.getDescription());
    }

    @Override
    public int getItemCount() {
        return scheduleList.size();
    }

    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleRecySummary, scheduleRecyStartDate, scheduleRecyStartTime, scheduleRecyEndDate, scheduleRecyEndTime, scheduleRecyTag, scheduleRecyDescription;
        public LinearLayout editDeleteBtn;
        public Button btnEdit, btnDelete;
        private Context context;

        public ScheduleViewHolder(View view) {
            super(view);
            scheduleRecySummary = view.findViewById(R.id.scheduleSummaryItemView);
            scheduleRecyStartDate = view.findViewById(R.id.scheduleStartDateItemView);
            scheduleRecyStartTime = view.findViewById(R.id.scheduleStartTimeItemView);
            scheduleRecyEndDate = view.findViewById(R.id.scheduleEndDateItemView);
            scheduleRecyEndTime = view.findViewById(R.id.scheduleEndTimeItemView);
            scheduleRecyTag = view.findViewById(R.id.scheduleTagItemView);
            scheduleRecyDescription = view.findViewById(R.id.scheduleDescriptionItemView);

            editDeleteBtn = view.findViewById(R.id.editDeleteBtn);
            btnEdit = view.findViewById(R.id.btnEdit);
            btnDelete = view.findViewById(R.id.btnDelete);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 버튼의 가시성을 토글합니다.
                    boolean isVisible = editDeleteBtn.getVisibility() == View.VISIBLE;
                    editDeleteBtn.setVisibility(isVisible ? View.GONE : View.VISIBLE);
                }
            });


            // Edit 버튼 클릭 리스너
            btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 토스트 메시지 표시
                    Toast.makeText(view.getContext(), "Edit 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
//                    try {
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//
//                        intent.setData(Uri.parse("content://com.android.calendar/time"));
//
//                        // 인텐트가 실행 가능한지 확인
//                        if (intent.resolveActivity(context.getPackageManager()) != null) {
//                            context.startActivity(intent);
//                        } else {
//                            // 구글 캘린더 앱이 설치되어 있지 않은 경우
//                            Toast.makeText(context, "구글 캘린더 앱이 설치되어 있지 않습니다.", Toast.LENGTH_SHORT).show();
//                        }
//                    }catch (Exception e){
//                        Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
                }
            });

            // Edit 버튼 클릭 리스너
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 토스트 메시지 표시
                    Toast.makeText(view.getContext(), "Delete 버튼이 눌렸습니다.", Toast.LENGTH_SHORT).show();
                    for (Schedule tempSchedule:scheduleList){
                        if(tempSchedule.getSummary().equals(thisSchedule.getSummary())){
                            scheduleList.remove(tempSchedule);
                        }
                    }
                }
            });
        }


    }


}
