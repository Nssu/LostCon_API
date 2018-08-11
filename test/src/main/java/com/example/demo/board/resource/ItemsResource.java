package com.example.demo.board.resource;

import com.example.demo.board.mapper.ItemMapper;
import com.example.demo.board.mapper.UserMapper;
import com.example.demo.board.model.ItemVO;
import com.example.demo.board.model.UserVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemsResource {

    private ItemMapper itemMapper;

    public ItemsResource(ItemMapper itemMapper){
        this.itemMapper=itemMapper;
    }

    // 아이템 정보 업로드!
    @RequestMapping(value = "/info-upload",method = RequestMethod.POST,consumes=MediaType.MULTIPART_FORM_DATA_VALUE)
//    @ResponseBody
    public ResponseEntity<Object> uploadFile(@RequestParam("file")MultipartFile file,ItemVO item) throws IOException {
        int item_key=0;
        String fileName = file.getOriginalFilename();
        item.setItem_img(fileName);
        File convertFile = new File("src/main/resources/drawable/item/"+fileName);
        convertFile.createNewFile();
        FileOutputStream fout = new FileOutputStream(convertFile);
        fout.write(file.getBytes());
        fout.close();

        System.out.println("user_key : "+item.getUser_key());
        // 아이템 등록하는 코드
        System.out.println("fileName : "+item.getItem_img());
        itemMapper.uploadItem(item);

        item_key=itemMapper.getItemKey(item);

        return new ResponseEntity<>("{\"item_key\" : "+ item_key+"}",HttpStatus.OK);
    }


}
