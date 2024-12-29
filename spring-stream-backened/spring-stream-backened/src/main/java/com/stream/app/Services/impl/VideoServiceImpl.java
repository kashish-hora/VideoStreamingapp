package com.stream.app.Services.impl;

import ch.qos.logback.core.net.SyslogOutputStream;
import ch.qos.logback.core.util.StringUtil;
import com.stream.app.Entities.Video;
import com.stream.app.Services.VideoServices;
import com.stream.app.reositories.videorepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
@Service
public class VideoServiceImpl implements VideoServices {
    @Value("${files.video}")
    String DIR;
    @Autowired
    private videorepository videorepo;
    @PostConstruct
    public void init(){

        File file=new File(DIR);
        if(!file.exists()){
            file.mkdir();
            System.out.println("Folder created");

        }
        else{
            System.out.println("folder already exist");
        }
    }

    @Override
    public Video save(Video video, MultipartFile file) {
        //original file name
try{
        String filename=file.getOriginalFilename();
        String contentType=file.getContentType();


            InputStream inputstream=file.getInputStream();
            //file path
          String cleanfilename= StringUtils.cleanPath(filename);
          //folder path :create
          String cleanfolder= StringUtils.cleanPath(DIR);
          //folder path with filename
         Path path= Paths.get(cleanfolder,cleanfilename);
    System.out.println(path);
    //copy file to folder
    Files.copy(inputstream,path, StandardCopyOption.REPLACE_EXISTING);

    //video metadata
    video.setContentType(contentType);
    video.setFilepath(path.toString());

    return videorepo.save(video);





        } catch (IOException e) {
            e.printStackTrace();
             return null;
        }


        //folder path with filename
        //copy file to the folder
        //video metadata
        //metadata save

    }

    @Override
    public Video get(String videoId) {
        return null;
    }

    @Override
    public Video getbytitle(String title) {
        return null;
    }

    @Override
    public List<Video> getAll() {
        return List.of();
    }
}
