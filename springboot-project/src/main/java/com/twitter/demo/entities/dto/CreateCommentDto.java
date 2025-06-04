package com.twitter.demo.entities.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class CreateCommentDto {

    @NotNull
    private UUID postId;

    @NotNull
    private UUID authorId;

    @NotNull
    @NotBlank
    private String comment;
}
