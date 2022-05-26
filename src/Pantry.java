import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class Pantry {
    private List<Product> products;

    public Pantry(List<Product> products) {
        this.products = new ArrayList<>();
    }

    public Pantry(String path){
        products = new ArrayList<>();
        receiveCsv(path);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Pantry() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product){
        products.add(product);
    }

    public void addProduct(String saveBarcode){
        Product product = new Product(saveBarcode);
        products.add(product);
    }

    public void removeProduct(String barcode){
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getBarcode().equals(barcode)) {
                products.remove(products.get(i));
                System.out.println("Product deleted");
            }
        }
    }

    public List<Product> searchByKcal(String signal, double calories){
        List<Product> store = new ArrayList<>();

        for (Product product : products) {
            if (signal.equals("+")) {
                if (product.getKcal() > calories) {
                    store.add(product);
                }
            } else if (signal.equals("-")) {
                if (product.getKcal() < calories) {
                    store.add(product);
                }
            } else {
                System.out.println("Signal invalid, try ´+´ or ´-´");
            }
        }
        return store;
    }

    public void saveListAsCsv(String path){
        try {
            File file = new File(path);
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Product product : products) {
                bw.write(product.getBarcode() + ";" + product.getName() + ";" + product.getSize() + ";" + product.getKcal() + ";" + product.getQuantity());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void receiveCsv(String path){
        String line = "";
        String splitBy = ";";
        try {
            BufferedReader br = new BufferedReader(new FileReader("test.csv"));
            while ((line = br.readLine()) != null) {
                String[] product = line.split(splitBy);
                System.out.println(Arrays.toString(product));
                products.add(new Product(product[0], product[1], product[2], Double.parseDouble(product[3])*4.184,Integer.parseInt(product[4])));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Pantry{" +
                "\nproducts=" + products +
                '}';
    }
}
