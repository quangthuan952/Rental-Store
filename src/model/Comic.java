package model;

import data.Data;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;


public class Comic extends Product {
    private int pageNumber;
    private String paperSize;
    private String language;


    public int getPageNumber() {

        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {

        this.pageNumber = pageNumber;
    }

    public String getPaperSize() {

        return paperSize;
    }

    public void setPaperSize(String paperSize) {

        this.paperSize = paperSize;
    }

    public String getLanguage() {

        return language;
    }

    public void setLanguage(String language) {

        this.language = language;
    }


    public Comic(String id, String name, String author, String yearOfPublication, String category, float price, int pageNumber, String paperSize, String language) {
        super(id, name, author, yearOfPublication, category, price);
        this.pageNumber = pageNumber;
        this.paperSize = paperSize;
        this.language = language;
    }

    public Comic(int pageNumber, String paperSize, String language) {
        this.pageNumber = pageNumber;
        this.paperSize = paperSize;
        this.language = language;
    }

    public Comic() {

    }
    public Comic(String name, String ID) {
        super(name, ID);
    }

    @Override
    public String toString() {
        return this.getId() + ";" + this.getName() + ";" + this.getAuthor() + ";" + this.getYearOfPublication() + ";" + this.getCategory() + ";" + this.getPrice()
                + ";" + this.getPageNumber() + ";" + this.getPaperSize() + ";" + this.getLanguage();
    }

    @Override
    public void addProduct(Product product) {

        String dir = System.getProperty("user.dir");
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\ComicData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(String.valueOf((Comic) product));
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
        List<Comic> l = (new Data().getDataComic());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\ComicData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Comic o: l) {
                if(o.getId().equalsIgnoreCase(product.getId())) {
                    bw.write(String.valueOf((Comic) product));
                    bw.newLine();
                }
                else {
                    bw.write(String.valueOf((Comic) o));
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
        List<Comic> l = (new Data().getDataComic());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\ComicData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Comic o: l) {
                if(o.getId().equalsIgnoreCase(product.getId())) {
                    continue;
               }
                bw.write(String.valueOf((Comic) o));
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
            FileReader fr = new FileReader(dir + "\\src\\data\\ComicData.txt");
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
                String year = (txt[3]);
                String category = txt[4];
                float price = Float.parseFloat(txt[5]);
                int pageNumber = Integer.parseInt(txt[6]);
                String paperSize = txt[7];
                String language = txt[8];
                if(criterion == 0 && key.equalsIgnoreCase(txt[1])) {
                        Comic c = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
                        list.add(c);
                }
                else if(criterion == 1 && key.equalsIgnoreCase(txt[2])) {
                    Comic c = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
                    list.add(c);
                }
                else if(criterion == 2 && key.equalsIgnoreCase(txt[4])) {
                    Comic c = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
