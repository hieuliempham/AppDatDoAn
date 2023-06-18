package Api.AppDatDoAn.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "hoadon")
public class HoaDon {
    @Id
    @GeneratedValue
    private UUID mahoadon;

    @Column(name = "ngaylap")
    private LocalDate ngaylap;

    @Column(name = "tongtien")
    private Double tongtien;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dondathang_id", referencedColumnName = "madondathang")
    private DonDatHang donDatHang;

    @PrePersist
    public void preNgayLap() {
        this.ngaylap = LocalDate.now();
    }

}
