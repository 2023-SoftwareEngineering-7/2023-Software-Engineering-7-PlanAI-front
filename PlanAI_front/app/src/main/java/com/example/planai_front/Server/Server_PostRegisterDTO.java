package com.example.planai_front.Server;



import android.util.Log;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
public class Server_PostRegisterDTO {
    public Server_PostRegisterDTO() {
    }

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content; // LocalDateTime 대신 String 사용
    @SerializedName("author_id")
    @Expose
    private Long author_id;
    @SerializedName("tagList")
    @Expose
    private List<String> tagList;

    public Server_PostRegisterDTO(String title, String content, Long author_id, List<String> tagList) {
        this.title = title;
        this.content = content;
        this.author_id = author_id;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content= content;
    }
    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }


    // toString 메서드도 필요에 따라 추가할 수 있습니다.
}

