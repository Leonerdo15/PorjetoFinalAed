import java.util.Objects;

public class Home {
    public static void main(String[] args) {

        Pantry pantry =  new Pantry();
        String barcode = "5449000000996";
        Product product = new Product(barcode);
        pantry.addProduct(product);
        product.setKcal(123);
        pantry.addProduct("8992760221028");
        pantry.addProduct("5053990101580");
        System.out.println(pantry);
        pantry.saveListAsCsv("test.csv");

        Pantry fridge = new Pantry("test.csv");
        System.out.println(fridge);
        fridge.addProduct("5603722505775");
        fridge.saveListAsCsv("test1.csv");
        System.out.println(fridge);

    }
}
