package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue
    private UUID roleId;

    @Size(max = 50, message = "Name must be less than 50 characters")
    @NotBlank(message = "Role name is required")
    @Column(name = "role_name", length = 50, nullable = false)
    private String roleName;

    @Size(max = 250, message = "Description must be less than 250 characters")
    @Column(name = "description", length = 250)
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<Account> accounts = new HashSet<>();
}
