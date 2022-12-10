package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;

/**
 * Interface za smjerove na visokoškolskoj razini.
 */
public interface Visokoskolska {

    /**
     * Računa konačnu ocjenu iz danih ispita. Nije dozvoljena negativna ocjena.
     * @param ispiti Ispiti iz kojih se uzimaju ocjene. Očekuje se filtriran popis ispita za jednog studenta.
     * @param ocjenaPismenogZavrsnog Ocjena dobivena za završni rad.
     * @param ocjenaObraneZavrsnogRada Ocjena dobivena za obranu završnog rada.
     * @param student Popis Studenata.
     * @return Konačna ocjena studenta.
     */
    abstract BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti,Integer ocjenaPismenogZavrsnog,Integer ocjenaObraneZavrsnogRada,Student student);


    /**
     * Računa prosječnu ocjenu iz danih ispita. Nije dozvoljena negativna ocjena.
     * @param ispiti Ispiti iz kojih se uzimaju ocjene. Očekuje se filtriran popis ispita za jednog studenta.
     * @return Prosječna ocjena studenta na ispitima.
     * @throws NemoguceOdreditiProsjekStudentaException Ako se pronađe ispit s negativnom ocjenom.
     */
    default BigDecimal odrediProsjekOcjnaNaIspitima(Ispit[] ispiti) throws NemoguceOdreditiProsjekStudentaException {
        int counter = 0;
        BigDecimal prosjek = new BigDecimal(0);


        for (int i = 0; i < ispiti.length; i++) {
            if (ispiti[i].getOcjena() > 1 )
            {
                prosjek = prosjek.add(new BigDecimal(ispiti[i].getOcjena()));
                counter++;
            }
            else
            {
                throw new NemoguceOdreditiProsjekStudentaException(String.format
                        ("\"Student %s %s je ocjenjen negativom ocjenom iz predmeta %s (%s)!\"",
                        ispiti[i].getStudent().getIme(),
                        ispiti[i].getStudent().getPrezime(),
                        ispiti[i].getPredmet().getNaziv(),
                        ispiti[i].getPredmet().getSifra()));

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

    /**
     * Vraća sve ispite koje je pisao određen student.
     * @param ispiti Svi ispiti.
     * @param student Student prema kojem se filtriraju ispiti.
     * @return Filtrirani ispiti.
     */
    default Ispit[] filtrirajIspitePoStudentu(Ispit[] ispiti,Student student)//
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
