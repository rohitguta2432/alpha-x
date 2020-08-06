package io.rammila.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "categorys")
@EntityListeners({AuditingEntityListener.class})
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(unique = true)
    private String name;
    private String description;
    private Boolean isActive = Boolean.TRUE;

    @JsonProperty("subCategories")
    @OneToMany(targetEntity = SubCategory.class,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id",referencedColumnName = "id")
    private List<SubCategory> subCategories = new ArrayList<>();

    @CreatedDate
    private Date createdAt;

    @LastModifiedDate
    private Date updatedAt;

    @PrePersist
    public void  setIsActive(){
        this.setIsActive(true);
    }
}
