package Service;

import Model.User;
import Repository.UserRepository;

import java.util.List;

public class UserService {
private UserRepository userRepository;
public UserService (){userRepository = new UserRepository();}
    public List<User> getAllUser(){return userRepository.getAll();}
    public List<User> getUser(){return userRepository.getUser();}
    public List<User> getAllStaff(){return userRepository.getStaff();}
    public User findCustomerById (long idUser){
        return userRepository.findById(idUser);
    }
    public User findCustomerByName(String name){return userRepository.findByName(name);}
    public void updateCustomerById (long id, User user){
        userRepository.updateById(id, user);
    }
    public void addCustomer (User user){
        userRepository.add(user);
    }
    public void deleteCustomerById (long id){
        userRepository.deleteById(id);
    }

    public boolean existsByUsername(String username) {
        List<User> users = this.getUser();
        for (User user : users) {
            if (user.getUserName().equals(username))
                return true;
        }
        return false;
    }
    public boolean existsByPhone(String phone) {
        List<User> users = this.getUser();
        for (User user : users) {
            if (user.getMobile().equals(phone))
                return true;
        }
        return false;
    }
    public boolean existById(Long id) {
        if (findCustomerById(id) != null) {
            return true;
        } else {
            return false;
        }
    }
    public User checkUserNamePassword(String username, String password) {
        List<User> list = userRepository.getAll();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(username) && list.get(i).getPassword().equals(password)) {
                return list.get(i);
            }
        }
        return null;
    }
}
