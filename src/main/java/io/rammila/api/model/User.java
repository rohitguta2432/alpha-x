package io.rammila.api.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "mobile"))
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners({AuditingEntityListener.class})
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    private String mobile;
    private String password;
    private Boolean status;
    private String firstName;
    private String lastName;
    private String fullName;

    @OneToMany(targetEntity = Address.class, cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    List<Address> address;

    @CreatedDate
    private Date createdAt;
    @LastModifiedDate
    private Date updatedAt;


}
