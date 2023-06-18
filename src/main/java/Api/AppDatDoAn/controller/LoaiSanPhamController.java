package Api.AppDatDoAn.controller;

import Api.AppDatDoAn.entity.LoaiSanPham;
import Api.AppDatDoAn.services.LoaiSanPhamService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@PreAuthorize("isAuthenticated()")
@RequestMapping("/loai-san-pham")
public class LoaiSanPhamController {
    @Autowired
    private LoaiSanPhamService loaiSanPhamService;

    @GetMapping
    public String loaiSanPham(Model model) {
        List<LoaiSanPham> loaiSanPhams = loaiSanPhamService.getAll();
        model.addAttribute("loaiSanPhams", loaiSanPhams);
        return "loaisanpham/loai-san-pham";
    }

    @GetMapping("/them-loai")
    public String themLoai(Model model) {
        model.addAttribute("newLoai", new LoaiSanPham());
        return "loaisanpham/them-loai";
    }
    @PostMapping("/them-loai")
    public String themLoai(@Valid @ModelAttribute("newLoai") LoaiSanPham loaiSanPham,
                           Model model, BindingResult result) {
        if (result.hasErrors())
        {
            List<FieldError> errors = result.getFieldErrors();
            for (FieldError error : errors)
            {
                model.addAttribute(error.getField() + "_error", error.getDefaultMessage());
            }
            return "loaisanpham/them-loai";
        }
        loaiSanPhamService.saveLoaiSanPham(loaiSanPham);
        return "redirect:/loai-san-pham";
    }

    @GetMapping("/sua-loai/{maloai}")
    public String suaLoai(@PathVariable("maloai")Long maLoai, Model model) {
        model.addAttribute("editLoai", loaiSanPhamService.getById(maLoai));
        return "loaisanpham/sua-loai";
    }
    @PostMapping("/sua-loai")
    public String suaLoai(@Valid @ModelAttribute("maloai")LoaiSanPham loaiSanPham) {
        loaiSanPhamService.updateLoaiSanPham(loaiSanPham);
        return "redirect:/loai-san-pham";
    }

    @GetMapping("/xoa-loai/{maloai}")
    public String xoaLoai(@PathVariable("maloai")Long maLoai) {
        loaiSanPhamService.removeLoaiSanPham(maLoai);
        return "redirect:/loai-san-pham";
    }
}
