package com.smartosc.product.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * product
 *
 * @author Tung lam
 * @created_at 22/06/2020 - 10:37
 * @created_by Tung lam
 * @since 22/06/2020
 */
@Table(name = "category")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    private List<Product> products;

    public Category(long l, String category, String emak) {
    }
}
