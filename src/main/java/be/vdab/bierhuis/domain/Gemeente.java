package be.vdab.bierhuis.domain;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Gemeente {
    @Min(1000)
    @Max(9999)
    private final int postcode;
    @NotBlank
    private final String gemeente;
    public Gemeente(int postcode, String gemeente) {
        this.postcode = postcode;
        this.gemeente = gemeente;
    }
    public int getPostcode() {
        return postcode;
    }
    public String getGemeente() {
        return gemeente;
    }
}
