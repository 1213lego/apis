package com.lego.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table
@NamedQueries({
        @NamedQuery(name = "Bike.findAllOrderedDescendingByPrice", query = "select b from Bike b order by b.price desc"),
        @NamedQuery(name = "Bike.pair" , query = "select new com.lego.util.Pair(b.serial,b) from Bike b")
})
public class Bike {
	@Id
	private String serial;
	@Column(nullable = false)
	private LocalDate purchaseDate;
	private Double weight;
	@Column (nullable = false)
	private Double price;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	@Temporal(TemporalType.TIMESTAMP)
	private Date deletedAt;
}
