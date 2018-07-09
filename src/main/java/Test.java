import com.agurova.dal.ImageRepository;
import com.agurova.dal.UserRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.StockImagesService;
import com.agurova.services.impl.UnsplashStockImagesService;
import org.apache.log4j.Logger;

public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static StockImagesService stockImagesService = new UnsplashStockImagesService();

    public static void main(String[] args) {

        ImageRepository imageRepository = new ImageRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        User user = new User();
        user.setName("alex");
        user.setLogin("ermolaxe");
        user.setPassword("123");

        Image image = stockImagesService.getRandomImage();

        user.addImage(image);
        userRepository.save(user);
    }
}
