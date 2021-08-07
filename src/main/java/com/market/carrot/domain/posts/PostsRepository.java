package com.market.carrot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {
    @Query("SELECT post FROM Posts post ORDER BY post.id DESC")
    List<Posts> findAllDesc();
}
