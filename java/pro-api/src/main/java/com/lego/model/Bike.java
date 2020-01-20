package com.lego.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Bike.findAllOrderedDescendingByPrice", query = "select b from Bike b order by b.price desc"),
        @NamedQuery(name = "Bike.pair", query = "select new com.lego.util.Pair(b.serial,b) from Bike b")
})
public class Bike implements Persistable<String> {
    @Id
    private String serial;
    @Column(nullable = false, updatable = false)
    private LocalDate purchaseDate;
    private Double weight;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;

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

    @PrePersist
    public void prePersist() {
        System.out.println("--------------|||@PrePersist|||----------------------------------");
        this.setCreatedAt(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("--------------|||@PreUpdate|||----------------------------------");
        this.setUpdatedAt(LocalDateTime.now());
    }
}
