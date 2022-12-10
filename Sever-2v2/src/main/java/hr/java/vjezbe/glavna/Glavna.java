package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Glavna {

    public static final int BROJ_PROFESORA = 2;
    public static final int BROJ_PREDMETA = 2;
    public static final int BROJ_STUDENATA = 2;
    public static final int BROJ_ISPITA = 2;

    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);


        System.out.print("Unesite broj obrazovnih ustanova:");
        Integer brojUstanova = 0;
        do {
            brojUstanova = unos.nextInt();
            unos.nextLine();

            if (brojUstanova > 3 || brojUstanova < 0) {
                System.out.println("Krivi unos!");
            }
        }
        while (brojUstanova > 3 || brojUstanova < 0);


        //Dvorana[] dvorana = new Dvorana[BROJ_ISPITA];TODO

        for (int aa = 0; aa < brojUstanova; aa++) {

            System.out.println("Unesite podatke za " + (aa + 1) + " obrazovnu ustanovu: ");

            System.out.println("aa = " + aa);


            Profesor[] poljeProfesora = unosProfesora(unos);
            System.out.println("Unijeli ste sljedeće profesore:");
            for (Profesor profesor : poljeProfesora) {
                System.out.println();
                System.out.println("Sifra profesora: " + profesor.getSifra());
                System.out.println("Ime profesora: " + profesor.getIme());
                System.out.println("prezime profesora: " + profesor.getPrezime());
                System.out.println("titula profesora: " + profesor.getTitula());
                System.out.println();
            }

            Predmet[] poljePredmeta = unosPredmeta(unos, poljeProfesora);
            System.out.println("Unijeli ste sljedeće predmete:");
            for (Predmet predmet : poljePredmeta) {
                System.out.println();
                System.out.println("Sifra predmeta: " + predmet.getSifra());
                System.out.println("Naziv predmeta: " + predmet.getNaziv());
                System.out.println("Broj ECTS bodova: " + predmet.getBrojEctsBodova());
                System.out.println("Nositelj predmeta: " + predmet.getNositelj().getIme() +
                        " " + predmet.getNositelj().getPrezime());
                System.out.println();
            }

            Student[] poljeStudenta = unosStudenata(unos);
            System.out.println("Unijeli ste sljedeće studente:");
            for (Student student : poljeStudenta) {
                System.out.println();
                System.out.println("Ime studenta: " + student.getIme());
                System.out.println("Prezme studenta: " + student.getPrezime());
                System.out.println("Jmbag studenta: " + student.getJmbag());
                System.out.println("Datum rodenja studenta: " + student.getDatumRodjena().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
                System.out.println();
            }
            Ispit[] poljeIspita = unosIspita(unos, poljePredmeta, poljeStudenta);
            System.out.println("Unijeli ste sljedeće ispitne rokove:");
            for (Ispit ispit : poljeIspita) {
                System.out.println();
                System.out.println("Za ispitni rok iz predmeta: " + ispit.getPredmet().getNaziv() + ".");
                if (ispit.getOcjena().equals(5)) {
                    System.out.println("Student " + ispit.getStudent().getIme() + " " + ispit.getStudent().getPrezime() +
                            " je ostvario ocjenu 'izvrstan' na predmetu " + ispit.getPredmet().getNaziv() + ":");
                } else {
                    System.out.println("Niti jedan student nije ostvario ocjenu 'izvrstan'.");
                }
                System.out.println();
            }


            ObrazovnaUstanova[] obrazovneUstanove = new ObrazovnaUstanova[brojUstanova];
            System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 -\n" +
                    "Veleučilište Jave, 2 - Fakultet računarstva):");
            Integer odabirUstanove = unos.nextInt();
            unos.nextLine();
            System.out.println("Unesite naziv obrazovne ustanove:");
            String nazivUstanove = unos.nextLine();
            if (odabirUstanove == 1) {
                obrazovneUstanove[aa] = new VeleucilisteJave(nazivUstanove, poljePredmeta, poljeProfesora, poljeStudenta, poljeIspita);

            } else if (odabirUstanove == 2) {
                obrazovneUstanove[aa] = new FakultetRacunarstva(nazivUstanove, poljePredmeta, poljeProfesora, poljeStudenta, poljeIspita);
            }



            int ocjenaZavrsnogRadaStudenta;
            int ocjenaObraneZavrsnogRadaStudenta;
            int godina = 2022;
            int studentiUpisaniDalje = 0;
        for (Student student : poljeStudenta) {
            System.out.println("Unesite ocjenu završnog rada za studenta: " + student.getIme() +
                    " " + student.getPrezime() + ":");
            ocjenaZavrsnogRadaStudenta = unos.nextInt();
            unos.nextLine();
            System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + student.getIme() +
                    " " + student.getPrezime() + ":");
            ocjenaObraneZavrsnogRadaStudenta = unos.nextInt();
            unos.nextLine();
            System.out.println("Konačna ocjena studija studenta: " + student.getIme() +
                    " " + student.getPrezime() + ":");
            if (odabirUstanove == 1) {
                System.out.println(((VeleucilisteJave) obrazovneUstanove[aa]).izracunajKonacnuOcjenuStudijaZaStudenta(poljeIspita, ocjenaZavrsnogRadaStudenta,
                        ocjenaObraneZavrsnogRadaStudenta,student));
            }
            if (odabirUstanove == 2) {
                System.out.println(((FakultetRacunarstva) obrazovneUstanove[aa]).izracunajKonacnuOcjenuStudijaZaStudenta(poljeIspita, ocjenaZavrsnogRadaStudenta,
                        ocjenaObraneZavrsnogRadaStudenta,student));
            }


                if (studentiUpisaniDalje == 2)//TODO 1
                {
                if (odabirUstanove == 1) {

                    System.out.println("Najbolji student 2022. godine je " +
                            obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getIme() +
                            " " + obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getPrezime() + " "
                            + obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getJmbag());
                }

                if (odabirUstanove == 2) {
                    System.out.println("Najbolji student 2022. godine je " +
                            obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getIme() +
                            " " + obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getPrezime() + " "
                            + obrazovneUstanove[aa].odrediNajuspjesnijegStudentaNaGodini(godina).getJmbag());

                    System.out.println("Student koji je osvojio rektorovu nagradu je: " +
                            ((FakultetRacunarstva) obrazovneUstanove[aa]).odrediStudentaZaRektorovuNagradu().getIme() +
                            " " + ((FakultetRacunarstva) obrazovneUstanove[aa]).odrediStudentaZaRektorovuNagradu().getPrezime() + " "
                            + ((FakultetRacunarstva) obrazovneUstanove[aa]).odrediStudentaZaRektorovuNagradu().getJmbag());
                }
            }
            studentiUpisaniDalje=1;
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

            System.out.print("Unesite dvoranu:");
            String nazivDvorane = unos.nextLine();
            System.out.print("Unesite zgradu:");
            String nazivZgrade = unos.nextLine();
            Dvorana dvorana = new Dvorana(nazivDvorane, nazivZgrade);

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

            poljeIspita[i] = new Ispit(predmet, student, ocjena, datumIVrijeme, dvorana);
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
            Student[] studenti = new Student[brojStudenataUPredmetu];

            poljePredmeta[i] = new Predmet(sifra, naziv, brojEctsBodova, nositelj, studenti);
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

            poljeProfesora[i] = new Profesor.ProfesorBuilder().setSifra(sifra).setIme(ime).setPrezime(prezime).setTitula(titula).createProfesor();
        }
        return poljeProfesora;
    }


}
