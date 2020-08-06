package io.rammila.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "sub_categorys")
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
public class SubCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean isActive;

    @JsonProperty("products")
    @OneToMany(targetEntity = Product.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "sub_category_id",referencedColumnName = "id")
    private List<Product> products = new ArrayList<>();

    @CreatedBy
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;

    @PrePersist
    public void  setIsActive(){
        this.setIsActive(true);
    }
}
