package hr.java.vjezbe.entitet;

import java.time.LocalDate;

public class Student extends Osoba{

    private String jmbag;
    private LocalDate datumRodjena;

    public Student(String ime, String prezime, String jmbag, LocalDate datumRodjena) {
        super(ime,prezime);
        this.jmbag = jmbag;
        this.datumRodjena = datumRodjena;
    }

    public String getJmbag() {
        return jmbag;
    }

    public void setJmbag(String jmbag) {
        this.jmbag = jmbag;
    }

    public LocalDate getDatumRodjena() {
        return datumRodjena;
    }

    public void setDatumRodjena(LocalDate datumRodjena) {
        this.datumRodjena = datumRodjena;
    }
}
