package pl.pb.ogloszeniadrobne.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.pb.ogloszeniadrobne.model.Advertisement;

import java.util.List;
import java.util.Optional;

public interface AdvertisementRepository extends JpaRepository<Advertisement, Long> {

    List<Advertisement> findAllByCategoryId(Long id);

    @Query("Select c from Advertisement c where lower(c.title) like lower(concat('%', ?1,'%'))")
    List<Advertisement> findAdvertisementByTitle(String title);

    Optional<Advertisement> findAdvertisementByUserEmailIsContainingIgnoreCase(String userName);

    @Override
    Page<Advertisement> findAll(Pageable page);
}
