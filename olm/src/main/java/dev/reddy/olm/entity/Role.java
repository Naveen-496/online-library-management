package dev.reddy.olm.entity;


import com.fasterxml.jackson.annotation.JsonInclude;
import dev.reddy.olm.enums.Authority;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
@JsonInclude(NON_DEFAULT)
public class Role extends Auditable{

    private String name;
    private Authority authority;

}
