package model;

/*
 * @author: Hoàng Quang Thuận*/

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
    private String item;
    private String rentDate;
    private String returnDate;
    private float deposit;
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

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public Bill(String codeOrder, String items, Product product, String rentDate, float deposit, Customer customer) {
        this.codeOrder = codeOrder;
        this.item = items;
        this.product = product;
        this.rentDate = rentDate;
        this.deposit = deposit;
        this.customer = customer;
    }

    public Bill(String codeOrder, String items, String rentDate, String returnDate, float deposit, Customer customer, Product product) {
        this.codeOrder = codeOrder;
        this.item = items;
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.deposit = deposit;
        this.customer = customer;
        this.product = product;
    }

    public Bill() {
    }

    // tính phí thuê sản phẩm
    public float calculateHireCharge() {
        LocalDate dateBefore = LocalDate.parse(rentDate);
        LocalDate dateAfter = LocalDate.parse(returnDate);
        return ChronoUnit.DAYS.between(dateBefore, dateAfter) * getProduct().getPrice();
    }

    // tính số tiền phải trả
    public float calculateAmountPay() {
        return calculateHireCharge() - deposit;
    }

    public String toStringRental() {
        return codeOrder + ";" + item + ";" + product.getId() + ";" + product.getName() + ";" + rentDate + ";" + deposit + ";" + customer.getName() + ";" + customer.getPhone();
    }

    public String toStringReturn() {
        return codeOrder + ";" + item + ";" + product.getId() + ";" + product.getName() + ";" + rentDate + ";" + returnDate
                + ";" + deposit + ";" + calculateHireCharge() + ";" + customer.getName() + ";" + customer.getPhone() + ";" + calculateAmountPay();
    }

    public void addBill(Bill bill) {
        Data data = new Data();
        String dir = System.getProperty("user.dir");
        String nameCustomer = bill.getCustomer().getName();
        String phoneCusomer = bill.getCustomer().getPhone();
        Customer customer = new Customer(nameCustomer, phoneCusomer);
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\BillData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            FileWriter fw1 = new FileWriter(dir + "\\src\\data\\CustomerData.txt", true);
            if (data.checkCustomer(nameCustomer, phoneCusomer)) {
                BufferedWriter bw2 = new BufferedWriter(fw1);
                bw2.write(customer.toString());
                bw2.newLine();
                bw2.close();
            }
            bw.write(bill.toStringRental());
            bw.newLine();
            fw1.close();
            bw.close();
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editBill(Bill bill, String name, String phone) {
        String dir = System.getProperty("user.dir");
        Data data = new Data();
        List<Bill> l = data.getDataBill();
        List<Customer> customerList = data.getDataCustomer();
        Customer customer = null;
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            FileWriter fw1 = new FileWriter(dir + "\\src\\data\\CustomerData.txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            for (Bill o : l) {
                if (o.getCodeOrder().equalsIgnoreCase(bill.getCodeOrder())) {
                    String nameCustomer = bill.getCustomer().getName();
                    String phoneCustomer = bill.getCustomer().getPhone();
                    customer = new Customer(nameCustomer, phoneCustomer);
                    bw.write(bill.toStringRental());
                    bw.newLine();
                } else {
                    bw.write(o.toStringRental());
                    bw.newLine();
                }
            }
            for (Customer o : customerList) {
                if (o.getName().equalsIgnoreCase(name) && o.getPhone().equalsIgnoreCase(phone)) {
                    bw1.write(customer.toString());
                    bw1.newLine();
                } else {
                    bw1.write(o.toString());
                    bw1.newLine();
                }
            }
            bw.close();
            fw.close();
            bw1.close();
            fw1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteBill(Bill bill) {
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

                if (item.equalsIgnoreCase("Comic")) {
                    product = new Comic(nameProduct, itemID);
                    product.setName(nameProduct);
                } else {
                    product = new CompactDisc(nameProduct, itemID);
                    product.setName(nameProduct);
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
                } else if (criterion == 3) {
                    LocalDate dateBefore = LocalDate.parse(rentDate);
                    LocalDate dateAfter = LocalDate.parse(key);
                    if (ChronoUnit.DAYS.between(dateBefore, dateAfter) >= 0) {
                        Bill bill = new Bill(codeOrder, item, product, rentDate, deposit, customer);
                        list.add(bill);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public void returnProduct(Bill bill) {
        String dir = System.getProperty("user.dir");
        Data data = new Data();
        List<Bill> billList = data.getDataBill();
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\ReturnData.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            FileWriter fw1 = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw.write(bill.toStringReturn());
            bw.newLine();
            bw.close();
            fw.close();
            for (Bill o : billList) {
                if (o.getCodeOrder().equalsIgnoreCase(bill.getCodeOrder())) {
                    continue;
                }
                bw1.write(o.toStringRental());
                bw1.newLine();
            }
            bw1.close();
            fw1.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // tính tổng doanh thu trong 1 khoảng thời gian
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
                float rentalFee = Float.parseFloat(txt[7]);
                LocalDate tmp = LocalDate.parse(date);
                if (ChronoUnit.DAYS.between(tmp, dateFrom) <= 0 && ChronoUnit.DAYS.between(tmp, dateTo) >= 0) {
                    revenue += rentalFee;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return revenue;
    }

    // tính tổng doanh thu
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
                if (item.indexOf("D") == 1) {
                    revenueCD += rentalFee;
                } else {
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