package vo;

public record Registration(Integer id,
                           User studant,
                           User professor,
                           boolean status){
}
