package com.pedro.blogAPI.layers.repository;

import com.pedro.blogAPI.layers.domain.dto.BlogPostResponse;
import com.pedro.blogAPI.layers.domain.model.BlogPost;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BlogPost b WHERE b.id = :id")
    int deleteByIdCustom(@Param("id") Long id);

    @Query("SELECT b FROM BlogPost b WHERE " +
            "LOWER(b.title) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.content) LIKE LOWER(CONCAT('%', :term, '%')) OR " +
            "LOWER(b.category) LIKE LOWER(CONCAT('%', :term, '%'))")
    List<BlogPost> findByTerm(@Param("term") String term);

}
