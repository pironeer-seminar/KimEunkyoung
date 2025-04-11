package com.example.demo1.post.dto.request;

import com.example.demo1.post.entity.PostStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PostUpdateReq {

    @Schema(description = "게시글 제목", example = "수정된 제목입니다.")
    @NotBlank(message = "제목은 필수입니다.")
    private String title;

    @Schema(description = "게시글 내용", example = "수정된 내용입니다.")
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @Schema(description = "게시글 상태 (PUBLIC 또는 PRIVATE)", example = "PRIVATE")
    @NotNull(message = "게시글 상태는 필수입니다.")
    private PostStatus status;
}
