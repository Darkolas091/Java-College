package hr.java.vjezbe.entitet;


import java.math.BigDecimal;
import java.util.List;

public abstract class ObrazovnaUstanova {
    private String nazivUstanove;
    private List<Predmet> predmeti;
    private List<Profesor> profesori;
    private List<Student> studenti;
    private List<Ispit> ispiti;

    public ObrazovnaUstanova(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        this.nazivUstanove = nazivUstanove;
        this.predmeti = predmeti;
        this.profesori = profesori;
        this.studenti = studenti;
        this.ispiti = ispiti;
    }

    public abstract Student odrediNajuspjesnijegStudentaNaGodini(Integer godina);

    public String getNazivUstanove() {
        return nazivUstanove;
    }

    public void setNazivUstanove(String nazivUstanove) {
        this.nazivUstanove = nazivUstanove;
    }

    public List<Predmet> getPredmeti() {
        return predmeti;
    }

    public void setPredmeti(List<Predmet> predmeti) {
        this.predmeti = predmeti;
    }

    public List<Profesor> getProfesori() {
        return profesori;
    }

    public void setProfesori(List<Profesor> profesori) {
        this.profesori = profesori;
    }

    public List<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(List<Student> studenti) {
        this.studenti = studenti;
    }

    public List<Ispit> getIspiti() {
        return ispiti;
    }

    public void setIspiti(List<Ispit> ispiti) {
        this.ispiti = ispiti;
    }

    public abstract BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaDiplomskogRada, Integer ocjenaObraneDiplomskogRada, Student student);
}
