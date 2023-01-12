package Repository;

import Model.User;
import Service.FileContext;

public class UserRepository extends FileContext<User> {
    public UserRepository() {
        filePath = "C:\\Phu Phan\\CinemaManager-FaB-\\data\\User.txt";
        tClass = User.class;
    }
}
