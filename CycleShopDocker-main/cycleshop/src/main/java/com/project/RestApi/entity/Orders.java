package com.project.RestApi.entity;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Data
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    // @ManyToOne
    // @JoinColumn(name = "cycle_id")
    // private Cycle cycle;

    private int quantity;

    private int totalPrice;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date orderedAt;

}
