package Api.AppDatDoAn.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class HoaDonDto {
    private UUID mahoadon;
    private UUID madondathang;
    private String tenkhachhang;
    private LocalDate ngaylap;
    private Double tongtien;
}
