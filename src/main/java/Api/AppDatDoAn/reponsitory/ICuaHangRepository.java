package Api.AppDatDoAn.reponsitory;

import Api.AppDatDoAn.entity.CuaHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuaHangRepository extends JpaRepository<CuaHang, String> {
    @Query("SELECT c FROM CuaHang c WHERE c.macuahang = ?1")
    CuaHang findByMaCuaHang(String macuahang);

}
