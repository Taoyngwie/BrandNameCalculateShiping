import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.*;
import java.text.*;

public class ShoppingCalculator extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField priceField;
    private JTextField shippingCostField;
    private JTextField weightField;
    private JComboBox<String> currencyBox;
    private JButton calculateButton;
    private JLabel totalLabel;
    private CurrencyConverter currencyConverter;

    public ShoppingCalculator() {
        super("Shopping Calculator");
        currencyConverter = new CurrencyConverter();

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel priceLabel = new JLabel("Price:");
        priceField = new JTextField(20);
        JPanel pricePanel = new JPanel(new BorderLayout());
        pricePanel.add(priceLabel, BorderLayout.WEST);
        pricePanel.add(priceField, BorderLayout.CENTER);
        panel.add(pricePanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10))); // add some spacing

        JLabel shippingCostLabel = new JLabel("Shipping Cost (in USD):");
        shippingCostField = new JTextField(20);
        JPanel shippingCostPanel = new JPanel(new BorderLayout());
        shippingCostPanel.add(shippingCostLabel, BorderLayout.WEST);
        shippingCostPanel.add(shippingCostField, BorderLayout.CENTER);
        panel.add(shippingCostPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel weightLabel = new JLabel("Weight (in grams):");
        weightField = new JTextField(20);
        JPanel weightPanel = new JPanel(new BorderLayout());
        weightPanel.add(weightLabel, BorderLayout.WEST);
        weightPanel.add(weightField, BorderLayout.CENTER);
        panel.add(weightPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel currencyLabel = new JLabel("Currency:");
        String[] currencies = { "USD", "EUR", "GBP", "JPY", "CAD", "AUD" };
        currencyBox = new JComboBox<>(currencies);
        JPanel currencyPanel = new JPanel(new BorderLayout());
        currencyPanel.add(currencyLabel, BorderLayout.WEST);
        currencyPanel.add(currencyBox, BorderLayout.CENTER);
        panel.add(currencyPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(calculateButton);
        panel.add(buttonPanel);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));

        totalLabel = new JLabel("");
        JPanel totalPanel = new JPanel(new BorderLayout());
        totalPanel.add(totalLabel, BorderLayout.WEST);
        panel.add(totalPanel);
        
        add(panel);

        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        String message = "Average sneaker weight (pair) = 600-1000g\n" + "Average t-shirt weight = 130g\n"
                + "Average hoodie weight = 700g\n" + "Average bag weight = 500-1500g\n" + "Average pants weight = 500g\n"
                + "Average parcel box = 300g\n";

        JOptionPane.showMessageDialog(null, message, "Guide", JOptionPane.INFORMATION_MESSAGE);
        		
    	}
    


public void actionPerformed(ActionEvent e) {
    if (e.getSource() == calculateButton) {
        try {
            double price = Double.parseDouble(priceField.getText());
            double shippingCost = Double.parseDouble(shippingCostField.getText());
            double weight = Double.parseDouble(weightField.getText());
            double totalCost = price + shippingCost;
            String selectedCurrency = (String) currencyBox.getSelectedItem();
            double conversionRate = getConversionRate(selectedCurrency); // get the conversion rate for the selected currency
            if(weight<=1000) {totalCost += 24;}else {totalCost = price + (Math.floor(weight/1000) * 24) + ((weight % 1000)/1000 * 24);}
            double convertedTotal = totalCost * conversionRate;
            String currencySymbol = getCurrencySymbol(selectedCurrency); // get the currency
            totalLabel.setText(String.format("Total cost: %s%.2f", currencySymbol, convertedTotal));
        } catch (NumberFormatException ex) {
            totalLabel.setText("Invalid input");
        }
    }
}

protected void hello() {
    System.out.println();
}

private double getConversionRate(String currency) {
    return currencyConverter.getConversionRate(currency);
}

private String getCurrencySymbol(String currency) {
    switch (currency) {
        case "USD":
            return "$";
        case "EUR":
            return "€";
        case "GBP":
            return "£";
        case "JPY":
            return "¥";
        case "CAD":
            return "$";
        case "AUD":
            return "$";
        default:
            return "";
    }
}

}
