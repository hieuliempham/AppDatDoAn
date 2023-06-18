package Api.AppDatDoAn.services;

import Api.AppDatDoAn.reponsitory.IHoaDonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ThongKeService {
    @Autowired
    private IHoaDonRepository hoaDonRepository;

    public Map<Integer, Double> thongKeTongTienTheoThang(int year, String mach) {
        List<Object[]> results = hoaDonRepository.getMonthlyRevenue(year, mach);

        Map<Integer, Double> revenueByMonth = new HashMap<>();
        for (Object[] result : results) {
            int month = (int) result[0];
            double total = (double) result[1];
            revenueByMonth.put(month, total);
        }

        return revenueByMonth;
    }

    public Map<Integer, Double> thongKeTongTienTheoThang(int year) {
        List<Object[]> results = hoaDonRepository.getMonthlyRevenue(year);

        Map<Integer, Double> revenueByMonth = new HashMap<>();
        for (Object[] result : results) {
            int month = (int) result[0];
            double total = (double) result[1];
            revenueByMonth.put(month, total);
        }

        return revenueByMonth;
    }

    public Map<Integer, Double> thongKeTongTienTheoNgay(int month, int year, String mach) {
        List<Object[]> results = hoaDonRepository.getDailyRevenue(month, year, mach);

        Map<Integer, Double> revenueByDay = new HashMap<>();
        for (Object[] result : results) {
            int day = (int) result[0];
            double total = (double) result[1];
            revenueByDay.put(day, total);
        }

        return revenueByDay;
    }

    public Map<Integer, Double> thongKeTongTienTheoNgay(int month, int year) {
        List<Object[]> results = hoaDonRepository.getDailyRevenue(month, year);

        Map<Integer, Double> revenueByDay = new HashMap<>();
        for (Object[] result : results) {
            int day = (int) result[0];
            double total = (double) result[1];
            revenueByDay.put(day, total);
        }

        return revenueByDay;
    }
}
