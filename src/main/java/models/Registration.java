package models;

public record Registration(Integer id_registration,
                           User user,
                           User professor,
                           boolean status){
}
