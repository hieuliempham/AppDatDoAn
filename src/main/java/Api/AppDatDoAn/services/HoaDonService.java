package Api.AppDatDoAn.services;

import Api.AppDatDoAn.entity.HoaDon;
import Api.AppDatDoAn.reponsitory.IHoaDonRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HoaDonService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

    public HoaDon getHoaDonById(UUID id) {
        return hoaDonRepository.findById(id).orElse(null);
    }

    public List<HoaDon> getAllHoaDonByMaCH(String macuahang) {
        return hoaDonRepository.findAllHoaDonByMaCH(macuahang);
    }

    public List<HoaDon> getAllHoaDon() {
        return hoaDonRepository.findAll();
    }

    public void saveHoaDon(HoaDon hoaDon) {
        hoaDonRepository.save(hoaDon);
    }

}
