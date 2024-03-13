package codeinside.imagetaggingapp.service;

import codeinside.imagetaggingapp.model.ImageModel;
import codeinside.imagetaggingapp.repositories.ImageRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
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
    public void saveImageTest() throws IOException {
        byte[] imageData = new byte[]{1, 2, 3}; // Пример данных для изображения
        File imageFile = new File("screen.png");

        doReturn(imageData).when(imageService).convertImageToByteArray(imageFile);

        imageService.saveImage(imageFile);

        ArgumentCaptor<ImageModel> argument = ArgumentCaptor.forClass(ImageModel.class);
        verify(imageRepository).save(argument.capture());

        assertEquals(imageData, argument.getValue().getImageData());
    }

//    @Test
//    public void testSaveImage() {
//        MockitoAnnotations.openMocks(this);
//
//        File imageFile = new File("src/main/resources/imageFiles/screen.png");
//
//        assertDoesNotThrow(() -> {
//            when(imageRepository.save(any(ImageModel.class))).thenReturn(null);
//            imageService.saveImage(imageFile);
//        });
//    }

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