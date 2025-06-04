package com.twitter.demo.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class CommentsDto {

    private UUID id;

    private String comment;
}
