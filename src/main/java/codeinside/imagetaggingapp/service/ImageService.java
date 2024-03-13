package codeinside.imagetaggingapp.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import codeinside.imagetaggingapp.model.ImageModel;
import codeinside.imagetaggingapp.repositories.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImage(File imageFile) {
        try {
            byte[] imageData = convertImageToByteArray(imageFile);
            ImageModel imageModel = new ImageModel();
            imageModel.setImageData(imageData);

            // сохраняние изображения в базе данных
            imageRepository.save(imageModel);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] convertImageToByteArray(File imageFile) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(imageFile);
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            int b;
            while ((b = fileInputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            return byteArrayOutputStream.toByteArray();
        }
    }

    public List<ImageModel> getAllImages() {
        return imageRepository.findAll();
    }

    public ImageModel getImageById(Long id) {
        return imageRepository.findById(id).orElse(null);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}
