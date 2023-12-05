package models;

public record Discipline(Integer id_discipline,
                         User professor,
                         String name,
                         String description,
                         String access_code) {
}
