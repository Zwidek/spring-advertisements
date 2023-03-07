package pl.pb.ogloszeniadrobne.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.model.Category;
import pl.pb.ogloszeniadrobne.service.AdvertisementService;
import pl.pb.ogloszeniadrobne.service.CategoryService;
import pl.pb.ogloszeniadrobne.service.WelcomeMessageService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.CURRENT_PAGE;
import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.PAGE_SIZE;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final AdvertisementService advertisementService;

    @Autowired
    public CategoryController(CategoryService categoryService, AdvertisementService advertisementService) {
        this.categoryService = categoryService;
        this.advertisementService = advertisementService;
    }

    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }

    @GetMapping("/{id}")
    public String category(@PathVariable Long id,
                           Model model,
                           @RequestParam Optional<Integer> page,
                           @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(CURRENT_PAGE);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<AdvertisementDto> advPage = advertisementService.getAllAdvertisementsByCategory(id, PageRequest.of(currentPage - 1, pageSize));
        getPageAtributes(model, advPage);
        return "category";
    }

    @GetMapping()
    public String home(Model model,
                       Principal principal,
                       @RequestParam Optional<Integer> page,
                       @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(CURRENT_PAGE);
        int pageSize = size.orElse(PAGE_SIZE);
        String loggedInUser = principal.getName();
        model.addAttribute("loggedInuser", loggedInUser);
        Page<AdvertisementDto> advPage = advertisementService.getAllAdvertisements(PageRequest.of(currentPage - 1, pageSize));
        getPageAtributes(model, advPage);
        return "home";
    }

    private void getPageAtributes(Model model, Page<AdvertisementDto> advPage) {
        model.addAttribute("categories", findAllCategories());
        model.addAttribute("advertisements", advPage);
        int totalPages = advPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
    }
}
