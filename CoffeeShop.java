
package javaapplicationsunhacks;

public class CoffeeShop {
  
//instance variables 
    double price; int cups; double balance; String supplier; double advertising; int customers;
    public CoffeeShop() {
    balance = 800; 
    cups = 0; price = 0;
    supplier = "Default";
    advertising = 0; 
}
    
    public void setCups(int c) {
        if(cups > 0) balance += cups; 
        setBalance(-cups); 
        cups = c;}
    public void setBalance(double b) {balance += b;}
    public void addCustomers(int c) { customers += c; }
    public void setPrice(double p) {price = p;} 
    public void setSupplier(String s) {
        if (supplier.equals("a")) {
            balance += 400; 
        }
        if (supplier.equals("b")) {
            balance += 200; 
        }
        supplier = s;
        if (s.equals("a")) balance -= 400; 
        if (s.equals("b")) balance -= 200; 
    }
    public void setAdvertising(double a) {
        if(advertising > 0) advertising += a; 
        balance -= a;
        advertising = a;
    } 
    public int getCups() {return cups; } 
    public double getPrice() {return price; }
    public double getAdvertising() {return advertising; } 
    public String getSupplier() {
       if (supplier.equals("a")) {
           return "Local organic farm";
       } 
       if (supplier.equals("b")) {
           return "Bulk corporate supplier";
        }
       else return "Default"; 
       }
    public double getBalance() {return balance;}
    public int getCustomers() { return customers; }



}


