package models;

public record User(Integer id_user,
                   String name,
                   String surname,
                   String email,
                   String cpf,
                   String password,
                   String access_level,
                   boolean status
){
}
