package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.CuaHang;
import Api.AppDatDoAn.reponsitory.ICuaHangRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CuaHangService {
    @Autowired
    private ICuaHangRepository cuaHangReponsitory;

    public List<CuaHang> getAllCuaHang() {
        return cuaHangReponsitory.findAll();
    }

    public CuaHang getCuaHangById(String maCuaHang) {
        return cuaHangReponsitory.findByMaCuaHang(maCuaHang);
    }

    public CuaHang saveCuaHang(CuaHang cuahang) {
        String macuahang = RandomStringUtils.randomAlphanumeric(10);
        cuahang.setMacuahang(macuahang);
        cuahang.setLuotdanhgia(0L);
        return cuaHangReponsitory.save(cuahang);
    }

    public void updateCuaHang(CuaHang cuahang) {
        cuaHangReponsitory.save(cuahang);
    }

    public void removeCuaHang(String id) {
        cuaHangReponsitory.deleteById(id);
    }
}
