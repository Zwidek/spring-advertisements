package pl.pb.ogloszeniadrobne.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.pb.ogloszeniadrobne.dto.AdvertisementDto;
import pl.pb.ogloszeniadrobne.model.Advertisement;
import pl.pb.ogloszeniadrobne.repository.AdvertisementRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static pl.pb.ogloszeniadrobne.util.AdvertisementUtil.PAGE_SIZE;

@Service
@AllArgsConstructor
public class AdvertisementService {
    private final AdvertisementRepository advertisementRepository;

    public Page<AdvertisementDto> getAllAdvertisements(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AdvertisementDto> advertisements = advertisementRepository.findAll().stream().map(AdvertisementDtoMapper::mapper).toList();
        return getPageAttributes(pageSize, currentPage, startItem, advertisements);
    }

    public Page<AdvertisementDto> getAllAdvertisementsByCategory(Long id, Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<AdvertisementDto> advertisements = advertisementRepository.findAllByCategoryId(id).stream().map(AdvertisementDtoMapper::mapper).toList();
        return getPageAttributes(pageSize, currentPage, startItem, advertisements);
    }

    public void deleteAdvertisement(Long id) {
        advertisementRepository.deleteById(id);
    }

    public void addAdvertisement(Advertisement advertisement) {
        advertisementRepository.save(advertisement);
    }

    public Optional<Advertisement> getSingleAdvertisement(Long id) {
        return advertisementRepository.findById(id);
    }


    public List<AdvertisementDto> getAdvertisementByTitle(String title, int page) {
        return advertisementRepository.findAdvertisementByTitle(title, PageRequest.of(page, PAGE_SIZE)).stream().map(AdvertisementDtoMapper::mapper).toList();
    }

    private Page<AdvertisementDto> getPageAttributes(int pageSize, int currentPage, int startItem, List<AdvertisementDto> advertisements) {
        List<AdvertisementDto> adv = null;

        if (advertisements.size() < startItem) {
            advertisements = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, advertisements.size());
            adv = advertisements.subList(startItem, toIndex);
        }

        assert adv != null;
        return new PageImpl<>(adv, PageRequest.of(currentPage, PAGE_SIZE),
                advertisements.size());
    }
}
