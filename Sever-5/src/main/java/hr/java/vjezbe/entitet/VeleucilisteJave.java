package hr.java.vjezbe.entitet;

import hr.java.vjezbe.glavna.Glavna;
import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Visokoškolska obrazovna ustanova za Javu.
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska
{
    private static final Logger logger = LoggerFactory.getLogger(Glavna.class);


    public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,List<Ispit> ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        int indexNajboljiStudent = 0;

        List<Ispit> ispitiList = getIspiti();
        List<Student> studentiList = getStudenti();
        Student daBest = studentiList.get(0);
        int najboljaOcjenaATM = 0;


        for (int i = 0; i < ispitiList.size(); i++)
        {
            for (int j = 0; j < studentiList.size(); j++)
            {
                if (ispitiList.get(i).getStudent() == studentiList.get(i))
                {
                    //Ispit[] filtrirani = filtrirajIspitePoStudentu(ispitiList,studentiList[j]);
                    if (ispitiList.get(i).getOcjena()>najboljaOcjenaATM)
                    {
                        najboljaOcjenaATM = ispitiList.get(i).getOcjena();
                        daBest = studentiList.get(j++);
                    }
                }


            }
        }

        return daBest;
    }


    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, Integer ocjenaPismenogZavrsnog, Integer ocjenaObraneZavrsnogRada, Student student)
    {
        int counter = 0;
        int sum = 0;
        float prosjek;
        int ukopnoDiplomskiOcjena;


        try
        {
            for (int i = 0; i < ispiti.size(); i++)
            {
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

            }
            prosjek = (float)sum/counter;

            ukopnoDiplomskiOcjena = ocjenaPismenogZavrsnog + ocjenaObraneZavrsnogRada;

        } catch (NemoguceOdreditiProsjekStudentaException e) {

            logger.warn(String.format("Student %s %s zbog negativne ocjene na jednom od ispita ima prosjek 'nedovoljan (1)'!", student.getIme(), student.getPrezime()), e);

            return BigDecimal.ONE;
        }
        return BigDecimal.valueOf((2 * prosjek + ukopnoDiplomskiOcjena)/4).setScale(0, RoundingMode.HALF_UP);

    }



}
