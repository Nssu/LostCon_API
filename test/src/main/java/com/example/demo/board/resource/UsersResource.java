package com.example.demo.board.resource;

import com.example.demo.board.mapper.UserMapper;
import com.example.demo.board.model.ItemVO;
import com.example.demo.board.model.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UsersResource {

    private UserMapper mapper;
    private String baseUrl = "http://localhost:8080";


    public UsersResource(UserMapper mapper){
        this.mapper=mapper;
    }

    @GetMapping("/all") // 모든 유저의 정보를 보여준다.
    public List<UserVO> getAll(){
        return mapper.findAll();
    }

    @RequestMapping(value = "/mine/{user_key}",method = RequestMethod.GET) // 나의 정보
    public List<UserVO> getUser(@PathVariable(value="user_key")int user_key){
        return mapper.findUser(user_key);
    }

    @RequestMapping(value = "/others/{user_key}",method = RequestMethod.GET) // 나를 제외한 다른 사람들의 정보
    public List<UserVO> getOtherUser(@PathVariable(value="user_key")int user_key){
        return mapper.findUserNotMe(user_key);
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST,produces = "application/json")
    public ResponseEntity<Object> uploadFile(@RequestBody UserVO user,@RequestParam MultipartFile file) throws IOException {
        File convertFile = new File("src/main/resources/drawable/user/"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();
        System.out.println("user_name : "+user.getUser_name());


        return new ResponseEntity<>("File is uploaded successfully",HttpStatus.OK);
    }

    @RequestMapping(value = "/getUserList", method= RequestMethod.GET)
    public String demo() {
        System.out.println(mapper.getUserList().get(0).getUser_name());
        return "demo";
    }



    @RequestMapping(value = "/test",method = RequestMethod.POST)
    public ResponseEntity<Object> test(@RequestParam MultipartFile file) throws IOException {
        File convertFile = new File("src/main/resources/drawable/user/"+file.getOriginalFilename());
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();


        return new ResponseEntity<>("File is uploaded successfully",HttpStatus.OK);
    }

    /*
    FCM 알림서비스를 위해서 토큰 등록
     */
    @RequestMapping(value = "/register/token",method = RequestMethod.GET)
    public ResponseEntity<Object> registerToken(){



        return new ResponseEntity<>("register is successfully",HttpStatus.OK);
    }

    @RequestMapping(value = "/register/test",method = RequestMethod.POST)
    public ResponseEntity<Object> test(@RequestBody UserVO user) throws Exception{
        RestTemplate restTemplate = new RestTemplate();
        System.out.println("user_name : "+user.getUser_name());
//        URI uri = URI.create(baseUrl+"/users/register/token");
        String responseString = restTemplate.getForObject(baseUrl+"/users/register/token", String.class);
        System.out.println(responseString);
//        List<Article> articles = articlesService.getArticles();
//        String jsonString = jsonStringFromObject(articles);
        return new ResponseEntity<>("File is uploaded successfully",HttpStatus.OK);
    }

}
