package dhbw.karlsruhe.it.solar.core.physics;

public class LifeRating {
    
    private float rating;

    public LifeRating(float rating) {
        this.rating = rating;
    }

    public String inPercent() {
        return formatValue() + " %";
    }
    
    private String formatValue() {
        return String.format("%.00f", rating*100);
    }
}
