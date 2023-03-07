package pl.pb.ogloszeniadrobne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.service.AdvertisementService;
import pl.pb.ogloszeniadrobne.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.CURRENT_PAGE;
import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.PAGE_SIZE;

@RequestMapping("/search")
@Controller
public class SearchController {
    private final CategoryService categoryService;
    private final AdvertisementService advertisementService;

    @Autowired
    public SearchController(CategoryService categoryService, AdvertisementService advertisementService) {
        this.categoryService = categoryService;
        this.advertisementService = advertisementService;
    }

    @GetMapping()
    public String getAllAdvertisementsForThymeleaf(@RequestParam String title,
                                                   Model model,
                                                   @RequestParam Optional<Integer> page,
                                                   @RequestParam Optional<Integer> size) {
        int currentPage = page.orElse(CURRENT_PAGE);
        int pageSize = size.orElse(PAGE_SIZE);
        Page<AdvertisementDto> advPage = advertisementService.getAdvertisementByTitle(title, PageRequest.of(currentPage - 1, pageSize));
        getPageAtributes(model, advPage);
        return "search";
    }

    private void getPageAtributes(Model model, Page<AdvertisementDto> advPage) {
        model.addAttribute("categories", categoryService.findAllCategories());
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
