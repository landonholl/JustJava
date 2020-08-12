package com.example.android.justjava;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    int price = 0;
    int total = 0;
    TextView quantityTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("g","my message");
        quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + total);
    }

    /**
     * This method is called when the + button is clicked.
     */
    public void increment(View view) {
        if (total == 100){
            Toast.makeText(this, "You cannot have more than 100 cups of coffee", Toast.LENGTH_SHORT).show();
            return;
        }

        total = total + 1;
        displayQuantity(total);

    }
    public void decrement(View view) {
        if(total == 1) {
            Toast.makeText(this, "You cannot have less tan 1 cup of coffee", Toast.LENGTH_SHORT).show();
            return;

        }

        total = total - 1;
        displayQuantity(total);

    }

    /**
     * This method displays the given quantity value on the screen.
     */

    private void displayQuantity(int number) {
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        int basePrice = 5;

        if (addWhippedCream){
            basePrice = basePrice + 1;
        }

        if (addChocolate){
            basePrice = basePrice + 2;
        }

        return total * basePrice;
    }

    public void submitOrder (View view) {
       CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.WhippedBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();
        Log.v("MainActivity" , "Has Whipped Cream: " + hasWhippedCream);

        CheckBox chocolateCheckBox = (CheckBox) findViewById(R.id.ChocolateBox);
        boolean hasChocolate = chocolateCheckBox.isChecked();
        Log.v("MAinActivity" , "Has Chocolate: " + hasChocolate);



        EditText nameField = (EditText)findViewById(R.id.nameField);
        String name = nameField.getText().toString();

        int price = calculatePrice(hasWhippedCream, hasChocolate);

        String priceMessage = createOrderSummary(price, hasWhippedCream, hasChocolate, name);
        displayMessage(priceMessage);
    }


    private String createOrderSummary (int price, boolean addWhippedCream, boolean addChocolate, String nameField) {
        String priceMessage = "\nName: " + nameField;
        priceMessage += "\nAdd whipped cream? " + addWhippedCream;
        priceMessage += "\nAdd Chocolate? " + addChocolate;
        priceMessage += "\nQuantity:" + total;
        priceMessage += "\nTotal: $" + calculatePrice(addWhippedCream , addChocolate);
        priceMessage += "\nThank you!";
        return priceMessage;
    }

}
