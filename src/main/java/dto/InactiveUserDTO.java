package dto;

public class InactiveUserDTO {
    private final Integer id_user;

    private final String name;

    private final String surname;

    private final String email;

    private final String cpf;

    public InactiveUserDTO(Integer id_user, String name, String surname, String email, String cpf) {
        this.id_user = id_user;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cpf = cpf;
    }

    public Integer getId_user() {
        return id_user;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getCpf() {
        return cpf;
    }

}
