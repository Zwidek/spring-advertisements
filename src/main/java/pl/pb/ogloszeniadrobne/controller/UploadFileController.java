package pl.pb.ogloszeniadrobne.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
@AllArgsConstructor
public class UploadFileController {

    private AdvertisementService advertisementService;
    public static String UPLOAD_DIRECTORY = System.getProperty("user.dir") + "/src/main/resources/static/img";

    @GetMapping("/uploadimage/{id}")
    public String displayUploadForm(@PathVariable Long id, Model model) {
        model.addAttribute("advertisementId", id);
        return "upload-file";
    }

    @PostMapping("/upload/{id}")
    public String uploadImage(Model model, @RequestParam("image") MultipartFile file, @PathVariable Long id) throws IOException {
        StringBuilder fileNames = new StringBuilder();
        Path fileNameAndPath = Paths.get(UPLOAD_DIRECTORY, id + ".jpg");
        fileNames.append(id).append(".jpg");
        Files.write(fileNameAndPath, file.getBytes());
        model.addAttribute("msg", "Uploaded images: " + fileNames);

        Optional<Advertisement> singleAdvertisement = advertisementService.getSingleAdvertisement(id);
        singleAdvertisement.get().setUrl(String.valueOf(id));

        advertisementService.addAdvertisement(singleAdvertisement.get());

        return "redirect:/category?page=1";
    }
}