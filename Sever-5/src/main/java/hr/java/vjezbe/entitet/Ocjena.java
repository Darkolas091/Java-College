package hr.java.vjezbe.entitet;

import java.math.BigDecimal;

/**
 * Enumeracija svih dozvoljenih vrijednosti ocjena.
 */
public enum Ocjena {
    NEDOVOLJAN(1, "nedovoljan"),
    DOVOLJAN(2, "dovoljan"),
    DOBAR(3, "dobar"),
    VRLO_DOBAR(4, "vrlo dobar"),
    IZVRSTAN(5, "izvrstan");

    private final int numerickaVrijednost;
    private final String opis;

    Ocjena(int numerickaVrijednost, String opis) {
        this.numerickaVrijednost = numerickaVrijednost;
        this.opis = opis;
    }

    public int getNumerickaVrijednost() {
        return numerickaVrijednost;
    }

    public String getOpis() {
        return opis;
    }

    /**
     * Pretvara ocjenu u BigDecimal
     * @return numericka vrijednost kao BigDecimal
     */
    public BigDecimal toBigDecimal() {
        return new BigDecimal(numerickaVrijednost);
    }

}
