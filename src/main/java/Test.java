import com.agurova.dal.ImageRepository;
import com.agurova.dal.UserRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.models.User;
import com.agurova.services.external.StockImagesService;
import com.agurova.services.external.impl.UnsplashStockImageServiceImpl;
import com.agurova.services.helper.ImageMainService;
import com.agurova.services.helper.impl.ImageMainServiceImpl;
import org.apache.log4j.Logger;

import java.util.List;

public class Test {
    private static final Logger log = Logger.getLogger(Test.class);
    private static StockImagesService stockImagesService = new UnsplashStockImageServiceImpl();

    public static void main(String[] args) {
        StockImagesService sis = new UnsplashStockImageServiceImpl();
        Image image = sis.getRandomImage();
    }
}
