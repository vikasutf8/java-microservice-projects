package Streaming.Application.Video.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import Streaming.Application.Video.entity.Video;
import Streaming.Application.Video.playload.CustomMessage;
import Streaming.Application.Video.service.VideoService;

@RestController
@RequestMapping("/api/v1/video")
public class VideoControllers {

    @Autowired
    private VideoService videoService;

    public VideoControllers(VideoService videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createVideo(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam("description") String description) {
        Video video = new Video();
        video.setTitle(title);
        video.setDescription(description);
        video.setContentType(file.getContentType());
        video.setVideoId(UUID.randomUUID().toString()); 

        Video videoSaved = videoService.save(video, file);
        if (videoSaved != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(videoSaved);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new CustomMessage("Video creation failed", false));
        }
    }
}
