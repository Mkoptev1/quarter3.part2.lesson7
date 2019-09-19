package com.geekbrains.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "ware")
@Data
@NoArgsConstructor
public class Ware {
    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ware_seq")
    @SequenceGenerator(name = "ware_seq", sequenceName = "ware_ware_id_seq", allocationSize = 1)
    @Column(name = "ware_id")
    private Long id;

    @Column(name = "ware_name")
    private String name;

    @Column(name = "ware_cost")
    private Long cost;
}
