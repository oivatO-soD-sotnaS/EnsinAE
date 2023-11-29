package vo;

public record Registration(Integer id_registration,
                           User studant,
                           User professor,
                           boolean status){
}
