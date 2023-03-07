package pl.pb.ogloszeniadrobne.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import pl.pb.ogloszeniadrobne.model.Advertisement;
import pl.pb.ogloszeniadrobne.service.AdvertisementService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class UploadFileController {
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";
    private final AdvertisementService advertisementService;

    @Autowired
    public UploadFileController(AdvertisementService advertisementService) {
        this.advertisementService = advertisementService;
    }

    @GetMapping("/uploadimage/{id}")
    public String displayUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("advertisementId", id);
        return "upload-file";
    }

    @PostMapping("/upload/{id}")
    public String uploadImage(@RequestParam("image") MultipartFile file, @PathVariable Long id) throws IOException {
        Optional<Advertisement> singleAdvertisement = advertisementService.getSingleAdvertisement(id);
        if (file == null || file.isEmpty()) {
            return "redirect:/" + singleAdvertisement.get().getId();
        }

        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, id + ".jpg");
        Files.write(fileNameAndPath, file.getBytes());
        singleAdvertisement.get().setFilePath(String.valueOf(id));
        advertisementService.addAdvertisement(singleAdvertisement.get());
        return "redirect:/" + singleAdvertisement.get().getId();
    }
}