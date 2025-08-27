package com.pedro.blogAPI.layers.repository;

import com.pedro.blogAPI.layers.domain.model.BlogPost;
import jakarta.persistence.Id;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<BlogPost, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM BlogPost b WHERE b.id = :id")
    int deleteByIdCustom(@Param("id") Long id);
}
