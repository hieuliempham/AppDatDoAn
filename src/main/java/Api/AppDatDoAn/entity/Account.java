package Api.AppDatDoAn.entity;

import Api.AppDatDoAn.validator.annotation.ValidUsername;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue
    private UUID accountId;

    @Email(message = "Email invalid")
    @Column(name = "username", length = 64)
    @ValidUsername
    private String username;

    @Column(name = "password", length = 64)
    @Size(min = 6, message = "Mật khẩu phải ít nhất 6 ký tự.")
    @Size(max = 144, message = "Mật khẩu không được vượt quá 144 ký tự.")
    private String password;

    @Column(name = "macuahang", length = 144)
    @NotBlank(message = "Vui lòng nhập mã cửa hàng.")
    private String macuahang;

    @Column(name = "verifycation_code", length = 64)
    private String verificationCode;

    @Column(name = "reset_password_token", length = 30)
    private String resetPasswordToken;

    private boolean enabled;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    private ShoppingCart cart;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "account_role",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Account(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }
}
