package Model;

/**
 *
 * @author LGD
 */
public class Feast {
    private String code;
    private String name;
    private double price;
    private String ingredient;

    public Feast(String code, String name, double price, String ingredient) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.ingredient = ingredient;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public String toString() {
        return "Feast{" + "code=" + code + ", name=" + name + ", price=" + price + ", ingredient=" + ingredient + '}';
    }
}
