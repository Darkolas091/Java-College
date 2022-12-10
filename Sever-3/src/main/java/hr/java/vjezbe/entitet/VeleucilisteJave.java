package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Visokoškolska obrazovna ustanova za Javu.
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska
{
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);


    public VeleucilisteJave(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        int indexNajboljiStudent = 0;

        Ispit[] ispiti = getIspiti();
        Student[] studenti = getStudenti();
        Student daBest = studenti[0];
        int najboljaOcjenaATM = 0;


        for (int i = 0; i < ispiti.length; i++)
        {
            for (int j = 0; j < studenti.length; j++)
            {
                if (ispiti[i].getStudent() == studenti[i])
                {
                    //Ispit[] filtrirani = filtrirajIspitePoStudentu(ispiti,studenti[j]);
                    if (ispiti[i].getOcjena()>najboljaOcjenaATM)
                    {
                        najboljaOcjenaATM = ispiti[i].getOcjena();
                        daBest = studenti[j++];
                    }
                }


            }
        }

        return daBest;
    }


    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjenaPismenogZavrsnog, Integer ocjenaObraneZavrsnogRada, Student student)
    {
        int counter = 0;
        int sum = 0;
        float prosjek;
        int ukopnoDiplomskiOcjena;


        try
        {
            for (int i = 0; i < ispiti.length; i++)
            {
                if (ispiti[i].getStudent() == student && ispiti[i].getOcjena() > 1)
                {
                    sum += ispiti[i].getOcjena();
                    ++counter;



                } else if (ispiti[i].getStudent() == student && ispiti[i].getOcjena() == 1)
                {
                    throw new NemoguceOdreditiProsjekStudentaException(String.format
                            ("\"Student %s %s je ocjenjen negativom ocjenom iz predmeta %s (%s)!\"",
                                    ispiti[i].getStudent().getIme(),
                                    ispiti[i].getStudent().getPrezime(),
                                    ispiti[i].getPredmet().getNaziv(),
                                    ispiti[i].getPredmet().getSifra()));
                }

            }
            prosjek = (float)sum/counter;

            ukopnoDiplomskiOcjena = ocjenaPismenogZavrsnog + ocjenaObraneZavrsnogRada;

        } catch (NemoguceOdreditiProsjekStudentaException e) {

            logger.warn(String.format("Student %s %s zbog negativne ocjene na jednom od ispita ima prosjek 'nedovoljan (1)'!", student.getIme(), student.getPrezime()), e);

            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf((2 * prosjek + ukopnoDiplomskiOcjena)/4).setScale(0, RoundingMode.HALF_UP);

    }


    @Override
    public BigDecimal odrediProsjekOcjnaNaIspitima(Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException {
        return Visokoskolska.super.odrediProsjekOcjnaNaIspitima(ispiti);
    }

    @Override
    public Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        return Visokoskolska.super.filtrirajIspitePoStudentu(ispiti, student);
    }
}
