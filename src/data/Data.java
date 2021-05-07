package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.*;

import java.io.BufferedReader;
import java.io.FileReader;

public class Data {

    public float getPriceProduct(String itemID) {
        String dir = System.getProperty("user.dir");
        float priceItem = 0;
        if(itemID.indexOf("M") == 1) {
            try {
                FileReader fd = new FileReader(dir +"\\src\\data\\ComicData.txt");
                BufferedReader bd = new BufferedReader(fd);
                String line ="";
                while(true) {
                    line = bd.readLine();
                    if(line == null) {
                        break;
                    }
                    String txt[] = line.split(";");
                    String ID = txt[0];
                    if(ID.equalsIgnoreCase(itemID)) {
                        priceItem = Float.parseFloat(txt[5]);
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                FileReader fd = new FileReader(dir +"\\src\\data\\CompactDiscData.txt");
                BufferedReader bd = new BufferedReader(fd);
                String line ="";
                while(true) {
                    line = bd.readLine();
                    if(line == null) {
                        break;
                    }
                    String txt[] = line.split(";");
                    String ID = txt[0];
                    if(ID.equalsIgnoreCase(itemID)) {
                        priceItem = Float.parseFloat(txt[5]);
                        break;
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return priceItem;
    }
    public ObservableList<Comic> getDataComic() {
        ObservableList<Comic> comicObservableList = FXCollections.observableArrayList();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\ComicData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
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
                int pageNumber = Integer.parseInt(txt[6]);
                String paperSize = txt[7];
                String language = txt[8];
                Comic comic = new Comic(ID, name, author, year, category, price, pageNumber, paperSize, language);
                comicObservableList.add(comic);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comicObservableList;
    }
    public ObservableList<CompactDisc> getDataCD() {
        ObservableList<CompactDisc> compactDiscs = FXCollections.observableArrayList();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\CompactDiscData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
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
                //Sau tất cả;Sobin Hoàng Sơn;2021/5/3;Tình cảm;50.0;1h30;Tình cảm;4K
                CompactDisc cd = new CompactDisc(ID, name, author, year, category, price, time, capacity, resolution);
                compactDiscs.add(cd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return compactDiscs;
    }

    public ObservableList<Bill> getDataBill() {
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\BillData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
                if(line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String codeOrder = txt[0];
                String item = txt[1];
                String itemID = txt[2];
                String nameProduct = txt[3];
                String rentDate = txt[4];
                float deposit = Float.parseFloat(txt[5]);
                String nameCustomer = txt[6];
                String phone = txt[7];
                Customer customer = new Customer(nameCustomer, phone);
                if(item.equals("Comic")) {
                    Product comic = new Comic(nameProduct, itemID);
                    comic.setName(nameProduct);
                    Bill bill = new Bill(codeOrder, item, comic, rentDate, deposit,customer);
                    bills.add(bill);
                }
                else {
                    Product cd = new CompactDisc(nameProduct, itemID);
                    cd.setName(nameProduct);
                    Bill bill = new Bill(codeOrder, item, cd, rentDate, deposit,customer);
                    bills.add(bill);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }

    public ObservableList<Bill> getDataReturn() {
        ObservableList<Bill> bills = FXCollections.observableArrayList();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\BillData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
                if(line == null) {
                    break;
                }
                String txt[] = line.split(";");

                String codeOrder = txt[0];
                String item = txt[1];
                String itemID = txt[2];
                String nameProduct = txt[3];
                String rentDate = txt[4];
                String returnDate = txt[5];
                float priceItem = 0;
                float deposit = Float.parseFloat(txt[6]);
                String nameCustomer = txt[8];
                String phone = txt[9];
                float amoutPaid = Float.parseFloat(txt[10]);
                Customer customer = new Customer(nameCustomer, phone);
                if(item.equals("Comic")) {
                    Product comic = new Comic(nameProduct, itemID);
                    comic.setName(nameProduct);
                    priceItem = getPriceProduct(itemID);
                    comic.setPrice(priceItem);
                    Bill bill = new Bill(codeOrder, item, rentDate, returnDate, deposit, customer, comic);
                    bills.add(bill);
                }
                else {
                    Product cd = new CompactDisc(nameProduct, itemID);
                    cd.setName(nameProduct);
                    priceItem = getPriceProduct(itemID);
                    cd.setPrice(priceItem);
                    Bill bill = new Bill(codeOrder, item, rentDate, returnDate, deposit, customer, cd);
                    bills.add(bill);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bills;
    }
    public ObservableList<Customer> getDataCustomer() {
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\CustomerData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
                if(line == null) {
                    break;
                }
                String txt [] = line.split(";");
                String name = txt[0];
                String phone = txt[1];
                Customer customer = new Customer(name, phone);
                customerList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerList;
    }
    public boolean checkCustomer(String name, String phone) {
        boolean check = false;
        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir +"\\src\\data\\CustomerData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line ="";
            while(true) {
                line = bd.readLine();
                if(line == null) {
                    break;
                }
                String txt [] = line.split(";");
                String nameCustomer = txt[0];
                String phoneCustomer = txt[1];
                if(nameCustomer.equalsIgnoreCase(name) && phone.equalsIgnoreCase(phone)) {
                    check = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(check)
            return false;
        return true;
    }


}
