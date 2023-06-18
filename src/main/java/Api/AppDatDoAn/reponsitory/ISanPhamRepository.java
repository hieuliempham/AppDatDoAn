package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.SanPham;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ISanPhamRepository extends JpaRepository<SanPham, String> {
    @Query("SELECT sp FROM SanPham sp WHERE " +
            "sp.tensanpham LIKE CONCAT('%',:query, '%')")
    List<SanPham> searchByName(String query);

    @Query("SELECT sp FROM SanPham sp WHERE sp.loaisanpham.maloai = :query")
    List<SanPham> searchByTheLoai(Long query);

    @Query("SELECT sp FROM SanPham sp WHERE sp.cuahang.macuahang = :query")
    List<SanPham> findAllSanPhamByMaCH(String query);

    @Query("SELECT sp FROM SanPham sp WHERE LOWER(sp.tensanpham) LIKE %:name% AND sp.loaisanpham.maloai = :theloaiId")
    List<SanPham> searchByNameAndTheLoai(@Param("name") String name, @Param("theloaiId") Long theloaiId);

}
