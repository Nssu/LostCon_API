package com.example.demo.board.firebase;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import com.example.demo.board.mapper.ItemMapper;
import com.example.demo.board.model.ItemVO;
import com.google.common.base.Utf8;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.nio.cs.UTF_32;

import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;




/*
자신을 제외한 모든 사용자에게 푸시알림 보낼 것임
-> 그다음 수락을 누른 사용자의 로케이션이 오면 response로 다시 보내줘야함
 */
@RestController
@RequestMapping("/firebase")
public class FirebaseController {
    private final String TOPIC = "JavaSampleApproach";

    @Autowired
    AndroidPushNotificationService androidPushNotificationsService;

    @RequestMapping(value = "/help/{what}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<String> sendLocation(@PathVariable int what,@RequestParam int item_key,int user_key) throws JSONException {

        JSONObject body = new JSONObject();

        System.out.println("what : "+what);
        System.out.println("item_key : "+item_key);
        System.out.println("user_key : "+user_key);

        String user1_token ="fUTHMa5Rmj4:APA91bEUnSY30suqDzySyl_D15-5VyP-FNOnVOnRBnLhizcnefqhiIi7tge94qT8sO3ZctKHRgpp_mt4dDEGVy6hoqP-i1-65zlyw31vdFNnuz7NxXz01FDWUCLi0Ivnxw0JWxjU9xkNhmAFPocec1VF7wzvg4mlRg";
//                "c0m8TePx0aA:APA91bErGppnisKVFT-Vc6SgDgWGkfs7lbzflxBupWQ5PnWZ7334Vk4gJpUtiEODDIYkVURcScczQ6cX7NI7s1Tf5xkDk94NzVLeWojc4LFgsCLA-9Yg5FJXAPOF1CGcGsUpBA0K6YNtlihAGhI8CBnjgeuhmsVYCg";
//        array.put("c0m8TePx0aA:APA91bErGppnisKVFT-Vc6SgDgWGkfs7lbzflxBupWQ5PnWZ7334Vk4gJpUtiEODDIYkVURcScczQ6cX7NI7s1Tf5xkDk94NzVLeWojc4LFgsCLA-9Yg5FJXAPOF1CGcGsUpBA0K6YNtlihAGhI8CBnjgeuhmsVYCg");
//        body.put("to", "c0m8TePx0aA:APA91bErGppnisKVFT-Vc6SgDgWGkfs7lbzflxBupWQ5PnWZ7334Vk4gJpUtiEODDIYkVURcScczQ6cX7NI7s1Tf5xkDk94NzVLeWojc4LFgsCLA-9Yg5FJXAPOF1CGcGsUpBA0K6YNtlihAGhI8CBnjgeuhmsVYCg");
        List<String> tokenList = new ArrayList<String>();
        tokenList.add(user1_token);

        JSONArray jsonArray = new JSONArray();
        jsonArray.put(tokenList.get(0));

        body.put("registration_ids",jsonArray);
        /*
        여러명의 사용자에게 토큰을 보내고 싶다면 registration_ids를 보내면 된다.
         */
        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", "Message");
        notification.put("body", "somebody want to know your location!");

        JSONObject data = new JSONObject();
        data.put("Key-1", "JSA Data 1");
        data.put("Key-2", "JSA Data 2");

        body.put("notification", notification);
        body.put("data", data);


        HttpEntity<String> request = new HttpEntity<>(body.toString());


        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();

            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }


}
