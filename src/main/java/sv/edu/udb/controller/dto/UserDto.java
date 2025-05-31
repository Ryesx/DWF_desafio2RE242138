package sv.edu.udb.controller.dto;

import lombok.Data;
import sv.edu.udb.domain.User;

@Data
public class UserDto {
    private Integer idUser;
    private String username;
    private String firstname;
    private String lastname;
    private Integer age;
    // Sin password por seguridad

    public UserDto(User user) {
        this.idUser = user.getIdUser();
        this.username = user.getUsername();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.age = user.getAge();
    }
}