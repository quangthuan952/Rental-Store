package model;

import java.util.Date;
    public class Customer {
        private String name;
        private int phone;
        private String items;
        private Date duration;
        private double deposit;
        private double hireCharge;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPhone() {
            return phone;
        }

        public void setPhone(int phone) {
            this.phone = phone;
        }

        public String getItems() {
            return items;
        }

        public void setItems(String items) {
            this.items = items;
        }

        public Date getDuration() {
            return duration;
        }

        public void setDuration(Date duration) {
            this.duration = duration;
        }

        public double getDeposit() {
            return deposit;
        }

        public void setDeposit(double deposit) {
            this.deposit = deposit;
        }

        public double getHireCharge() {
            return hireCharge;
        }

        public void setHireCharge(double hireCharge) {
            this.hireCharge = hireCharge;
        }
}
