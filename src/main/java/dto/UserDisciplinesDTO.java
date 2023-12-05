package dto;

public class UserDisciplinesDTO {
    private final Integer id_discipline;
    private final String name;
    private final String disciplineProfessor;
    private final String professorEmail;
    private final String disciplineDescription;
    private final String disciplineAccessCode;

    public UserDisciplinesDTO(Integer id, String name, String disciplineProfessor, String professorEmail, String disciplineDescription, String disciplineAccessCode) {
        this.id_discipline = id;
        this.name = name;
        this.disciplineProfessor = disciplineProfessor;
        this.professorEmail = professorEmail;
        this.disciplineDescription = disciplineDescription;
        this.disciplineAccessCode = disciplineAccessCode;
    }

    public Integer getId_discipline() {
        return id_discipline;
    }

    public String getName() {
        return name;
    }

    public String getDisciplineProfessor() {
        return disciplineProfessor;
    }

    public String getProfessorEmail() {
        return professorEmail;
    }

    public String getDisciplineDescription() {
        return disciplineDescription;
    }

    public String getDisciplineAccessCode() {
        return disciplineAccessCode;
    }

}
