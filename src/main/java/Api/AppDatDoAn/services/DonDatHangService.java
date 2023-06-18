package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.DonDatHang;
import Api.AppDatDoAn.reponsitory.IDonDatHangRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DonDatHangService {

    @Autowired
    private IDonDatHangRepository donDatHangReponsitory;

    public DonDatHang getDDHById(UUID id) {
        return donDatHangReponsitory.findById(id).orElse(null);
    }

    public List<DonDatHang> getAllDDHByMaCH(String macuahang) {
        return donDatHangReponsitory.findAllDDHByMaCH(macuahang);
    }

    public List<DonDatHang> getAllDDH() {
        return donDatHangReponsitory.findAll();
    }

    public DonDatHang saveDDH(DonDatHang donDatHang) {

        return donDatHangReponsitory.save(donDatHang);
    }
}
