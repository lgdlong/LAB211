package model;

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

    public String getIngredient() {
        String[] paragraph = this.ingredient.split("#");
        StringBuilder strb = new StringBuilder();
        for (String s : paragraph) {
            strb.append(s.trim())
                .append("\n");
        }
        return strb.toString().trim();
    }

    @Override
    public String toString() {
        return String.format("""
                             -----------------------------------------
                             Code       : %s
                             Name       : %s
                             Price      : %s
                             Ingredients: 
                             %s
                             """,
                
                code, name, price, getIngredient());
    }
}
