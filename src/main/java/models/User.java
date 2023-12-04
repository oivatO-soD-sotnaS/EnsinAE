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
    @Override
    public String toString() {
        return "User{" +
                "user=" + id_user +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", password='" + password + '\'' +
                ", access_level='" + access_level + '\'' +
                ", status=" + status +
                '}';
    }
}
