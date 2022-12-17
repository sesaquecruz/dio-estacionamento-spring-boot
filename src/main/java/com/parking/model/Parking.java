package com.parking.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Parking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;
    @Column(nullable = false)
    @JsonIgnore
    private Long timestamp;
    @Column(nullable = false, unique = true)
    private String code;
    @Column(nullable = false)
    private String license;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Boolean checkout;
    @Column(nullable = false)
    private LocalDateTime entryDate;
    private LocalDateTime exitDate;
    private Double bill;

    @PrePersist
    private void onCreate() {
        timestamp = System.currentTimeMillis();
    }
}
