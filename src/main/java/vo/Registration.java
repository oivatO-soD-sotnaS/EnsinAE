package vo;

public record Registration(Integer id_registration,
                           User user,
                           User professor,
                           boolean status){
    @Override
    public String toString() {
        return "Registration{" +
                "id_registration=" + id_registration +
                ", user=" + user +
                ", professor=" + professor +
                ", status=" + status +
                '}';
    }
}
