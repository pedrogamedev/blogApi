package com.pedro.blogAPI.layers.service.mapper;

import com.pedro.blogAPI.layers.domain.dto.BlogPostRequest;
import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.layers.domain.model.BlogPost;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BlogPostMapper {

    public BlogPost toBlogPost(BlogPostRequest request){
        return new BlogPost(
                request.title(),
                request.content(),
                request.category(),
                request.tags()
        );
    }

    public BlogPostResponse toBlogPostResponse(BlogPost blogPost){

        return new BlogPostResponse(
                blogPost.getId(),
                blogPost.getTitle(),
                blogPost.getContent(),
                blogPost.getCategory(),
                blogPost.getTags(),
                blogPost.getCreatedAt(),
                blogPost.getUpdatedAt()
        );
    }

    public List<BlogPostResponse> toBlogPostResponseList(List<BlogPost> list){
        List<BlogPostResponse> listResult = new ArrayList<>();

        for(int i = 0; i<= list.size() - 1; i++){
            listResult.add(toBlogPostResponse(list.get(i)));
        }

        return listResult;
    }



}
