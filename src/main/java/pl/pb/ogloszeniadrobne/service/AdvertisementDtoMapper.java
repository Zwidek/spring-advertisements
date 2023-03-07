package pl.pb.ogloszeniadrobne.service;

import lombok.NoArgsConstructor;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.model.Advertisement;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class AdvertisementDtoMapper {

    private List<AdvertisementDto> mapToAdvertismentDtos(List<Advertisement> allAdvertisements) {
        return allAdvertisements.stream()
                .map(AdvertisementDtoMapper::mapper)
                .toList();
    }

    static AdvertisementDto mapper(Advertisement advertisement) {
        return AdvertisementDto.builder()
                .id(advertisement.getId())
                .user(advertisement.getUser())
                .dateAdded(advertisement.getDateAdded())
                .title(advertisement.getTitle())
                .price(advertisement.getPrice())
                .url(advertisement.getUrl())
                .description(advertisement.getDescription())
                .build();
    }
}
