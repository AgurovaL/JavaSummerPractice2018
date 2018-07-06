import com.agurova.dal.ImageRepository;
import com.agurova.dal.UserRepository;
import com.agurova.dal.impl.ImageRepositoryImpl;
import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.models.Image;
import com.agurova.models.User;
import org.apache.log4j.Logger;

import java.util.List;

public class Test {
    private static final Logger log = Logger.getLogger(Test.class);

    public static void main(String[] args) {
        ImageRepository imageRepository = new ImageRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl();

        //1
        User user1 = new User();
        user1.setName("Ivanov");
        user1.setLogin("ivanov");
        user1.setPassword("1111");

        User user2 = new User();
        user2.setName("Petrova");
        user2.setLogin("petrova");
        user2.setPassword("2222");

        //2
        Image image1 = new Image();
        image1.setAdress("address1");
        image1.setName("name1");

        Image image2 = new Image();
        image2.setAdress("address2");
        image2.setName("name2");

        User.addFavoriteImage(user1, image1);

 //------------------------
        userRepository.addUser(user1);
        userRepository.addUser(user2);
        imageRepository.addImage(image1);
        imageRepository.addImage(image2);


        List<User> users = userRepository.getAll();
        for (User user: users){
            System.out.println(user.getId() + user.getName());
        }

        System.out.println();
        List<Image> images = imageRepository.getAll();
        for (Image image: images){
            System.out.println(image.getId() + image.getName());
        }

        log.info("Это информационное сообщение!");
        log.error("Это сообщение ошибки");
    }
}
