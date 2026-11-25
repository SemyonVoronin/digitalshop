package school.voronin.digitalshop.service;

import org.springframework.stereotype.Service;
import school.voronin.digitalshop.entity.UserEntity;
import school.voronin.digitalshop.model.User;
import school.voronin.digitalshop.model.UserRole;
import school.voronin.digitalshop.repository.UserRepository;

import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService{

    public final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createUser(User user){
        if (userRepository.existsByEmail(user.email())){
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }

        UserEntity entity = new UserEntity();
        entity.setUsername(user.username());
        entity.setEmail(user.email());
        entity.setPassword(user.password());
        entity.setRole(UserRole.BUYER);

        UserEntity saved = userRepository.save(entity);
        return toDomain(saved);

    }

    @Override
    public User login(String email, String password){
        UserEntity entity = userRepository.findByEmail(email).
                orElseThrow(() -> new IllegalArgumentException("Неверный email или пароль"));

        if (!entity.getPassword().equals(password)){
            throw new IllegalArgumentException("Неверный email или пароль");
        }
        return toDomain(entity);
    }

    private User toDomain(UserEntity entity){
        return new User(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getPassword(),
                entity.getRole()
        );
    }
}
