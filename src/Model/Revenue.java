package Model;

import java.util.Date;

public class Revenue {

    public static class ByCate {

        private String cate;
        private double revenue;
        private int quantity;
        private double minPrice;
        private double maxPrice;
        private double avgPrice;

        public ByCate(String cate, double revenue, int quantity, double minPrice, double maxPrice, double avgPrice) {
            this.cate = cate;
            this.revenue = revenue;
            this.quantity = quantity;
            this.minPrice = minPrice;
            this.maxPrice = maxPrice;
            this.avgPrice = avgPrice;
        }

        public String getCate() {
            return cate;
        }

        public void setCate(String cate) {
            this.cate = cate;
        }

        public double getRevenue() {
            return revenue;
        }

        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getMinPrice() {
            return minPrice;
        }

        public void setMinPrice(double minPrice) {
            this.minPrice = minPrice;
        }

        public double getMaxPrice() {
            return maxPrice;
        }

        public void setMaxPrice(double maxPrice) {
            this.maxPrice = maxPrice;
        }

        public double getAvgPrice() {
            return avgPrice;
        }

        public void setAvgPrice(double avgPrice) {
            this.avgPrice = avgPrice;
        }

        @Override
        public String toString() {
            return "ByCate{" + "cate=" + cate + ", revenue=" + revenue + ", quantity=" + quantity + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", avgPrice=" + avgPrice + '}';
        }

    }

    public static class ByUser {

        private String user;
        private double revenue;
        private int quantity;
        private Date firstTime;
        private Date lastTime;

        public ByUser(String user, double revenue, int quantity, Date firstTime, Date lastTime) {
            this.user = user;
            this.revenue = revenue;
            this.quantity = quantity;
            this.firstTime = firstTime;
            this.lastTime = lastTime;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public double getRevenue() {
            return revenue;
        }

        public void setRevenue(double revenue) {
            this.revenue = revenue;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public Date getFirstTime() {
            return firstTime;
        }

        public void setFirstTime(Date firstTime) {
            this.firstTime = firstTime;
        }

        public Date getLastTime() {
            return lastTime;
        }

        public void setLastTime(Date lastTime) {
            this.lastTime = lastTime;
        }

        @Override
        public String toString() {
            return "ByUser{" + "user=" + user + ", revenue=" + revenue + ", quantity=" + quantity + ", firstTime=" + firstTime + ", lastTime=" + lastTime + '}';
        }

    }

}
