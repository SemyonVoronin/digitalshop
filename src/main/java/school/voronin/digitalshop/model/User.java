package school.voronin.digitalshop.model;

import java.util.UUID;

public record User (
        UUID id,
        String username,
        String email,
        String password,
        UserRole role
){

}
