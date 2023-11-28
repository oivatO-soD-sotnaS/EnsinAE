package vo;

public record User(Integer id,
                   String name,
                   String surname,
                   String email,
                   String cpf,
                   String password,
                   String access_level,
                   boolean status
){

}
