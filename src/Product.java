import org.json.JSONObject;

import java.io.IOException;

public class Product {
    private String barcode;
    private String name;
    private String size;
    private double Kcal;
    private int quantity;

    public Product(String barcode, String name, String size, double Kcal, int quantity) {
        this.barcode = barcode;
        this.name = name;
        this.size = size;
        this.Kcal = Kcal * 0.239;
        this.quantity = quantity;
    }

    public Product(String barcode) {
        JSONObject product = getProduct(barcode);
        String store = String.valueOf(product.getJSONArray("items").getJSONObject(0).getJSONArray("nutrients").getJSONObject(0).get("per_100g"));

        this.barcode = barcode;
        this.name = product.getJSONArray("items").getJSONObject(0).getString("name");
        this.size = String.valueOf(product.getJSONArray("items").getJSONObject(0).getJSONObject("package").get("quantity"));
        this.Kcal = (store != null)?Double.parseDouble(store)* 0.239:-1;
        this.quantity = 1;
    }


    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getKcal() {
        return Kcal;
    }

    public void setKcal(double kcal) {
        this.Kcal = kcal;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void addQuantity(int quantity){
        this.quantity += quantity;
    }

    public void removeQuantity(int quantity){
        int quant = this.quantity -= quantity;

        if (quant < 0) {
            this.quantity += quantity;
            System.out.println("Too much to remove you just have: " + this.quantity);
        }
    }

    private JSONObject getProduct(String barcode){
        JSONObject product;
        String url = "https://chompthis.com/api/v2/food/branded/barcode.php?api_key=16BV8nT6MOdvA3hkS&code=";
        try {
            product = JsonTreatment.readJsonFromUrl(url + barcode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    @Override
    public String toString() {
        return "Product{" +
                "barcode='" + barcode + '\'' +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", Kcal='" + Kcal + '\'' +
                ", quantity=" + quantity +
                "}\n";
    }
}
