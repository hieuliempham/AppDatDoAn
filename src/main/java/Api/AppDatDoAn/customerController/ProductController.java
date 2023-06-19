package Api.AppDatDoAn.customerController;

import Api.AppDatDoAn.entity.LoaiSanPham;
import Api.AppDatDoAn.entity.SanPham;
import Api.AppDatDoAn.services.LoaiSanPhamService;
import Api.AppDatDoAn.services.SanPhamService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ProductController {
    private final SanPhamService sanPhamService;

    private final LoaiSanPhamService loaiSanPhamService;

    @GetMapping("/menu")
    public String menu(Model model) {
        model.addAttribute("page", "Sản phẩm");
        model.addAttribute("title", "Menu");
        List<LoaiSanPham> categories = loaiSanPhamService.getAll();
        List<SanPham> products = sanPhamService.getAllSanPham();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "customer/index";
    }


    @GetMapping("/product-detail/{id}")
    public String details(@PathVariable("id") String id, Model model) {
        SanPham product = sanPhamService.getSanPhamById(id);
        List<SanPham> productDtoList = sanPhamService.timKiemTheoLoai(product.getLoaisanpham().getMaloai());
        model.addAttribute("products", productDtoList);
        model.addAttribute("title", "Product Detail");
        model.addAttribute("page", "Product Detail");
        model.addAttribute("productDetail", product);
        return "customer/product-detail";
    }

//    @GetMapping("/shop-detail")
//    public String shopDetail(Model model) {
//        List<CategoryDto> categories = loaiSanPhamService.getCategoriesAndSize();
//        model.addAttribute("categories", categories);
//        List<ProductDto> products = sanPhamService.randomProduct();
//        List<ProductDto> listView = sanPhamService.listViewProducts();
//        model.addAttribute("productViews", listView);
//        model.addAttribute("title", "Shop Detail");
//        model.addAttribute("page", "Shop Detail");
//        model.addAttribute("products", products);
//        return "shop-detail";
//    }
//
//
//    @GetMapping("/high-price")
//    public String filterHighPrice(Model model) {
//        List<CategoryDto> categories = loaiSanPhamService.getCategoriesAndSize();
//        model.addAttribute("categories", categories);
//        List<ProductDto> products = sanPhamService.filterHighProducts();
//        List<ProductDto> listView = sanPhamService.listViewProducts();
//        model.addAttribute("title", "Shop Detail");
//        model.addAttribute("page", "Shop Detail");
//        model.addAttribute("productViews", listView);
//        model.addAttribute("products", products);
//        return "shop-detail";
//    }
//
//
//    @GetMapping("/lower-price")
//    public String filterLowerPrice(Model model) {
//        List<CategoryDto> categories = loaiSanPhamService.getCategoriesAndSize();
//        model.addAttribute("categories", categories);
//        List<ProductDto> products = sanPhamService.filterLowerProducts();
//        List<ProductDto> listView = sanPhamService.listViewProducts();
//        model.addAttribute("productViews", listView);
//        model.addAttribute("title", "Shop Detail");
//        model.addAttribute("page", "Shop Detail");
//        model.addAttribute("products", products);
//        return "shop-detail";
//    }
//
//    @GetMapping("/find-products/{id}")
//    public String productsInCategory(@PathVariable("id") Long id, Model model) {
//        List<CategoryDto> categoryDtos = loaiSanPhamService.getCategoriesAndSize();
//        List<ProductDto> productDtos = sanPhamService.findByCategoryId(id);
//        List<ProductDto> listView = sanPhamService.listViewProducts();
//        model.addAttribute("productViews", listView);
//        model.addAttribute("categories", categoryDtos);
//        model.addAttribute("title", productDtos.get(0).getCategory().getName());
//        model.addAttribute("page", "Product-Category");
//        model.addAttribute("products", productDtos);
//        return "products";
//    }
//
//
//    @GetMapping("/search-product")
//    public String searchProduct(@RequestParam("keyword") String keyword, Model model) {
//        List<CategoryDto> categoryDtos = loaiSanPhamService.getCategoriesAndSize();
//        List<ProductDto> productDtos = sanPhamService.searchProducts(keyword);
//        List<ProductDto> listView = sanPhamService.listViewProducts();
//        model.addAttribute("productViews", listView);
//        model.addAttribute("categories", categoryDtos);
//        model.addAttribute("title", "Search Products");
//        model.addAttribute("page", "Result Search");
//        model.addAttribute("products", productDtos);
//        return "products";
//    }
}
