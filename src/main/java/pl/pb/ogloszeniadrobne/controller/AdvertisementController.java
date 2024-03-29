package pl.pb.ogloszeniadrobne.controller;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.model.Advertisement;
import pl.pb.ogloszeniadrobne.model.User;
import pl.pb.ogloszeniadrobne.repository.AdvertisementRepository;
import pl.pb.ogloszeniadrobne.repository.UserRepository;
import pl.pb.ogloszeniadrobne.service.AdvertisementService;
import pl.pb.ogloszeniadrobne.service.CategoryService;
import pl.pb.ogloszeniadrobne.service.UserService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping
public class AdvertisementController {
    private final AdvertisementService advertisementService;
    private final AdvertisementRepository advertisementRepository;
    private final CategoryService categoryService;
    private final UserRepository userRepository;
    private final UserService userService;
    private final ArrayList<String> forbiddenWords;

    @Autowired
    public AdvertisementController(AdvertisementService advertisementService, AdvertisementRepository advertisementRepository, CategoryService categoryService, UserRepository userRepository, UserService userService, ArrayList<String> forbiddenWords) {
        this.advertisementService = advertisementService;
        this.advertisementRepository = advertisementRepository;
        this.categoryService = categoryService;
        this.userRepository = userRepository;
        this.userService = userService;
        this.forbiddenWords = forbiddenWords;
    }

    @GetMapping
    @ResponseBody
    Page<AdvertisementDto> getAllAdvertisements(Pageable pageable) {
        return advertisementService.getAllAdvertisements(pageable);
    }

    @GetMapping("/{id}")
    String getSingleAdvertisement(@PathVariable Long id, Model model) {
        Optional<Advertisement> advertisement = advertisementService.getSingleAdvertisement(id);
        advertisement.get().setViewCounter(advertisement.get().getViewCounter() + 1);
        advertisementService.addAdvertisement(advertisement.get());
        model.addAttribute("advertisement", advertisement);
        return "advertisement-info";
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteJobOffer(@PathVariable Long id) {
        advertisementService.deleteAdvertisement(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/add")
    String addAdvertisementThymeleaf(Model model) {
        model.addAttribute("newAdvertisement", new Advertisement());
        model.addAttribute("categories", categoryService.findAllCategories());
        return "new-advertisement";
    }

    @GetMapping(path = "/save")
    String saveAdvertisement(Advertisement advertisement, @AuthenticationPrincipal UserDetails currentUser, BindingResult bindingResult) {
        String username = currentUser.getUsername();
        Optional<User> findByUsername = userRepository.findByEmail(username);
        advertisement.setUser(findByUsername.get());
        LocalDateTime now = LocalDateTime.now();
        advertisement.setDateAdded(now);
        advertisement.setViewCounter(0L);
        for (String forbiddenWord : forbiddenWords) {
            if (advertisement.getDescription().contains(forbiddenWord))
                return "word-forbidden";
        }
        for (String forbiddenWord : forbiddenWords) {
            if (advertisement.getTitle().contains(forbiddenWord))
                return "word-forbidden";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:";
        }
        advertisementRepository.save(advertisement);
        return "redirect:/uploadimage/" + advertisement.getId();
    }

    @GetMapping(path = "/delete/{id}")
    String deleteAdvertisement(@PathVariable Long id, @AuthenticationPrincipal UserDetails currentUser) {
        Optional<Advertisement> getAdvertisementById = advertisementRepository.findById(id);
        boolean isOwner = getAdvertisementById.get().getUser().getEmail().equals(currentUser.getUsername());
        if ((isOwner) || userService.isCurrentUserAdmin()) {
            deleteJobOffer(id);
        }
        return "redirect:/category?page=1";
    }
}
