package com.pedro.blogAPI.layers.domain.model;

import com.pedro.blogAPI.miscelaneous.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "post")
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "title")
    String title;

    @Column(name = "content")
    String content;

    @Column(name = "category")
    Category category;

    @Column(name = "tag")
    ArrayList<String> tags;

    @Column(name = "createdAt")
    LocalDateTime createdAt;

    @Column(name = "updatedAt")
    LocalDateTime updatedAt;

    public BlogPost(String title, String content, Category category, ArrayList<String> tags){
        this.title = title;
        this.category = category;
        this.content = content;
        this.tags = tags;
        createdAt = LocalDateTime.now();
    }
}
