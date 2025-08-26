# Learning Video Streaming with Java and Spring Boot



# API Documentation

## Create Video

### Request

```
POST /api/v1/video/create
Content-Type: multipart/form-data
title=Video Title
description=Video Description
```

### Response

```
HTTP/1.1 201 Created
Content-Type: application/json
{
    videoId: "videoId",
    title: "Video Title",
    description: "Video Description",
    contentType: "video/mp4",
    filePath: "video/videoId.mp4"
}
```



