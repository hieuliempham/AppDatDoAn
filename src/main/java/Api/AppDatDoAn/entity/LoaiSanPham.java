package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "loaisanpham")
public class LoaiSanPham {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maloai;

    @NotBlank(message = "Tên loại không được phép bỏ trống")
    @Column(name = "tenloai", length = 144)
    private String tenloai;

    @OneToMany(mappedBy = "loaisanpham", cascade = CascadeType.ALL)
    private List<SanPham> sanphams;
}
