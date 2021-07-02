package be.vdab.bierhuis.domain;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

public class Adres {
    @NotBlank
    private final String straat;
    @NotBlank
    private final String huisNr;
    @Valid
    private final Gemeente gemeente;
    public Adres(String straat, String huisNr,Gemeente gemeente) {
        this.straat = straat;
        this.huisNr = huisNr;
        this.gemeente = gemeente;
    }
    public String getStraat() {
        return straat;
    }
    public String getHuisNr() {
        return huisNr;
    }
    public Gemeente getGemeente() {
        return gemeente;
    }
}
