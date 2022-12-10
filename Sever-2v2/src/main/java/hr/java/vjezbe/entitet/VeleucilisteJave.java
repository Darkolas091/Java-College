package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska
{

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

                Ispit[] filtrirani = filtrirajIspitePoStudentu(ispiti,studenti[j]);
                if (filtrirani[i].getOcjena()>najboljaOcjenaATM)
                {
                    najboljaOcjenaATM = filtrirani[i].getOcjena();
                    daBest = studenti[j++];
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
        for (int i = 0; i < ispiti.length; i++)
        {
            if (ispiti[i].getStudent() == student)
            {
                sum += ispiti[i].getOcjena();
                ++counter;
            }
        }
        float prosjek = (float)sum/counter;

        int ukopnoDiplomskiOcjena = ocjenaPismenogZavrsnog + ocjenaObraneZavrsnogRada;
        return BigDecimal.valueOf((2 * prosjek + ukopnoDiplomskiOcjena)/4).setScale(0, RoundingMode.HALF_UP);
    }


    @Override
    public BigDecimal odrediProsjekOcjnaNaIspitima(Ispit[] ispiti) {
        return Visokoskolska.super.odrediProsjekOcjnaNaIspitima(ispiti);
    }

    @Override
    public Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti, Student student) {
        return Visokoskolska.super.filtrirajIspitePoStudentu(ispiti, student);
    }
}
