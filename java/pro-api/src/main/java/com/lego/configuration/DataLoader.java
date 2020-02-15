package com.lego.configuration;

import com.lego.domain.Author;
import com.lego.domain.Bike;
import com.lego.domain.Post;
import com.lego.repository.jpa.AuthorRepository;
import com.lego.repository.jpa.BikeRepository;
import com.lego.repository.jpa.PostRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class DataLoader {
    private final BikeRepository bikeRepository;
    private final AuthorRepository authorRepository;
    private final PostRepository postRepository;

    public DataLoader(BikeRepository bikeRepository, AuthorRepository authorRepository, PostRepository postRepository) {
        this.bikeRepository = bikeRepository;
        this.authorRepository = authorRepository;
        this.postRepository = postRepository;
        loadBikes();
        loadData();
    }

    private void loadData() {
        List<Author> s = IntStream
                .range(1, 10)
                .mapToObj((i) -> {
                    Author author = Author
                            .builder()
                            .name("An " + i)
                            .thumbnail("At " + i)
                            .build();
                    author.setPosts(generatePostsFor(author));
                    return author;
                })
                .collect(Collectors.toList());
        authorRepository.saveAll(s);
    }

    private List<Post> generatePostsFor(Author author) {
        return IntStream
                .range(0, 5)
                .mapToObj(i -> Post
                        .builder()
                        .author(author)
                        .category("Pc " + i)
                        .text("Pt " + i)
                        .title("Pti " + i)
                        .build())
                .collect(Collectors.toList());
    }

    private void loadBikes() {
        List<Bike> bikes = IntStream.range(1, 20)
                .mapToObj((i) -> Bike.builder()
                        .price(i * 6.831698)
                        .serial("B" + i)
                        .weight(1.36598 * i * i)
                        .purchaseDate(LocalDate.now())
                        .build())
                .collect(Collectors.toList());
        bikeRepository.saveAll(bikes);
    }
}
