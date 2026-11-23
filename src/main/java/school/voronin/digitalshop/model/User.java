package school.voronin.digitalshop.model;

public record User (
        Long id,
        String username,
        String email,
        String password,
        UserRole role
){

}
