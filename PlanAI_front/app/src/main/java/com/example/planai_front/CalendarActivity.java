package com.example.planai_front;

import com.example.planai_front.Server.Server_ScheduleDTO;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.*;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CalendarActivity {
    public Server_ScheduleDTO EventToScheduleDTO(Event event) {
        SimpleDateFormat simpledateformat = new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss+09:00", Locale.KOREA);

        Server_ScheduleDTO retScheduleDTO = new Server_ScheduleDTO();

        retScheduleDTO.setTitle(event.getSummary());
        retScheduleDTO.setStartDate(simpledateformat.format(event.getStart().getDateTime()));
        retScheduleDTO.setEndDate(simpledateformat.format(event.getEnd().getDateTime()));

        //creator의 id가 string으로 저장되기 때문에 이를 hashCode로 변환하여 사용
        retScheduleDTO.setOwnerId((long)event.getCreator().hashCode());
        // get tag and description
        String des = event.getDescription();

        String L[];
        // 설명에 태그를 #으로 구분하여 저장, ex  ) {descript} #{tag1} #{tag2} => {tag1}, {tag2}가 태그로 저장
        L = des.split("#");

        if(L.length == 0)
            return retScheduleDTO;
        retScheduleDTO.setDescription(L[0]);
        if(L.length == 1)
            return retScheduleDTO;

        List<String> tag = new ArrayList<String>();
        for(int i = 1; i < L.length; i++) {
            tag.add(L[i]);
        }
        retScheduleDTO.setTagList(tag);

        return retScheduleDTO;
    }
}
