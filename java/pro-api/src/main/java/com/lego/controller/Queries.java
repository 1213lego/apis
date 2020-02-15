package com.lego.controller;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.lego.domain.Post;
import com.lego.repository.jpa.PostRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Queries implements GraphQLQueryResolver {
    private final PostRepository postRepository;

    public Queries(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> recentPosts(int count, int offset) {
        return postRepository.findAll(PageRequest.of(offset, count)).getContent();
    }
}
