package model;


import data.Data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String name;
    private String phone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Customer() {
    }

    ;

    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return name + ";" + phone;
    }

    public Customer searchCustomer(String key, int criterion) {
        String dir = System.getProperty("user.dir");
        Customer customer = null;
        try {
            FileReader fr = new FileReader(dir + "\\src\\data\\CustomerData.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = "";

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break;
                }
                String txt[] = line.split(";");
                String name = txt[0];
                String phone = txt[1];
                if (criterion == 0 && key.equalsIgnoreCase(name)) {
                    customer = new Customer(name, phone);
                } else if (criterion == 1 && key.equalsIgnoreCase(phone)) {
                    customer = new Customer(name, phone);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public void editCustomer(Customer customer, String name, String phone) {
        String dir = System.getProperty("user.dir");
        Data data = new Data();
        List<Customer> customerList = (data.getDataCustomer());
        List<Bill> billList = data.getDataBill();
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\CustomerData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            FileWriter fw1 = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            for (Customer o : customerList) {
                if (o.getName().equalsIgnoreCase(name) && o.getPhone().equalsIgnoreCase(phone)) {
                    bw.write(customer.toString());
                    bw.newLine();
                } else {
                    bw.write(o.toString());
                    bw.newLine();
                }
            }
            for (Bill o : billList) {
                if (o.getCustomer().getName().equalsIgnoreCase(name) && o.getCustomer().getPhone().equalsIgnoreCase(phone)) {
                    o.getCustomer().setName(customer.getName());
                    o.getCustomer().setPhone(customer.getPhone());
                    bw1.write(o.toStringRental());
                    bw1.newLine();
                } else {
                    bw1.write(o.toStringRental());
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

    public void deleteCustomer(Customer customer) {
        String dir = System.getProperty("user.dir");
        Data data = new Data();
        List<Customer> customerList = (data.getDataCustomer());
        List<Bill> billList = data.getDataBill();
        try {
            FileWriter fw = new FileWriter(dir + "\\src\\data\\CustomerData.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            FileWriter fw1 = new FileWriter(dir + "\\src\\data\\BillData.txt");
            BufferedWriter bw1 = new BufferedWriter(fw1);
            for (Customer o : customerList) {
                if (o.getName().equalsIgnoreCase(customer.getName()) && o.getPhone().equalsIgnoreCase(customer.getPhone())) {
                   continue;
                } else {
                    bw.write(o.toString());
                    bw.newLine();
                }
            }
            for (Bill o : billList) {
                if (o.getCustomer().getName().equalsIgnoreCase(customer.getName()) && o.getCustomer().getPhone().equalsIgnoreCase(customer.getPhone())) {
                   continue;
                } else {
                    bw1.write(o.toStringRental());
                    bw1.newLine();
                }
            }
            bw.close();
            fw.close();
            bw1.close();
            fw1.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
