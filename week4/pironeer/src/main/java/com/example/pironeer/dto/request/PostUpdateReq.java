package com.example.pironeer.dto.request;


import com.example.pironeer.entity.PostStatus;
import lombok.Getter;

@Getter
public class PostUpdateReq {
    private String title;

    private String content;

    private PostStatus status;
}
