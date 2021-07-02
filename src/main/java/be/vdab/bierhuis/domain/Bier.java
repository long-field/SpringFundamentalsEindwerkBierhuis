package be.vdab.bierhuis.domain;
import org.springframework.format.annotation.NumberFormat;
import javax.validation.constraints.*;
import java.math.BigDecimal;

public class Bier {
    private final long id;
    @NotBlank
    private final String naam;
    private final long brouwerid;
    @NotNull
    @PositiveOrZero
    @Digits(integer=2, fraction=2)
    private final BigDecimal alcohol;
    @NotNull
    @PositiveOrZero
    @NumberFormat(pattern = "0.00")
    private final BigDecimal prijs;
    @PositiveOrZero
    private final long besteld;
    public Bier(long id, String naam, long brouwerid, BigDecimal alcohol, BigDecimal prijs, long besteld) {
        this.id = id;
        this.naam = naam;
        this.brouwerid = brouwerid;
        this.alcohol = alcohol;
        this.prijs = prijs;
        this.besteld = besteld;
    }
    public long getId() {
        return id;
    }
    public String getNaam() {
        return naam;
    }
    public long getBrouwerid() {
        return brouwerid;
    }
    public BigDecimal getAlcohol() {
        return alcohol;
    }
    public BigDecimal getPrijs() {
        return prijs;
    }
    public long getBesteld() {
        return besteld;
    }
}
