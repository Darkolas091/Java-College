package hr.java.vjezbe.glavna;

import hr.java.vjezbe.entitet.*;
import hr.java.vjezbe.sortiranje.StudentSorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.*;
import java.util.Scanner;

/**
 * Glavna klasa s metodom main i pomoćnim metodama.
 */
public class Glavna {

    public static final int BROJ_PROFESORA = 2;
    public static final int BROJ_PREDMETA = 2;
    public static final int BROJ_STUDENATA = 2;
    public static final int BROJ_ISPITA = 2;
    public static final int BROJ_OBRAZOVNIH_USTANOVA = 2;
    public static final Logger logger = LoggerFactory.getLogger(Glavna.class);

    /**
     * Početak programa.
     * @param args Argumenti iz komandne linije.
     */
    public static void main(String[] args) {
        Scanner unos = new Scanner(System.in);

        2
        3



        Integer brojUstanova = 0;
        boolean valid;
        do {
            try {
                System.out.print("Unesite broj obrazovnih ustanova:");
                brojUstanova = unos.nextInt();
                    if (brojUstanova != 2)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
            catch (InputMismatchException e)
            {
                System.out.println("Neispravan unos!");
                logger.error("Krivi unos", e);
                valid = false;
            }
            finally {
                unos.nextLine();
            }
        }
        while (!valid);


        //Dvorana[] dvorana = new Dvorana[BROJ_ISPITA];

        for (int aa = 0; aa < brojUstanova; aa++) {

            System.out.println("Unesite podatke za " + (aa + 1) + " obrazovnu ustanovu: ");

            System.out.println("aa = " + aa);

            List<Profesor> profesorList = unosProfesora(unos);
            System.out.println("Unijeli ste sljedeće profesore:");
            for (Profesor profesor : profesorList) {
                System.out.println();
                System.out.println("Sifra profesora: " + profesor.getSifra());
                System.out.println("Ime profesora: " + profesor.getIme());
                System.out.println("prezime profesora: " + profesor.getPrezime());
                System.out.println("titula profesora: " + profesor.getTitula());
                System.out.println();
            }

            List<Predmet> predmetList = unosPredmeta(unos, profesorList);
            System.out.println("Unijeli ste sljedeće predmete:");
            for (Predmet predmet : predmetList) {
                System.out.println();
                System.out.println("Sifra predmeta: " + predmet.getSifra());
                System.out.println("Naziv predmeta: " + predmet.getNaziv());
                System.out.println("Broj ECTS bodova: " + predmet.getBrojEctsBodova());
                System.out.println("Nositelj predmeta: " + predmet.getNositelj().getIme() +
                        " " + predmet.getNositelj().getPrezime());
                System.out.println();
            }

            List<Student> studentList = unosStudenata(unos);
            System.out.println("Unijeli ste sljedeće studente:");
            for (Student student : studentList) {
                System.out.println();
                System.out.println("Ime studenta: " + student.getIme());
                System.out.println("Prezme studenta: " + student.getPrezime());
                System.out.println("Jmbag studenta: " + student.getJmbag());
                System.out.println("Datum rodenja studenta: " + student.getDatumRodjena().format(DateTimeFormatter.ofPattern("dd.MM.yyyy.")));
                System.out.println();
            }
            List<Ispit> ispitiList = unosIspita(unos, predmetList, studentList);
            System.out.println("Unijeli ste sljedeće ispitne rokove:");
            for (Ispit ispit : ispitiList) {
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
            Integer odabirUstanove=1;
            do {
                try {
                    System.out.print("Odaberite obrazovnu ustanovu za navedene podatke koju želite unijeti (1 -\n" +
                            "Veleučilište Jave, 2 - Fakultet računarstva):");
                    odabirUstanove = unos.nextInt();
                    if (odabirUstanove > 2 || odabirUstanove < 1)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            System.out.println("Unesite naziv obrazovne ustanove:");
            String nazivUstanove = unos.nextLine();
            if (odabirUstanove == 1) {
                obrazovneUstanove[aa] = new VeleucilisteJave(nazivUstanove, predmetList, profesorList, studentList, ispitiList);

            } else if (odabirUstanove == 2) {
                obrazovneUstanove[aa] = new FakultetRacunarstva(nazivUstanove, predmetList, profesorList, studentList, ispitiList);
            }



            int ocjenaZavrsnogRadaStudenta = 0;
            int ocjenaObraneZavrsnogRadaStudenta = 0;
            int godina = 2022;
            int studentiUpisaniDalje = 0;
        for (Student student : studentList) {

            do {
                try {
                    System.out.println("Unesite ocjenu završnog rada za studenta: " + student.getIme() +
                            " " + student.getPrezime() + ":");
                    ocjenaZavrsnogRadaStudenta = unos.nextInt();
                    if (ocjenaZavrsnogRadaStudenta < 1 || ocjenaZavrsnogRadaStudenta > 5)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            do {
                try {
                    System.out.println("Unesite ocjenu obrane završnog rada za studenta: " + student.getIme() +
                            " " + student.getPrezime() + ":");
                    ocjenaObraneZavrsnogRadaStudenta = unos.nextInt();
                    if (ocjenaObraneZavrsnogRadaStudenta < 1 || ocjenaObraneZavrsnogRadaStudenta > 5)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            System.out.println("Konačna ocjena studija studenta: " + student.getIme() +
                    " " + student.getPrezime() + ":");
            if (odabirUstanove == 1) {
                System.out.println(((VeleucilisteJave) obrazovneUstanove[aa]).izracunajKonacnuOcjenuStudijaZaStudenta(ispitiList, ocjenaZavrsnogRadaStudenta,
                        ocjenaObraneZavrsnogRadaStudenta,student));
            }
            if (odabirUstanove == 2) {
                System.out.println(((FakultetRacunarstva) obrazovneUstanove[aa]).izracunajKonacnuOcjenuStudijaZaStudenta(ispitiList, ocjenaZavrsnogRadaStudenta,
                        ocjenaObraneZavrsnogRadaStudenta,student));
            }


                if (studentiUpisaniDalje == 1)
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

    /**
     * Učitava novi ispit preko danog scannera te danih predmeta i njihovih studenata.
     * @param unos Scanner s kojim se čitaju podatci.
     * @param predmetList Popis predmeta koji se polažu.
     * @param studentList Popis studenata.
     * @return Novi objekt s podacima učitanih koristeći dani scanner.
     */
    private static List<Ispit> unosIspita(Scanner unos, List<Predmet> predmetList, List<Student> studentList) {
        List<Ispit> ispitList = new ArrayList<>();
        boolean valid;
        for (int i = 0; i < BROJ_ISPITA; i++) {
            System.out.println("Unesite " + (i + 1) + ". ispitni rok: ");
            System.out.println("Odaberite predmet: ");

            Integer izborPredmeta = 0;
            do {
                try {
                    for (int j = 0; j < BROJ_PREDMETA; j++) {
                        System.out.println((j + 1) + ". " + predmetList.get(j).getNaziv());
                    }
                    izborPredmeta = unos.nextInt();
                    if (izborPredmeta < 0 || izborPredmeta > BROJ_PREDMETA)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            Predmet predmet = predmetList.get(izborPredmeta - 1);

            System.out.print("Unesite dvoranu:");
            String nazivDvorane = unos.nextLine();
            System.out.print("Unesite zgradu:");
            String nazivZgrade = unos.nextLine();
            Dvorana dvorana = new Dvorana(nazivDvorane, nazivZgrade);

            System.out.println("Odaberite studenta: ");
            for (int k = 0; k < BROJ_STUDENATA; k++) {
                System.out.println((k + 1) + ". " + studentList.get(k).getIme() + " " + studentList.get(k).getPrezime());
            }
            Integer izborStudenta = unos.nextInt();
            unos.nextLine();
            Student student = studentList.get(izborStudenta - 1);

            Integer ocjena= 0;
            do {
                try {
                    System.out.print("Unesite ocjenu na ispitu (1-5): ");
                    ocjena = unos.nextInt();
                    if (ocjena < 1 || ocjena > 5)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            System.out.print("Unesite datum i vrijeme ispita u formatu (dd.MM.yyyy.THH:mm):");
            String datumString = unos.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
            LocalDateTime datumIVrijeme = LocalDateTime.parse(datumString, formatter);

            ispitList.add(new Ispit(predmet, student, ocjena, datumIVrijeme, dvorana));

        }
        return ispitList;
    }

    /**
     * Učitava novog studenta preko danog scannera.
     * @param unos Scanner s kojim se čitaju podatci.
     * @return Novi objekt s podacima učitanih koristeći dani scanner.
     */
    private static List<Student> unosStudenata(Scanner unos) {
        List<Student> studentList = new ArrayList<>();
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

            studentList.add(new Student(ime, prezime, jmbag, datumRodjenja));
        }
        return studentList;
    }

    /**
     * Učitava novi predmet preko danog scannera te danih profesora i studenata.
     *
     * @param unos         Scanner s kojim se čitaju podatci.
     * @param profesorList Popis dostupnih profesora za nositelja predmeta.
     * @return Novi objekt s podacima učitanih koristeći dani scanner.
     */
    private static List<Predmet> unosPredmeta(Scanner unos, List<Profesor> profesorList) {
        boolean valid;
        List<Predmet> predmetList = new ArrayList<>();
        for (int i = 0; i < BROJ_PREDMETA; i++) {
            System.out.println("Unesite " + (i + 1) + ". predmet: ");
            System.out.print("Unesite sifru predmeta: ");
            String sifra = unos.nextLine();
            System.out.print("Unesite naziv predmeta: ");
            String naziv = unos.nextLine();
            Integer brojEctsBodova = 0;
            do {
                try {
                    System.out.print("Unesite broj ECTS bodova za predmet '" + naziv + "':");

                    brojEctsBodova = unos.nextInt();
                    if (brojEctsBodova < 1 || brojEctsBodova > 12)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);

            Integer brojNositelja = 0;
            do {
                try {
                    System.out.println("Odaberite profesora:");
                    for (int j = 0; j < BROJ_PROFESORA; j++) {
                        System.out.println((j + 1) + ". " + profesorList.get(j).getIme() + " " + profesorList.get(j).getPrezime());
                    }
                    brojNositelja = unos.nextInt();
                    if (brojNositelja < 1)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            Profesor nositelj = profesorList.get(brojNositelja - 1);
            Integer brojStudenataUPredmetu = 0;
            do {
                try {

                    System.out.print("Unesite broj studenata za predmet '" + naziv + "':");
                    brojStudenataUPredmetu = unos.nextInt();
                    if (brojStudenataUPredmetu < 0)
                    {
                        throw new InputMismatchException("Neispravan unos!");
                    }
                    valid = true;
                }
                catch (InputMismatchException e)
                {
                    System.out.println("Neispravan unos!");
                    logger.error("Krivi unos", e);
                    valid = false;
                }
                finally {
                    unos.nextLine();
                }
            }
            while (!valid);
            List<Student> studenti = new ArrayList<>();
            studenti.sort(new StudentSorter());
            predmetList.add(new Predmet(sifra, naziv, brojEctsBodova, nositelj,studenti));
        }
        return predmetList;
    }

    /**
     * Učitava novog profesora preko danog scannera.
     * @param unos Scanner s kojim se čitaju podatci.
     * @return Novi objekt s podacima učitanih koristeći dani scanner.
     */
    private static List<Profesor> unosProfesora(Scanner unos) {
        List<Profesor> profesoriList = new ArrayList<>();

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

            profesoriList.add(new Profesor.ProfesorBuilder().setSifra(sifra).setIme(ime).setPrezime(prezime).setTitula(titula).createProfesor());
        }
        return profesoriList;
    }


}
