package by.solbegsoft.shortener.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;
    @ManyToOne
    private User user;
    private String originUrl;
    private String shortUrlKey;
    private LocalDateTime createdDate;
    private LocalDateTime expirationDate;

    public Url(String originUrl, String shortUrlKey) {
        this.originUrl = originUrl;
        this.shortUrlKey = shortUrlKey;
    }

    @PrePersist
    protected void date(){
        createdDate = LocalDateTime.now();
        expirationDate = createdDate.plusDays(10);
    }
}