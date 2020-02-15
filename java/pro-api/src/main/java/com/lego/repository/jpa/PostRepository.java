package com.lego.repository.jpa;

import com.lego.model.Post;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @EntityGraph(value = "Post.post-entity-graph", type = EntityGraph.EntityGraphType.LOAD)
    Optional<Post> getById(Long id);
}
