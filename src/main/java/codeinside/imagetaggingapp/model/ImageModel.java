package codeinside.imagetaggingapp.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;

@Entity
@Data
@Table
@Component
public class ImageModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String imageName;

    @Column(name = "url")
    private String imageUrl;

    @Column(name = "tags")
    private String tags;

    @Lob
    private byte[] imageData;

}