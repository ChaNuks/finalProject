package com.UK.finalProject.controller;

import com.UK.finalProject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/content")
public class CommentController {

    private final CommentService commentService;
}
