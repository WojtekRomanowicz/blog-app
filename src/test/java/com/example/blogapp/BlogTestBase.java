package com.example.blogapp;

import com.example.blogapp.helper.DataGenerator;
import com.example.blogapp.repository.BlogRepository;
import com.example.blogapp.repository.FileRepository;
import com.example.blogapp.repository.PostRepository;
import com.example.blogapp.repository.UserRepository;
import com.example.blogapp.service.UserCRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

@Component
public class BlogTestBase {

    @Autowired
    DataGenerator dataGenerator;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserCRUDService userCRUDService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    BlogRepository blogRepository;

    @Autowired
    FileRepository fileRepository;

    @Autowired
    protected TestRestTemplate restTemplate;

}