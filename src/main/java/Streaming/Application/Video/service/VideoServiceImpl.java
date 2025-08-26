package Streaming.Application.Video.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Streaming.Application.Video.entity.Video;
import Streaming.Application.Video.repository.VideoRepository;
import jakarta.annotation.PostConstruct;

import org.springframework.util.StringUtils;

@Service
public class VideoServiceImpl  implements VideoService {

    @Value("${files.video}")
    String DIR ;

    @Autowired
    private VideoRepository videoRepository;

    //to create that folder before method and after constructor
    @PostConstruct
    public void init(){
        File file = new File(DIR);
        if(!file.exists()){
            file.mkdirs();
            System.out.println("Directory created");
        }
        else{
            System.out.println("Directory already exists");
        }

    }

    @Override
    public Video save(Video video, MultipartFile file) {

        try {
            //original file name
        String filename = file.getOriginalFilename();
        String contentType = file.getContentType();
        InputStream inputStream = file.getInputStream();
        
        // path of folder where video will be stored
         String cleanFileName = StringUtils.cleanPath(filename);
        String cleanFolder  = StringUtils.cleanPath(DIR);

        //folder path with file name
        Path path = Paths.get(cleanFolder,cleanFileName);
        System.out.println(path);

        //copy file to the folder
        Files.copy(inputStream, path, StandardCopyOption.REPLACE_EXISTING);

        //video meta data create
        video.setContentType(contentType);
        video.setFilePath(path.toString());

        //save metadata
        videoRepository.save(video);

        
        return video;
        } catch (IOException e) {
          e.printStackTrace();
          return null;
         }

    }

    @Override
    public Video get(String videoId) {
       return null;
    }

    @Override
    public Video getByTitle(String title) {
      return null;
    }

    @Override
    public List<Video> getAll() {
      
        return null;
    }

}
