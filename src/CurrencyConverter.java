//CurrencyConverter.java
public class CurrencyConverter {
 public double getConversionRate(String currency) {
     switch (currency) {
         case "USD":
             return 1.0;
         case "EUR":
             return 0.83;
         case "GBP":
             return 0.72;
         case "JPY":
             return 109.44;
         case "CAD":
             return 1.24;
         case "AUD":
             return 1.31;
         default:
             return 1.0; // return 1.0 as default conversion rate
     }
 }
 }

