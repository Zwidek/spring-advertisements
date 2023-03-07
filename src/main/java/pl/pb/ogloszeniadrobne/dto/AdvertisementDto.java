package pl.pb.ogloszeniadrobne.dto;

import lombok.Builder;
import lombok.Getter;
import pl.pb.ogloszeniadrobne.model.User;

import java.time.LocalDateTime;

@Getter
@Builder
public class AdvertisementDto {
    private Long id;
    private String title;
    private String url;
    private String description;
    private double price;
    private LocalDateTime dateAdded;
    private User user;
}
