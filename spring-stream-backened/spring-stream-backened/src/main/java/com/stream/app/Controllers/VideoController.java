package com.stream.app.Controllers;

import com.stream.app.Entities.Video;
import com.stream.app.Services.VideoServices;
import com.stream.app.payload.CustomMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/videos")
@CrossOrigin("*")
public class VideoController {
    private VideoServices videoservice;

    public VideoController(VideoServices videoservice) {
        this.videoservice = videoservice;
    }


    @PostMapping
    public ResponseEntity<?> create(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        Video video = new Video();
        video.setTitle(title);

        video.setDescription(description);
        video.setVideoId(UUID.randomUUID().toString());
        videoservice.save(video, file);
        Video savevideo=videoservice.save(video,file);
                if(savevideo!=null){
                    return ResponseEntity.status(HttpStatus.OK).body(video);
                }
                else{
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(CustomMessage.builder().message("video not uploaded").success(false).build());


        }


    }
}



