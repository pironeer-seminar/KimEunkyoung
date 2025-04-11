package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import lombok.Getter;

@Getter
public class PostUpdateReq {

    private String title;

    private String content;

    private PostStatus status;
}
