package model;

import data.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Product {
    private String time;
    private double capacity;
    private String resolution;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public CompactDisc(String ID, String name, String author, String yearOfPublication, String category, float price, String time, double capacity, String resolution) {
        super(ID, name, author, yearOfPublication, category, price);
        this.time = time;
        this.capacity = capacity;
        this.resolution = resolution;
    }
    public CompactDisc() {

    }
    public CompactDisc(String name, String ID) {
        super(name, ID);
    }

    @Override
    public String toString() {
        return this.getId() + ";" + this.getName() + ";" + this.getAuthor() + ";" + this.getYearOfPublication() + ";" + this.getCategory() + ";" + this.getPrice()
                + ";" + this.getTime() + ";" + this.getCapacity() + ";" + this.getResolution();
    }

    @Override
    public void addProduct(Product product) {

        System.out.println("Category" + product.getCategory() + " " + ((CompactDisc) product).getCapacity());
        String dir = System.getProperty("user.dir");
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\CompactDiscData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf((CompactDisc) product));
            bw.newLine();
            bw.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editProduct(Product product) {
        String dir = System.getProperty("user.dir");
        List<CompactDisc> l = (new Data().getDataCD());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\CompactDiscData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (CompactDisc o: l) {
                if(o.getId().equalsIgnoreCase(product.getId())) {
                    bw.write(String.valueOf((CompactDisc) product));
                    bw.newLine();
                }
                else {
                    bw.write(String.valueOf((CompactDisc) o));
                    bw.newLine();
                }

            }
            bw.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(Product product) {
        String dir = System.getProperty("user.dir");
        List<CompactDisc> l = (new Data().getDataCD());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\CompactDiscData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (CompactDisc o: l) {
                if(o.getId().equalsIgnoreCase(product.getId())) {
                    continue;
                }
                bw.write(String.valueOf((CompactDisc) o));
                bw.newLine();
            }
            bw.close();
            fw.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> searchProduct(String key, int criterion) {

        List<Product> list = new ArrayList<Product>();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fr = new FileReader(dir + "\\src\\data\\CompactDiscData.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while (true) {
                line = br.readLine();
                if(line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String ID = txt[0];
                String name = txt[1];
                String author = txt[2];
                String year = txt[3];
                String category = txt[4];
                float price = Float.parseFloat(txt[5]);
                String time =(txt[6]);
                double capacity = Double.parseDouble(txt[7]);
                String resolution = txt[8];
                if(criterion == 0 && key.equalsIgnoreCase(txt[1])) {
                    CompactDisc compactDisc = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
                    list.add(compactDisc);
                }
                else if(criterion == 1 && key.equalsIgnoreCase(txt[2])) {
                    CompactDisc compactDisc = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
                    list.add(compactDisc);
                }
                else if(criterion == 2 && key.equalsIgnoreCase(txt[4])) {
                    CompactDisc compactDisc = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
                    list.add(compactDisc);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
