package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "khachhang")
public class KhachHang {
    @Id
    @GeneratedValue
    private UUID makhachhang;

    @NotBlank(message = "Tên không được phép bỏ trống")
    @Column(name = "tenkhachhang", length = 64)
    @Size(max = 64, message = "Chuỗi không được quá 64 ký tự")
    private String tenkhachhang;

    @Column(name = "diachia", length = 255)
    private String diachi;

    @Pattern(regexp = "^\\d{10}$", message = "Số điện thoại không hợp lệ")
    @Column(name = "sodienthoai", length = 10)
    private String sodienthoai;

    @OneToMany(mappedBy = "khachhang", cascade = CascadeType.ALL)
    private List<DonDatHang> dondathangs;
}
