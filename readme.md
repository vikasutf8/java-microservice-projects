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

## List of All Videos

### Request

```
GET /api/v1/video/list
```

### Response

```
HTTP/1.1 200 OK
Content-Type: application/json
[
    {
        videoId: "videoId",
        title: "Video Title",
        description: "Video Description",
        contentType: "video/mp4",
        filePath: "video/videoId.mp4"
    },
    {
        videoId: "videoId",
        title: "Video Title",
        description: "Video Description",
        contentType: "video/mp4",
        filePath: "video/videoId.mp4"
    }
]
```

## Streaming Video

### Request

```
GET /api/v1/video/stream/{videoId}
```

### Response

```
HTTP/1.1 200 OK
Content-Type: video/mp4

```
### ISSUEs  --not byte  Meant complete file 10GB transmission
### making byte wise as broadcasting ...

-  Bandwidth issue 
- network conjection issue
- wasted Resources 
