package com.example.demo.board.resource;

import com.example.demo.board.mapper.ItemMapper;
import com.example.demo.board.mapper.SocketMapper;
import com.example.demo.board.model.ItemVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/socekts")
public class SocketsResource {

    private SocketMapper socketMapper;

    public SocketsResource(SocketMapper socketMapper){
        this.socketMapper=socketMapper;
    }


//    public ResponseEntity<Object>
}
