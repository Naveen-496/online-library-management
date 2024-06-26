package dev.reddy.olm.entity;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.OnDelete;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;
import static jakarta.persistence.FetchType.EAGER;
import static org.hibernate.annotations.OnDeleteAction.CASCADE;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "credentials")
@JsonInclude(NON_DEFAULT)
public class Credentials extends Auditable{

    private String password;
    @OneToOne(targetEntity = User.class, fetch = EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private User user;

    public Credentials(User user, String password) {
        this.user = user;
        this.password = password;
    }
}
