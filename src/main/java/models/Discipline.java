package models;

public record Discipline(Integer id_discipline,
                         User professor,
                         String name,
                         String description,
                         String access_code) {
    @Override
    public String toString() {
        return "Discipline{" +
                "id_discipline=" + id_discipline +
                ", professor=" + professor +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", access_code='" + access_code + '\'' +
                '}';
    }
}
