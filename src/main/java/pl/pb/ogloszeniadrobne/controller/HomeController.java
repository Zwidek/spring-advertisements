package pl.pb.ogloszeniadrobne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.model.Category;
import pl.pb.ogloszeniadrobne.service.AdvertisementService;
import pl.pb.ogloszeniadrobne.service.CategoryService;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.CURRENT_PAGE;
import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.PAGE_SIZE;

@Controller
class HomeController {
    private final CategoryService categoryService;
    private final AdvertisementService advertisementService;

    @Autowired
    public HomeController(CategoryService categoryService, AdvertisementService advertisementService) {
        this.categoryService = categoryService;
        this.advertisementService = advertisementService;
    }

    public List<Category> findAllCategories() {
        return categoryService.findAllCategories();
    }
    @GetMapping("/")
    String home(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            model.addAttribute("authentication", authentication);
            return "index";
        } else {
            return "index";
        }
    }

    @GetMapping("/category")
    public String home(Model model,
                       Principal principal,
                       @RequestParam Optional<Integer> page,
                       @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(CURRENT_PAGE);
        int pageSize = size.orElse(PAGE_SIZE);
        String loggedInUser = principal.getName();
        Page<AdvertisementDto> advPage = advertisementService.getAllAdvertisements(PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("loggedInuser", loggedInUser);
        model.addAttribute("category", findAllCategories());
        model.addAttribute("advertisement", advPage);
        int totalPages = advPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "home";
    }
}