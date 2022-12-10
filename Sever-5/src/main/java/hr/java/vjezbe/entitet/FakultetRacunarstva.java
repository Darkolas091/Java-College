package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski{

    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);


    public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti, List<Ispit> ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        List<Ispit> ispiti = getIspiti();
        List<Student> studenti = getStudenti();
        int najboljiStudentIndex = 0;
        Double najboljiProsjek = 0.00;
        int ukupno = 0;
        Double prosjek = 0.00;

        for (int i = 0; i < studenti.size(); i++)
        {
            ukupno = 0;
            for (int j = 0; j < ispiti.size(); j++)
            {
                if (ispiti.get(j).getStudent().getJmbag() == studenti.get(i).getJmbag())
                {
                    ukupno = ispiti.get(j).getOcjena() + ukupno;
                }
            }
            prosjek = Double.valueOf(ukupno / ispiti.size());
            if (prosjek >= najboljiProsjek)
            {
                boolean dalje = true;
                if (prosjek == najboljiProsjek && studenti.get(i).getDatumRodjena().isAfter(studenti.get(najboljiStudentIndex).getDatumRodjena()))
                {
                    najboljiProsjek = prosjek;
                    najboljiStudentIndex = i;
                    dalje = false;

                    String najmladjiStudenti = String.format("%s %s, %s %s",
                            studenti.get(i).getIme(),
                            studenti.get(i).getPrezime(),
                            studenti.get(i-1).getIme(),
                            studenti.get(i-1).getPrezime());

                    System.out.println("Pronađeno je više najmlađih studenata: " + najmladjiStudenti);
                    logger.error("Pronađeno je više najmlađih studenata: " + najmladjiStudenti);

                    throw new PostojiViseNajmladjihStudenataException(najmladjiStudenti);
                }
                if (dalje)
                {
                    najboljiProsjek = prosjek;
                    najboljiStudentIndex = i;
                }
            }
        }

        return studenti.get(najboljiStudentIndex);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        List<Ispit> ispitList = getIspiti();
        List<Student> studentList = getStudenti();
        List<Student> odlicniStudenti = new ArrayList<>();

        for (int k = 0, i = 0; i < ispitList.size(); i++)
        {
            for (int j = 0; j < studentList.size();j++)
            {
                if (ispitList.get(i).getOcjena().equals(5))
                {
                    odlicniStudenti.add(ispitList.get(i).getStudent());
                    k++;
                }
            }
        }
        Integer maxOdlicnihOcjena = 0;
        for (int i = 0; i < odlicniStudenti.size(); i++)
        {
            int currentBrojOdlicnihOcijena=0;
            for (int j = 0; j < ispitList.size();j++)
            {
                if (odlicniStudenti.get(i).getJmbag().equals(ispitList.get(j).getStudent().getJmbag()))
                {
                    currentBrojOdlicnihOcijena++;
                }
                if (currentBrojOdlicnihOcijena>maxOdlicnihOcjena)
                {
                    maxOdlicnihOcjena=currentBrojOdlicnihOcijena;
                }
            }
        }

        int counterZaZadnjeg = 0;
        Student[] studentSaNajvecimBrojemOdlicnih = new Student[studentList.size()];
        Student studentDaBest = studentList.get(0);


        for (int i = 0; i < odlicniStudenti.size();i++)
        {
            int currentBrojOdlicnihOcijena=0;
            for (int j = 0; j < ispitList.size();j++) {
                if (odlicniStudenti.get(i).getJmbag().equals(ispitList.get(j).getStudent().getJmbag()))
                {
                    currentBrojOdlicnihOcijena++;
                }
                if (currentBrojOdlicnihOcijena==maxOdlicnihOcjena)
                {
                    studentDaBest = odlicniStudenti.get(i);
                }
            }
        }



        return studentDaBest;
    }
    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaDiplomskogRada, Integer ocjenaObraneDiplomskogRada,Student student) {
        int counter = 0;
        int sum = 0;
        for (int i = 0; i < ispiti.size(); i++)
        {
            try {
                if (ispiti.get(i).getStudent() == student && ispiti.get(i).getOcjena() > 1)
                {
                    sum += ispiti.get(i).getOcjena();
                    ++counter;



                } else if (ispiti.get(i).getStudent() == student && ispiti.get(i).getOcjena() == 1)
                {
                    throw new NemoguceOdreditiProsjekStudentaException(String.format
                            ("\"Student %s %s je ocjenjen negativom ocjenom iz predmeta %s (%s)!\"",
                                    ispiti.get(i).getStudent().getIme(),
                                    ispiti.get(i).getStudent().getPrezime(),
                                    ispiti.get(i).getPredmet().getNaziv(),
                                    ispiti.get(i).getPredmet().getSifra()));
                }
            } catch (NemoguceOdreditiProsjekStudentaException e) {

                logger.warn(String.format("Student %s %s zbog negativne ocjene na jednom od ispita ima prosjek 'nedovoljan (1)'!", student.getIme(), student.getPrezime()), e);

                return BigDecimal.ONE;
            }
        }
        float prosjek = (float)sum/counter;
        int ukopnoDiplomskiOcjena = ocjenaObraneDiplomskogRada + ocjenaDiplomskogRada;
        return BigDecimal.valueOf((3 * prosjek + ukopnoDiplomskiOcjena)/5).setScale(0, RoundingMode.HALF_UP);
    }



    /**
     * @param ispiti Ispiti iz kojih se uzimaju ocjene. Očekuje se filtriran popis ispita za jednog studenta.
     * @return
     * @throws NemoguceOdreditiProsjekStudentaException
     */
    @Override
    public BigDecimal odrediProsjekOcjnaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {
        return Diplomski.super.odrediProsjekOcjnaNaIspitima(ispiti);
    }

    /**
     * @param ispiti  Svi ispiti.
     * @param student Student prema kojem se filtriraju ispiti.
     * @return
     */
    @Override
    public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {
        return Diplomski.super.filtrirajIspitePoStudentu(ispiti, student);
    }
}
