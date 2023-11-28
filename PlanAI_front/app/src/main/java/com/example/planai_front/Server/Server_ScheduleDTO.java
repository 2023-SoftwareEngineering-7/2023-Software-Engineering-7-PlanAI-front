package com.example.planai_front.Server;


import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
public class Server_ScheduleDTO {
    public Server_ScheduleDTO() {
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("startDate")
    @Expose
    private String startDate; // LocalDateTime 대신 String 사용
    @SerializedName("endDate")
    @Expose
    private String endDate;   // LocalDateTime 대신 String 사용
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("ownerId")
    @Expose
    private Long ownerId;
    @SerializedName("tagList")
    @Expose
    private List<String> tagList;

    public Server_ScheduleDTO(String title, String startDate, String endDate, String description, Long ownerId, List<String> tagList) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.description = description;
        this.ownerId = ownerId;

        this.tagList = (tagList != null) ? tagList : new ArrayList<>();
        if(this.tagList.isEmpty()){
            Log.e("Server!!", "empty tagList1");
        }else{
            Log.e("Server!!", tagList.get(0)+"asdf");
        }
        this.tagList = tagList;
    }
// 여기에 getter와 setter 메서드들을 추가합니다.

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    // startDate와 endDate의 경우, 서버가 LocalDateTime을 받아들이지만,
    // 클라이언트에서는 String 형식으로 처리한 후 서버에 맞게 변환하여 전송합니다.

    // toString 메서드도 필요에 따라 추가할 수 있습니다.
}