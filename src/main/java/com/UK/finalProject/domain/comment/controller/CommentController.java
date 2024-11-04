package com.UK.finalProject.domain.comment.controller;

import com.UK.finalProject.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class CommentController {

    private final CommentService commentService;
}
