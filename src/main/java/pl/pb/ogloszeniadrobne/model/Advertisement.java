package pl.pb.ogloszeniadrobne.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Advertisement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String url;
    private double price;
    private String description;
    private Long viewCounter;
    @Column(name = "date_added")
    private LocalDateTime dateAdded;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "category_id")
    private Category category;
    @JsonProperty
    private String userName() {
        return user.getUsername();
    }
    @JsonProperty
    private String categoryName() {
        return category.getName();
    }
}
