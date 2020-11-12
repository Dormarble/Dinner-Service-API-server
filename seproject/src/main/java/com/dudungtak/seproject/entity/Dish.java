package com.dudungtak.seproject.entity;

import lombok.*;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Accessors(chain = true)
@ToString(exclude = {"orderElementList", "dishElementList", "menuElementList"})
public class Dish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String status;

    private BigDecimal price;

    private LocalDate registeredAt;

    private LocalDate unregisteredAt;

    private LocalDateTime createdAt;

    private String createdBy;

    private LocalDateTime updatedAt;

    private String updatedBy;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<OrderElement> orderElementList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<DishElement> dishElementList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "dish")
    private List<MenuElement> menuElementList;
}
