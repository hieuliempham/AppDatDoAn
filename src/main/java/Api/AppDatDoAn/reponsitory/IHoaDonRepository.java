package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.HoaDon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Repository
public interface IHoaDonRepository extends JpaRepository<HoaDon, UUID> {
    @Query(value = "SELECT * FROM hoadon hd " +
            "JOIN dondathang ddh ON ddh.madondathang = hd.dondathang_id " +
            "JOIN ct_ddh ctdh ON ctdh.madondathang = ddh.madondathang " +
            "JOIN sanpham sp ON sp.masanpham = ctdh.masanpham " +
            "JOIN cuahang ch ON sp.cuahang_id = ch.macuahang " +
            "WHERE ch.macuahang = ?1", nativeQuery = true)
    List<HoaDon> findAllHoaDonByMaCH(String macuahang);

    @Query("SELECT MONTH(hd.ngaylap) AS month, SUM(hd.tongtien) AS total "
            + "FROM HoaDon hd " +
            "JOIN DonDatHang ddh ON ddh.madondathang = hd.donDatHang.madondathang " +
            "JOIN ChiTietDonDatHang ctdh ON ctdh.dondathang.madondathang = ddh.madondathang " +
            "JOIN SanPham sp ON sp.masanpham = ctdh.sanpham.masanpham " +
            "JOIN CuaHang ch ON sp.cuahang.macuahang = ch.macuahang " +
            "WHERE YEAR(hd.ngaylap) = :year AND ch.macuahang = :mach " +
            "GROUP BY MONTH(hd.ngaylap)")
    List<Object[]> getMonthlyRevenue(@Param("year") int year, @Param("mach") String mach);

    @Query("SELECT MONTH(hd.ngaylap) AS month, SUM(hd.tongtien) AS total "
            + "FROM HoaDon hd " +
            "JOIN DonDatHang ddh ON ddh.madondathang = hd.donDatHang.madondathang " +
            "JOIN ChiTietDonDatHang ctdh ON ctdh.dondathang.madondathang = ddh.madondathang " +
            "JOIN SanPham sp ON sp.masanpham = ctdh.sanpham.masanpham " +
            "JOIN CuaHang ch ON sp.cuahang.macuahang = ch.macuahang " +
            "WHERE YEAR(hd.ngaylap) = :year " +
            "GROUP BY MONTH(hd.ngaylap)")
    List<Object[]> getMonthlyRevenue(@Param("year") int year);

    @Query("SELECT DAY(hd.ngaylap) AS day, SUM(hd.tongtien) AS total " +
            "FROM HoaDon hd " +
            "JOIN DonDatHang ddh ON ddh.madondathang = hd.donDatHang.madondathang " +
            "JOIN ChiTietDonDatHang ctdh ON ctdh.dondathang.madondathang = ddh.madondathang " +
            "JOIN SanPham sp ON sp.masanpham = ctdh.sanpham.masanpham " +
            "JOIN CuaHang ch ON sp.cuahang.macuahang = ch.macuahang " +
            "WHERE MONTH(hd.ngaylap) = :month AND YEAR(hd.ngaylap) = :year AND ch.macuahang = :mach " +
            "GROUP BY DAY(hd.ngaylap)")
    List<Object[]> getDailyRevenue(@Param("month") int month, @Param("year") int year, @Param("mach") String mach);

    @Query("SELECT DAY(hd.ngaylap) AS day, SUM(hd.tongtien) AS total " +
            "FROM HoaDon hd " +
            "JOIN DonDatHang ddh ON ddh.madondathang = hd.donDatHang.madondathang " +
            "JOIN ChiTietDonDatHang ctdh ON ctdh.dondathang.madondathang = ddh.madondathang " +
            "JOIN SanPham sp ON sp.masanpham = ctdh.sanpham.masanpham " +
            "JOIN CuaHang ch ON sp.cuahang.macuahang = ch.macuahang " +
            "WHERE MONTH(hd.ngaylap) = :month AND YEAR(hd.ngaylap) = :year " +
            "GROUP BY DAY(hd.ngaylap)")
    List<Object[]> getDailyRevenue(@Param("month") int month, @Param("year") int year);
}
