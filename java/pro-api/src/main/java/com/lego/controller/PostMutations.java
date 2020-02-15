package com.lego.controller;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.lego.domain.Author;
import com.lego.domain.Post;
import com.lego.repository.jpa.PostRepository;
import org.springframework.stereotype.Component;

@Component
public class PostMutations implements GraphQLMutationResolver {
    private final PostRepository postRepository;

    public PostMutations(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post writePost(String title, String text, String category, long author) {
        return postRepository.save(Post
                .builder()
                .title(title)
                .text(text)
                .category(category)
                .author(Author.builder().id(author).build())
                .build());
    }
}
