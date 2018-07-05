import com.agurova.dal.UserRepository;
import com.agurova.dal.impl.UserRepositoryImpl;
import com.agurova.hibernate.HibernateUtil;
import com.agurova.models.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


public class Test {

  //  private static final Logger logger = LogManager.getLogger("HelloWorld");

    public static void main(String[] args) {

       // logger.info("Hello, World!");
//        try {
//            LogManager.getLogManager().readConfiguration(
//                    Test.class.getResourceAsStream("src/main/resources/log4j.xml"));
//        } catch (IOException e) {
//            System.err.println("Could not setup logger configuration: " + e.toString());
//        }
        HibernateUtil hibernateUtil = new HibernateUtil();
        UserRepository userRepository = new UserRepositoryImpl();

        User user1 = new User();
        user1.setName("Ivanov");
        user1.setLogin("ivanov");
        user1.setPassword("1111");

        User user2 = new User();
        user2.setName("Petrova");
        user2.setLogin("petrova");
        user2.setPassword("2222");

        userRepository.addUser(user1);
        userRepository.addUser(user2);
        List<User> users = userRepository.getAll();
    }
}
