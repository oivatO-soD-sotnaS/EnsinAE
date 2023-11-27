package DTO;

public class Registration {
    private Integer id;
    private User studant;
    private User professor;
    private boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getStudant() {
        return studant;
    }

    public void setStudant(User studant) {
        this.studant = studant;
    }

    public User getProfessor() {
        return professor;
    }

    public void setProfessor(User professor) {
        this.professor = professor;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
