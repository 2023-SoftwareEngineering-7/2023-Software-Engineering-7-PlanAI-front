package com.example.planai_front;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class OpenAIActivity {
    public static String chatGPT(String prompt) {
        String url = "https://api.openai.com/v1/chat/completions";
        String apiKey = "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"; // 여기에 고유 API 키 삽입, 만약 크레딧이 없다면 작동X
        String model = "gpt-3.5-turbo";
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Authorization", "Bearer " + apiKey);
            connection.setRequestProperty("Content-Type", "application/json; utf-8");
            connection.setDoOutput(true);

            // The request body
            String body = "{\"model\": \"" + model + "\", \"messages\": [{\"role\": \"user\", \"content\": \"" + prompt + "\"}]}";
            connection.setDoOutput(true);
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
            writer.write(body);
            writer.flush();
            writer.close();

            // Response from ChatGPT
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String line;

            StringBuffer response = new StringBuffer();

            while ((line = br.readLine()) != null) {
                response.append(line);
            }
            br.close();

            // calls the method to extract the message.
            return extractMessageFromJSONResponse(response.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String extractMessageFromJSONResponse(String response) {
        int start = response.indexOf("content")+ 11;

        int end = response.indexOf("\"", start);

        return response.substring(start, end);
    }

    public String askScheduleOptimization (List<Event> list) {
        String prompt = "다음과 같은 일정이 있을 때, 이 일정을 수행하기 좋게 최적화해줘.\n";

        for(Event item : list) {
            String s = "";

            DateTime st = item.getStart().getDateTime();
            DateTime et = item.getEnd().getDateTime();

            if(st != null) {
                s += "시작 시간 : " + st.toString();
            }
            else {
                s += "시작 시간 : 임의";
            }
            if(et != null) {
                s += ", 종료 시간 : " + et.toString();
            }
            else {
                s += "종료 시간 : 임의";
            }
            s += "\n";

            prompt += s;
        }

        return prompt;
    }
}
