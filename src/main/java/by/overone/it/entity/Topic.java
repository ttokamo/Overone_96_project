package by.overone.it.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
public class Topic {
    @Id
    @GeneratedValue(generator = "uuid-generator")
    @GenericGenerator(name = "uuid-generator", strategy = "uuid")
    @Column(nullable = false)
    private String id;
    @Setter
    private String authorId;
    @Setter
    private String author;
    @Setter
    private String message;
    @Setter
    private String topicName;
    @Setter
    private String imageFileName;
    @Setter
    private LocalDateTime createDate;
}
