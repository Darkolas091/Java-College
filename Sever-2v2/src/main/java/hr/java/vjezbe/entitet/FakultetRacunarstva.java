package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski{

    public FakultetRacunarstva(String nazivUstanove, Predmet[] predmeti, Profesor[] profesori, Student[] studenti, Ispit[] ispiti) {
        super(nazivUstanove, predmeti, profesori, studenti, ispiti);
    }

    @Override
    public Student odrediStudentaZaRektorovuNagradu() {
        Ispit[] ispiti = getIspiti();
        Student[] studenti = getStudenti();
        int najboljiStudentIndex = 0;
        Double najboljiProsjek = 0.00;
        int ukupno = 0;
        Double prosjek = 0.00;

        for (int i = 0; i < studenti.length; i++)
        {
            ukupno = 0;
            for (int j = 0; j < ispiti.length; j++)
            {
                if (ispiti[j].getStudent().getJmbag() == studenti[i].getJmbag())
                {
                    ukupno = ispiti[j].getOcjena() + ukupno;
                }
            }
            prosjek = Double.valueOf(ukupno / ispiti.length);
            if (prosjek >= najboljiProsjek)
            {
                boolean dalje = true;
                if (prosjek == najboljiProsjek && studenti[i].getDatumRodjena().isAfter(studenti[najboljiStudentIndex].getDatumRodjena()))
                {
                    najboljiProsjek = prosjek;
                    najboljiStudentIndex = i;
                    dalje = false;
                }
                if (dalje)
                {
                    najboljiProsjek = prosjek;
                    najboljiStudentIndex = i;
                }
            }
        }

        return studenti[najboljiStudentIndex];
    }

    @Override
    public Student odrediNajuspjesnijegStudentaNaGodini(Integer godina) {
        Ispit[] ispiti = getIspiti();
        Student[] studenti = getStudenti();
        Student[] odlicniStudenti = new Student[studenti.length];

        for (int k = 0, i = 0; i < ispiti.length; i++)
        {
            for (int j = 0; j < studenti.length;j++)
            {
                if (ispiti[i].getOcjena().equals(5))
                {
                    odlicniStudenti[k] = ispiti[i].getStudent();
                    k++;
                }
            }
        }
        Integer maxOdlicnihOcjena = 0;
        for (int i = 0; i < odlicniStudenti.length; i++)
        {
            int currentBrojOdlicnihOcijena=0;
            for (int j = 0; j < ispiti.length;j++)
            {
                if (odlicniStudenti[i].getJmbag().equals(ispiti[j].getStudent().getJmbag()))
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
        Student[] studentSaNajvecimBrojemOdlicnih = new Student[studenti.length];
        Student studentDaBest = studenti[0];


        for (int i = 0; i < odlicniStudenti.length;i++)
        {
            int currentBrojOdlicnihOcijena=0;
            for (int j = 0; j < ispiti.length;j++) {
                if (odlicniStudenti[i].getJmbag().equals(ispiti[j].getStudent().getJmbag()))
                {
                    currentBrojOdlicnihOcijena++;
                }
                if (currentBrojOdlicnihOcijena==maxOdlicnihOcjena)
                {
                    studentDaBest = odlicniStudenti[i];
                }
            }
        }



        return studentDaBest;
    }

    @Override
    public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(Ispit[] ispiti, Integer ocjenaDiplomskogRada, Integer ocjenaObraneDiplomskogRada,Student student) {
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

        int ukopnoDiplomskiOcjena = ocjenaObraneDiplomskogRada + ocjenaDiplomskogRada;
        return BigDecimal.valueOf((3 * prosjek + ukopnoDiplomskiOcjena)/5).setScale(0, RoundingMode.HALF_UP);
    }
}
