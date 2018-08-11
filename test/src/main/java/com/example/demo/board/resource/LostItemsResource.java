package com.example.demo.board.resource;

import com.example.demo.board.firebase.AndroidPushNotificationService;
import com.example.demo.board.mapper.LostItemMapper;
import com.example.demo.board.model.GpsVO;
import com.example.demo.board.model.LostItemVO;
import com.example.demo.board.model.PortNumVO;
import com.google.api.client.json.Json;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/lost/request")
public class LostItemsResource {

    @Autowired
    AndroidPushNotificationService androidPushNotificationsService;

    private LostItemMapper lostItemMapper;
    private String baseUrl = "http://localhost:8080";
    private float longitude=0;
    private float latitude=0;

    public LostItemsResource(LostItemMapper lostItemMapper){
        this.lostItemMapper=lostItemMapper;
    }

    public void pushFirebase(String notification_title,String notification_body,int portNum,String uuid){
        JSONObject body = new JSONObject();
        String user1_token = "fUTHMa5Rmj4:APA91bEUnSY30suqDzySyl_D15-5VyP-FNOnVOnRBnLhizcnefqhiIi7tge94qT8sO3ZctKHRgpp_mt4dDEGVy6hoqP-i1-65zlyw31vdFNnuz7NxXz01FDWUCLi0Ivnxw0JWxjU9xkNhmAFPocec1VF7wzvg4mlRg";
        String user2_token = "dhxOHEwKaKk:APA91bGgwwG3_5_T-HU0RTvTif7OY7IkKWY1CYNEPrR_qCtdkWZRnQ3MAMIRBQCh08gyYXpb1iNSGQ2b7_kn_-B7O1hIK28a60szG-T2yhtLioWL7Zu0ws0QVMtFJ8cbG-xtMrDE0kWXfC8p-VAQ56d21C9roRqPMQ";
        String user3_token = "eG1_MWcGT5c:APA91bHCVguaZOqeIYZVHJ-RWA9SzuD22FcI83H3DkxZaKJ3wO_Z5FbsGfwN9zSkSzcG23uo9hqDKIeOW2rEouOglv6eImeNz8GGHi50hcUMfZzmblXcDgAWBF_GcOO1aJb6Z3b5kdAmv9-pcJzFd0xgqqsL_vtwMw";

        List<String> tokenList = new ArrayList<String>();
        tokenList.add(user3_token);
        tokenList.add(user1_token);
        tokenList.add(user2_token);

        JSONArray jsonArray = new JSONArray();
        jsonArray.add(tokenList.get(0));
        jsonArray.add(tokenList.get(1));
        jsonArray.add(tokenList.get(2));

        body.put("registration_ids",jsonArray);

        body.put("priority", "high");

        JSONObject notification = new JSONObject();
        notification.put("title", notification_title);
        notification.put("body", notification_body);


        JSONObject data = new JSONObject();
        data.put("portNum", portNum);
        data.put("uuid",uuid);


        body.put("notification", notification);
        body.put("data", data);
        System.out.println(body);


        HttpEntity<String> request = new HttpEntity<>(body.toString());


        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();
    }
    /*
    분실한 사용자가 위치 요청
     */
   @RequestMapping(value = "/location",method = RequestMethod.POST,produces = "application/json")
    public GpsVO requestLocation(@RequestBody LostItemVO lostItemVO){


       GpsVO gpsVO = new GpsVO();
       pushFirebase("Message","Somebody want to ur location!!",-1,lostItemVO.getItem_uuid());

       /*Timer m_timer = new Timer();
       while(longitude==0) {
           TimerTask m_task = new TimerTask() {
               @Override
               public void run() {
                   System.out.println(longitude);
                   System.out.println(latitude);

               }
           };
           m_timer.schedule(m_task,3000);
           if(longitude!=0)
               m_timer.cancel();
       }*/
       gpsVO.setLatitude(latitude);
       gpsVO.setLongitude(longitude);


       return gpsVO;
   }

   /*
   위치 제공
    */
   @RequestMapping(value = "/location/gps", method = RequestMethod.POST)
    public ResponseEntity<Object> getGPS(@RequestBody GpsVO gps){
//        Map<String,Float> gpsMap = new HashMap<String,Float>();
//        List<Float> gpsList = new ArrayList<>();
        this.longitude = gps.getLongitude();
        this.latitude=gps.getLatitude();
        return new ResponseEntity<>("Thank u for ur help",HttpStatus.OK);
   }

   @RequestMapping(value = "/talk",method = RequestMethod.POST,produces = "application/json")
    public String getPortNum(@RequestBody LostItemVO lostItemVO) throws ParseException {
       // RestTemplate
       RestTemplate restTemplate = new RestTemplate();
       /*
       소켓한테 포트번호 요청하기.
        */
       // HttpHeaders


      HttpHeaders headers = new HttpHeaders();


       headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
       // Request to return JSON format
       headers.setContentType(MediaType.APPLICATION_JSON);
       headers.set("my_other_key", "my_other_value");

       // HttpEntity<String>: To get result as String.
       HttpEntity<Object> entity = new HttpEntity<Object>(headers);



       // Send request with GET method, and Headers.
       ResponseEntity<String> response = restTemplate.exchange(
               "http://192.168.0.200:3000/allocatePort?item_key="+lostItemVO.getItem_key(),
               HttpMethod.GET, entity, String.class);

//       String result = response.getBody();
       String result = response.getBody();

       JSONParser parser = new JSONParser();
       JSONObject jsonObject = (JSONObject) parser.parse(result);
        int portNum = Integer.parseInt(jsonObject.get("portNum").toString());
       System.out.println("portNum : "+portNum);

       System.out.println(result);
       pushFirebase("Message","somebody want to talk with u",portNum,"nothing");

       return result;
   }


}
