package com.pedro.blogAPI.layers.service.services;

import com.pedro.blogAPI.layers.domain.dto.BlogPostRequest;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.layers.domain.model.BlogPost;
import com.pedro.blogAPI.layers.repository.BlogRepository;
import com.pedro.blogAPI.layers.service.mapper.BlogPostMapper;
import com.pedro.blogAPI.miscelaneous.exceptions.BlogPostNotFoundException;
import com.pedro.blogAPI.miscelaneous.exceptions.EmptyDatabaseException;
import com.pedro.blogAPI.miscelaneous.utils.NullUtils;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class BlogPostService {

    @Autowired
    BlogRepository repository;

    @Autowired
    BlogPostMapper mapper;


    @Transactional
    public BlogPostResponse saveBlogPost(BlogPostRequest request){
         return mapper.toBlogPostResponse(repository.save(mapper.toBlogPost(request)));
    }

    public BlogPostResponse getBlogPostById(Long id) {
        BlogPost blogPost = repository.findById(id)
                .orElseThrow(() -> new BlogPostNotFoundException(id));

        return mapper.toBlogPostResponse(blogPost);
    }

    public List<BlogPostResponse> getAllBlogPost(){
        List<BlogPostResponse> responses = mapper.toBlogPostResponseList(repository.findAll());
         if(responses.isEmpty()){
             throw new EmptyDatabaseException();
         }
         return responses;
    }

    @Transactional
    public BlogPostResponse updateBlogPost(BlogPostRequest request, Long id){
        return  repository.findById(id)
                .map(newBlogPost ->{
                    NullUtils.updateIfPresent(newBlogPost::setCategory, request.category());
                    NullUtils.updateIfPresent(newBlogPost::setTags, request.tags());
                    NullUtils.updateIfPresent(newBlogPost::setTitle, request.title());
                    NullUtils.updateIfPresent(newBlogPost::setContent, request.content());
                    newBlogPost.setUpdatedAt(LocalDateTime.now());
                    return mapper.toBlogPostResponse(newBlogPost);
                }).orElseThrow(() -> new BlogPostNotFoundException(id));

    }

    //does the query once
    @Transactional
    public void deleteBlogPost(Long id){
        if(repository.deleteByIdCustom(id) <= 0){
            throw new BlogPostNotFoundException(id);
        }
    }
}

