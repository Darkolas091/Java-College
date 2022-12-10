package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

import java.math.BigDecimal;
import java.util.List;

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
    abstract BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti,Integer ocjenaPismenogZavrsnog,Integer ocjenaObraneZavrsnogRada,Student student);


    /**
     * Računa prosječnu ocjenu iz danih ispita. Nije dozvoljena negativna ocjena.
     * @param ispiti Ispiti iz kojih se uzimaju ocjene. Očekuje se filtriran popis ispita za jednog studenta.
     * @return Prosječna ocjena studenta na ispitima.
     * @throws NemoguceOdreditiProsjekStudentaException Ako se pronađe ispit s negativnom ocjenom.
     */
    default BigDecimal odrediProsjekOcjnaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException {
        int counter = 0;
        BigDecimal prosjek = new BigDecimal(0);


        for (int i = 0; i < ispiti.size(); i++) {
            if (ispiti.get(i).getOcjena() > 1 )
            {
                prosjek = prosjek.add(new BigDecimal(ispiti.get(i).getOcjena()));
                counter++;
            }
            else
            {
                throw new NemoguceOdreditiProsjekStudentaException(String.format
                        ("\"Student %s %s je ocjenjen negativom ocjenom iz predmeta %s (%s)!\"",
                        ispiti.get(i).getStudent().getIme(),
                        ispiti.get(i).getStudent().getPrezime(),
                        ispiti.get(i).getPredmet().getNaziv(),
                        ispiti.get(i).getPredmet().getSifra()));

            }
        }
        prosjek = prosjek.divide(new BigDecimal(counter));
        return prosjek;
    }


    private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti)
    {
        int i;
        int j = 0;
        List<Ispit> polozeniIspiti = null;
        for (i = 0; i < ispiti.size() ; i++)
        {
            if (ispiti.get(i).getOcjena() > 1)
            {
                polozeniIspiti.add(ispiti.get(i));
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
    default Ispit[] filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student)//
    {
        int i;
        int j = 0;
        Ispit[] ispitiPoStudentu = new Ispit[ispiti.size()];
        for ( i = 0; i < ispiti.size(); i++)
        {
            if (ispiti.get(i).getStudent() == student)
            {
                ispitiPoStudentu[j] = ispiti.get(i);
                j++;
            }
        }
        return ispitiPoStudentu;
    }



}
