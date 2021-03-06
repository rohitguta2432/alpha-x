package io.rammila.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@Entity
@Table(name = "carts")
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class Cart implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private UUID userId;
    private Boolean isActive;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;

    private Long quantity = 0l;

    @Transient
    private Double totalPricePerQuantity;

    @Transient
    private Double totalPrice;

    @Transient
    private Double totalPriceAfterTax;

    @Transient
    private Double tax;


    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    public Double getTotalPricePerQuantity() {
        Product product = this.product;
        return product.getPrice() * this.quantity;
    }

}
