package Streaming.Application.Video.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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

    // getAll video List
    @GetMapping("/list")
    public ResponseEntity<List<Video>> getAllVideos() {
        return ResponseEntity.ok(videoService.getAll());
    }

    // streaming video
    // http://localhost:8080/api/v1/video/stream/{videoId}
    @GetMapping("/stream/{videoId}")
    public ResponseEntity<Resource> streamVideo(@PathVariable String videoId) {

        Video video = videoService.get(videoId);
        String contentType = video.getContentType();
        String filePath = video.getFilePath();
        Resource resource = new FileSystemResource(filePath);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(contentType))
                .body(resource);
    }

    // streaming video by title

    @GetMapping("/stream/range/{videoId}")
    public ResponseEntity<Resource> streamVideoByTitle(@PathVariable String videoId,
            @RequestHeader(value = "Range", required = false) String rangeHeader) {

        Video video = videoService.get(videoId);
        Path filePath = Paths.get(video.getFilePath());
        String contentType = video.getContentType();

        Resource resource = new FileSystemResource(filePath);

        if (contentType == null) {
            contentType = "application/octet-stream";
        }
        long fileLength = filePath.toFile().length();
        if (rangeHeader == null) {
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .body(resource);
        }

        // calculate range
        long rangeStart;
        long rangeEnd;

        // bytes 1002-1343. or bytes=1002-1343
        rangeHeader.replace("bytes=", "");
        String[] ranges = rangeHeader.split("-");
        rangeStart = Long.parseLong(ranges[0]);
        if (ranges.length > 1) {
            rangeEnd = Long.parseLong(ranges[1]);
        } else {
            rangeEnd = fileLength - 1;
        }

        if (rangeEnd > fileLength - 1) {
            rangeEnd = fileLength - 1;
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath.toFile());
            inputStream.skip(rangeStart);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }

        long contentLength = rangeEnd - rangeStart + 1;
        org.springframework.http.HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        httpHeaders.add("Content-Range", "bytes " + rangeStart + "-" + rangeEnd + "/" + fileLength);
        httpHeaders.add("Cache-Control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        httpHeaders.add("X-Content-Type-Options", "nosniff");
        httpHeaders.setContentLength(contentLength);

        return ResponseEntity
                .status(HttpStatus.PARTIAL_CONTENT)
                .headers(httpHeaders) 
                .contentType(MediaType.parseMediaType(contentType))
                .body(new InputStreamResource(inputStream));
    }
}
