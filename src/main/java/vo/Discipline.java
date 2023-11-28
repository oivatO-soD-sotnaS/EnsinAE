package vo;

public record Discipline(Integer id,
                         User professor,
                         String name,
                         String description) {
}
