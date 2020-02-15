package com.lego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
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

    @Transient
    private boolean isNew;

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
