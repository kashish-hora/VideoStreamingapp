package com.stream.app.Services;

import com.stream.app.Entities.Video;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoServices {
    //save video
    Video save(Video video, MultipartFile file);
    //get video by id
    Video get(String videoId);
    //get video by title
    Video getbytitle(String title);
    List<Video> getAll();
}
