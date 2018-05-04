/**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.name;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        checkbox(view);
        String summary = createOrderSummary();
        EditText nameText = (EditText)findViewById(R.id.name_edit_text);
        String name = nameText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "just java order for "+ name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
    }
    }

    public void checkbox(View view){

    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    public int calculatePrice() {

        int price = quantity * 5;
        return(price);
    }
    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        quantity= 1+quantity;
        if(quantity>=100)
        {
            quantity=100;
        }
        displayQuantity(quantity);
    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        quantity=quantity-1;
        if(quantity<1)
        {
            quantity=1;
        }
        displayQuantity(quantity);
    }


    /**
     * this is the method for the summmry
     */
    public String createOrderSummary(){
        CheckBox hasWippedCream =(CheckBox) findViewById(R.id.wipped_cream_checkbox);
        boolean wippedCream = hasWippedCream.isChecked();
        CheckBox hasChocate = (CheckBox) findViewById(R.id.choclate_checkbox);
        boolean choclate = hasChocate.isChecked();
        EditText nameText = (EditText)findViewById(R.id.name_edit_text);
        String name = nameText.getText().toString();
        int price = 0;
        if(wippedCream==true)
        {
            if (choclate==true){
                price = quantity * (5+1+2);
            }
            else
            {
                price =quantity * (5+1);
            }
        }
        else if (choclate ==true)
        {
            price = quantity*(5+2);
        }
        else
        {
            price = quantity* 5;
        }

        return "Name: "+name+"\nAdd wipped cream?"+wippedCream+"\nAdd choclate topping?" + choclate + "\nQuantity:"+quantity+"\nTotal: "+price+"\nThank You";
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given text on the screen.
     */

    }

