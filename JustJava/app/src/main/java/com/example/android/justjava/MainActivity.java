/**
 * Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava; 
 */
package com.example.android.justjava;


import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.justjava.R;

import java.net.URI;
import java.text.NumberFormat;

import static android.R.attr.duration;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    // number of Coffees
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whippedCream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        EditText nameTextField = (EditText) findViewById(R.id.name_view);
        String name = String.valueOf(nameTextField.getText());
        int price = calculatePrice(hasWhippedCream, hasChocolate);
        String priceMessage =  createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        String[] addresses = {"vladimir.fomene@ashesi.edu.gh"};
        composeEmail(addresses,"Coffee Order Summary for " + name, priceMessage);

    }


    public void composeEmail(String[] addresses, String subject, String message) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, addresses);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Computes the order summary for our custormers
     * @param hasWhippedCream tells you whether or not order has whipped cream
     * @param hasChocolate tells you whether or not order has chocolate
     * @param price per cup of coffee
     * @return summary order summary for a client
     */
    private String createOrderSummary(int price, boolean hasWhippedCream, boolean hasChocolate, String clientName){
        String summary = getString(R.string.order_summary_name, clientName) + "\n" +
                         "Add whipped Cream? " + hasWhippedCream + "\n" +
                         "Add Chocolate? " + hasChocolate + "\n" +
                         "Quantity: " + quantity + "\n" +
                         "Total: " + "$" + price + "\n" +
                         getString(R.string.thank_you);
        return summary;

    }

    /**
     * Calculates the price of the order.
     */
    private int calculatePrice(boolean hasWhippedCream, boolean hasChocolate) {

        int basePrice = 5;

        //add price for whipped cream
        if(hasWhippedCream){ basePrice += 1; }

        //add price for chocolate
        if(hasChocolate){ basePrice += 2;  }

        return quantity * basePrice;
    }

    /*
    *  This method increment the counter for cups of coffee
     */
    public void increment(View view){

        //Make sure that you don't exceed 100 cups
        if(quantity < 100) {
            quantity = quantity + 1;
        }else {
            Toast.makeText(getApplicationContext(), "You can't go above 100 cup", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    /*
    * This method decrement the counter for cups of coffees
     */
    public void decrement(View view){

        //Make sure that you don't go below 1 cup
        if(quantity > 1) {
            quantity = quantity - 1;
        }else {
            Toast.makeText(getApplicationContext(), "You can't go below 1 cup", Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity(quantity);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int numberOfCoffees) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numberOfCoffees);
    }



}