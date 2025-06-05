package kr.ac.hansung.cse.hellospringdatajpa.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotEmpty(message = "상품 이름은 필수입니다")
    private String name;

    @Column(nullable = false)
    @NotEmpty(message = "브랜드는 필수입니다")
    private String brand;

    @Column(name = "made_in", nullable = false)
    @NotEmpty(message = "원산지는 필수입니다")
    private String madeIn;


    @Column(nullable = false)
    @NotNull(message = "가격은 필수입니다")
    @Min(value = 0, message = "가격은 0 이상이어야 합니다")
    private double price;
}