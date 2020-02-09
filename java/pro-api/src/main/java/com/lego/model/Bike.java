package com.lego.model;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@Builder
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Bike.findAllOrderedDescendingByPrice", query = "select b from Bike b order by b.price desc"),
        @NamedQuery(name = "Bike.pair", query = "select new com.lego.util.Pair(b.serial,b) from Bike b")
})
public class Bike extends BasicAuditable implements Persistable<String> {
    @Id
    @Column(length = 30)
    private String serial;
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private LocalDate purchaseDate;
    @Column(scale = 3)
    private Double weight;
    @Column(nullable = false, scale = 3)
    private Double price;
    @ManyToOne
    @JoinColumn(nullable = false)
    private State state;
    @Transient
    private boolean isNew;
    @ElementCollection
    private List<String> keywords;

    @Override
    public String getId() {
        return serial;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public Bike() {
    }
}
