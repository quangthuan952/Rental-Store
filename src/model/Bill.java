package model;

import data.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Bill {
    private String codeOrder;
    private String items;
    private String rentDate;
    private String returnDate;
    private float deposit;
    //private float hireCharge;
    private Customer customer;
    private Product product;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {

        this.customer = customer;
    }


    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getRentDate() {
        return rentDate;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getCodeOrder() {
        return codeOrder;
    }

    public void setCodeOrder(String codeOrder) {
        this.codeOrder = codeOrder;
    }

    public float getDeposit() {
        return deposit;
    }

    public void setDeposit(float deposit) {
        this.deposit = deposit;
    }

//    public float getHireCharge() {
//        return hireCharge;
//    }
//
//    public void setHireCharge(float hireCharge) {
//        this.hireCharge = hireCharge;
//    }

    public Bill(String codeOrder, String items, String rentDate, float deposit, Customer customer) {
        this.codeOrder = codeOrder;
        this.items = items;
        this.rentDate = rentDate;
        this.deposit = deposit;
        this.customer = customer;

    }

    public Bill(String codeOrder, String items, Product product, String rentDate, float deposit, Customer customer) {
        this.codeOrder = codeOrder;
        this.items = items;
        this.product = product;
        this.rentDate = rentDate;
        this.deposit = deposit;
        this.customer = customer;
    }

    public Bill(String codeOrder, String items, String rentDate, String returnDate, float deposit, Customer customer, Product product) {
        this.codeOrder = codeOrder;
        this.items = items;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.deposit = deposit;
        // this.hireCharge = hireCharge;
        this.customer = customer;
        this.product = product;
    }

    public Bill() {
    }

    ;

    public float hireCharge() {
        LocalDate dateBefore = LocalDate.parse(rentDate);
        LocalDate dateAfter = LocalDate.parse(returnDate);
        return ChronoUnit.DAYS.between(dateBefore, dateAfter) * getProduct().getPrice();
    }

    public float calculateAmountPay() {
        return hireCharge() - deposit;
    }

    public String toStringRental() {
        return codeOrder + ";" + items + ";" + product.getId() + ";" + product.getName() + ";" + rentDate + ";" + deposit + ";" + customer.getName() + ";" + customer.getPhone();
    }

    public String toStringReturn() {
        return codeOrder + ";" + items + ";" + product.getId() + ";" + product.getName() + ";" + rentDate + ";" + returnDate
                + ";" + deposit + ";" + hireCharge() + ";" + customer.getName() + ";" + customer.getPhone() + ";" + calculateAmountPay();
    }

    public void addBill(Bill bill) {
        String dir = System.getProperty("user.dir");
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\BillData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(bill.toStringRental());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editRental(Bill bill) {
        String dir = System.getProperty("user.dir");
        List<Bill> l = (new Data().getDataBill());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Bill o : l) {
                if (o.getCodeOrder().equalsIgnoreCase(bill.getCodeOrder())) {
                    bw.write(String.valueOf((bill)));
                    bw.newLine();
                } else {
                    bw.write(String.valueOf((Bill) o));
                    bw.newLine();
                }
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteRental(Bill bill) {
        String dir = System.getProperty("user.dir");
        List<Bill> l = (new Data().getDataBill());
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Bill o : l) {
                if (o.getCodeOrder().equalsIgnoreCase(bill.getCodeOrder())) {
                    continue;
                }
                bw.write(o.toStringRental());
                bw.newLine();
            }
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Bill> searchBill(String key, int criterion) {
        List<Bill> list = new ArrayList<Bill>();
        String dir = System.getProperty("user.dir");
        try {
            FileReader fr = new FileReader(dir + "\\src\\data\\BillData.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            while (true) {
                line = br.readLine();
                if (line == null) {
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
                Product product = null;
                Product comic = null;
                Product cd = null;
                if (item.equalsIgnoreCase("Comic")) {

                    comic = new Comic(nameProduct, itemID);
                    product = comic;
                    comic.setName(nameProduct);
                } else {
                    cd = new CompactDisc(nameProduct, itemID);
                    product = cd;
                    cd.setName(nameProduct);
                }
                if (criterion == 0 && key.equalsIgnoreCase(codeOrder)) {
                    Bill bill = new Bill(codeOrder, item, product, rentDate, deposit, customer);
                    list.add(bill);
                } else if (criterion == 1 && key.equalsIgnoreCase(nameCustomer)) {
                    Bill bill = new Bill(codeOrder, item, product, rentDate, deposit, customer);
                    list.add(bill);
                } else if (criterion == 2 && key.equalsIgnoreCase(phone)) {
                    Bill bill = new Bill(codeOrder, item, product, rentDate, deposit, customer);
                    list.add(bill);
                } else if (criterion == 3 && key.equalsIgnoreCase(rentDate)) {
                    Bill bill = new Bill(codeOrder, item, product, rentDate, deposit, customer);
                    list.add(bill);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void returnProduct(Bill bill) {
        String dir = System.getProperty("user.dir");
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\ReturnData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(bill.toStringReturn());
            bw.newLine();
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public float calculateRevenue(String from, String to) {
        float revenue = 0;
        LocalDate dateFrom = LocalDate.parse(from);
        LocalDate dateTo = LocalDate.parse(to);

        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir + "\\src\\data\\ReturnData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line = "";
            while (true) {
                line = bd.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String date = txt[5];

                //ChronoUnit.DAYS.between(dateForm, tmp) < 0 thì tmp là những ngày trước dateForm
                float rentalFee = Float.parseFloat(txt[7]);
                LocalDate tmp = LocalDate.parse(date);
                if (ChronoUnit.DAYS.between(tmp, dateFrom) < 0 && ChronoUnit.DAYS.between(tmp, dateTo) > 0) {
                    revenue += rentalFee;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenue;
    }

    public List<Float> calculateTotalRevenue() {
        List<Float> list = new ArrayList<>();
        float revenueComic = 0;
        float revenueCD = 0;
        float totalRevenue = 0;

        String dir = System.getProperty("user.dir");
        try {
            FileReader fd = new FileReader(dir + "\\src\\data\\ReturnData.txt");
            BufferedReader bd = new BufferedReader(fd);
            String line = "";
            while (true) {
                line = bd.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String item = txt[1];
                float rentalFee = Float.parseFloat(txt[7]);
                totalRevenue += rentalFee;
                if(item.indexOf("D") == 1) {
                    revenueCD += rentalFee;
                }
                else {
                    revenueComic += rentalFee;
                }
            }
            list.add(totalRevenue);
            list.add(revenueComic);
            list.add(revenueCD);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
