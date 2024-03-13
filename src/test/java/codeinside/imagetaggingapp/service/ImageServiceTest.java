package codeinside.imagetaggingapp.service;

import codeinside.imagetaggingapp.model.ImageModel;
import codeinside.imagetaggingapp.repositories.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ImageServiceTest {

    @Mock
    private ImageRepository imageRepository;

    @InjectMocks
    private ImageService imageService;

    @Test
    public void testSaveImage() {
        MockitoAnnotations.openMocks(this);

        File imageFile = new File("src/main/resources/imageFiles/screen.png");

        assertDoesNotThrow(() -> {
            when(imageRepository.save(any(ImageModel.class))).thenReturn(null);
            imageService.saveImage(imageFile);
        });
    }

    @Test
    public void getAllImageTest() {
        ImageService imageService = new ImageService(imageRepository);
        List<ImageModel> list = List.of(new ImageModel(), new ImageModel());
        when(imageRepository.findAll()).thenReturn(list);
        assertEquals(list, imageService.getAllImages());
    }

    @Test
    public void getImageByIdTest() {
        ImageService imageService = new ImageService(imageRepository);
        Long id = 1L;
        ImageModel model = new ImageModel();
        when(imageRepository.findById(id)).thenReturn(java.util.Optional.ofNullable(model));
        assertEquals(model, imageService.getImageById(id));
    }

    @Test
    public void deleteImageTest() {
        ImageService imageService = new ImageService(imageRepository);
        Long id = 1L;
        imageService.deleteImage(id);
        verify(imageRepository, Mockito.times(1)).deleteById(id);
    }
}