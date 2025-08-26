package Streaming.Application.Video.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import Streaming.Application.Video.entity.Video;

public interface VideoService {

    Video save(Video video, MultipartFile file);
    // save video


    Video get(String videoId);

    //get video by id

    Video getByTitle(String title);
    //get video by title

    List<Video> getAll();
    //get all videos


}
