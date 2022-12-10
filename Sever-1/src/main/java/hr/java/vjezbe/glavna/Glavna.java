package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

    public static final int BROJ_PROFESORA = 2;//2
    public static final int BROJ_PREDMETA = 3;//3
    public static final int BROJ_STUDENATA = 3;//2;
    public static final int BROJ_ISPITA = 1;

    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);


        Profesor[] poljeProfesora = unosProfesora(unos);
        Predmet[] poljePredmeta = unosPredmeta(unos, poljeProfesora);
        Student[] poljeStudenta = unosStudenata(unos);
        Ispit[] poljeIspita = unosIspita(unos, poljePredmeta, poljeStudenta);

        //ispis profesora
        //ispisSvegaUProfesor(poljeProfesora);
        //test Predmeti
        //isipisPredemtaSve(poljePredmeta);


        for (int i = 0; i < BROJ_ISPITA; i++)
        {
            if (poljeIspita[i].getOcjena() > 4)
            {
                System.out.println("Student " + poljeIspita[i].getStudent().getIme() + " " + poljeIspita[i].getStudent().getPrezime()
                + " je ostavrio ocjenu 'izvrstan' na predmetu '" + poljeIspita[i].getPredmet().getNaziv() + "'" );
            }
        }


    }

    private static Ispit[] unosIspita(Scanner unos, Predmet[] poljePredmeta, Student[] poljeStudenta) {
        Ispit[] poljeIspita = new Ispit[BROJ_ISPITA];
        for (int i = 0; i < BROJ_ISPITA; i++) {
            System.out.println("Unesite " + (i + 1) + ". ispitni rok: ");
            System.out.println("Odaberite predmet: ");
            for (int j = 0; j < BROJ_PREDMETA; j++) {
                System.out.println((j + 1) + ". " + poljePredmeta[j].getNaziv());
            }
            Integer izborPredmeta = unos.nextInt();
            unos.nextLine();
            Predmet predmet = poljePredmeta[izborPredmeta - 1];
            System.out.println("Odaberite studenta: ");
            for (int k = 0; k < BROJ_STUDENATA; k++) {
                System.out.println((k + 1) + ". " + poljeStudenta[k].getIme() + " " + poljeStudenta[k].getPrezime());
            }
            Integer izborStudenta = unos.nextInt();
            unos.nextLine();
            Student student = poljeStudenta[izborStudenta - 1];
            System.out.print("Unesite ocjenu na ispitu (1-5): ");
            Integer ocjena = unos.nextInt();
            unos.nextLine();
            System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");
            String datumString = unos.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
            LocalDateTime datumIVrijeme = LocalDateTime.parse(datumString, formatter);

            poljeIspita[i] = new Ispit(predmet, student, ocjena, datumIVrijeme);
        }
        return poljeIspita;
    }


    private static Student[] unosStudenata(Scanner unos) {
        Student[] poljeStudenta = new Student[BROJ_STUDENATA];
        for (int i = 0; i < BROJ_STUDENATA; i++) {
            System.out.println("Unesite " + (i + 1) + ". studenta: ");
            System.out.print("Unesite ime studenta: ");
            String ime = unos.nextLine();
            System.out.print("Unesite prezime studenta: ");
            String prezime = unos.nextLine();
            System.out.print("Unesite datum rodenja studenta " + ime + " " + prezime + " u formatu (dd.MM.yyyy.):");
            String datumRodenjaString = unos.nextLine();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate datumRodjenja = LocalDate.parse(datumRodenjaString, format);
            System.out.print("Unesite JMBAG studenta " + (prezime) + " " + (ime) + ":");
            String jmbag = unos.nextLine();

            poljeStudenta[i] = new Student(ime, prezime, jmbag, datumRodjenja);
        }
        return poljeStudenta;
    }

    private static void isipisPredemtaSve(Predmet[] poljePredmeta) {
        for (int i = 0; i < BROJ_PREDMETA; i++) {
            System.out.println((i + 1) + ". " + poljePredmeta[i].getSifra() + " " + poljePredmeta[i].getNaziv() + " "
                    + poljePredmeta[i].getBrojEctsBodova() + " " + poljePredmeta[i].getNositelj().getIme() + " " + poljePredmeta[i].getNositelj().getPrezime() + " " + poljePredmeta[i].getStudenti());
        }
    }

    private static Predmet[] unosPredmeta(Scanner unos, Profesor[] poljeProfesora) {
        Predmet[] poljePredmeta = new Predmet[BROJ_PREDMETA];
        for (int i = 0; i < BROJ_PREDMETA; i++) {
            System.out.println("Unesite " + (i + 1) + ". predmet: ");
            System.out.print("Unesite sifru predmeta: ");
            String sifra = unos.nextLine();
            System.out.print("Unesite naziv predmeta: ");
            String naziv = unos.nextLine();
            System.out.print("Unesite broj ECTS bodova za predmet '" + naziv + "':");
            Integer brojEctsBodova = unos.nextInt();
            unos.nextLine();
            System.out.println("Odaberite profesora:");
            for (int j = 0; j < BROJ_PROFESORA; j++) {
                System.out.println((j + 1) + ". " + poljeProfesora[j].getIme() + " " + poljeProfesora[j].getPrezime());
            }
            Integer brojNositelja = unos.nextInt();
            unos.nextLine();
            Profesor nositelj = poljeProfesora[brojNositelja - 1];
            System.out.print("Unesite broj studenata za predmet '" + naziv + "':");
            Integer brojStudenataUPredmetu = unos.nextInt();
            unos.nextLine();

            poljePredmeta[i] = new Predmet(sifra, naziv, brojEctsBodova, nositelj, brojStudenataUPredmetu);
        }
        return poljePredmeta;
    }

    private static void ispisSvegaUProfesor(Profesor[] poljeProfesora) {
        for (int i = 0; i < BROJ_PROFESORA; i++) {
            System.out.println((i + 1) + ". " + poljeProfesora[i].getSifra() + " " + poljeProfesora[i].getIme() + " "
                    + poljeProfesora[i].getPrezime() + " " + poljeProfesora[i].getTitula());
        }
    }

    private static Profesor[] unosProfesora(Scanner unos) {
        Profesor[] poljeProfesora = new Profesor[BROJ_PROFESORA];

        for (int i = 0; i < BROJ_PROFESORA; i++) {
            System.out.println("Unesite " + (i + 1) + ". profesora: ");
            System.out.print("Unesite sifru profesora: ");
            String sifra = unos.nextLine();
            System.out.print("Unesite ime profesora: ");
            String ime = unos.nextLine();
            System.out.print("Unesite prezime profesora: ");
            String prezime = unos.nextLine();
            System.out.print("Unesite titulu profesora: ");
            String titula = unos.nextLine();

            poljeProfesora[i] = new Profesor(sifra, ime, prezime, titula);
        }
        return poljeProfesora;
    }

}
