package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

public interface Visokoskolska {

    abstract BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti,Integer ocjenaPismenogZavrsnog,Integer ocjenaObraneZavrsnogRada,Student student);


    default BigDecimal odrediProsjekOcjnaNaIspitima(Ispit[] ispiti)
    {
        int counter = 0;
        BigDecimal prosjek = new BigDecimal(0);
        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getOcjena() > 1 )
            {
                prosjek = prosjek.add(new BigDecimal(ispiti[i].getOcjena()));
                counter++;
            }
        }
        prosjek = prosjek.divide(new BigDecimal(counter));
        return prosjek;
    }


    private Ispit[] filtrirajPolozeneIspite(Ispit[] ispiti)
    {
        int i;
        int j = 0;
        Ispit[] polozeniIspiti = new Ispit[ispiti.length];
        for (i = 0; i < ispiti.length ; i++)
        {
            if (ispiti[i].getOcjena() > 1)
            {
                polozeniIspiti[j] = ispiti[i];
                j++;
            }
        }
        return polozeniIspiti;
    }

    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti,Student student)//TODO
    {
        int i;
        int j = 0;
        Ispit[] ispitiPoStudentu = new Ispit[ispiti.length];
        for ( i = 0; i < ispiti.length; i++)
        {
            if (ispiti[i].getStudent() == student)
            {
                ispitiPoStudentu[j] = ispiti[i];
                j++;
            }
        }
        return ispitiPoStudentu;
    }



}
