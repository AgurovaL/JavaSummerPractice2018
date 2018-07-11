import com.agurova.dal.ImageRepository;
import com.agurova.dal.UserRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.StockImagesService;
import com.agurova.services.impl.UnsplashStockImageServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static StockImagesService stockImagesService = new UnsplashStockImageServiceImpl();

    public static void main(String[] args) {

//      ImageRepository imageRepository = new ImageRepositoryImpl();
//        UserRepository userRepository = new UserRepositoryImpl();
//
//        User user = new User();
//        user.setName("alex");
//        user.setLogin("ermolaxe");
//        user.setPassword("123");
//
//        List<Image> images = stockImagesService.getAllImages();
//        for (Image image : images) {
//            imageRepository.save(image);
//        }
//
//        imageRepository.deleteAll();
       // user.addImage(images.get(0));
       // userRepository.save(user);
    }
}
