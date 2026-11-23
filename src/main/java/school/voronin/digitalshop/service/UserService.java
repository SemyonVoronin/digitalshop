package school.voronin.digitalshop.service;


import school.voronin.digitalshop.model.User;

public interface UserService {
    User createUser(User user);
    User login(String email, String password);
}
