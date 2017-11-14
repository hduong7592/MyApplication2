package com.example.hieuduong.csc_201_assignment12_android;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


/**
 * Create by hieuduong on 11/13/17
 *
 * CSC 201 - Assignment 12
 * Problem 12.6:
 *
 * (NumberFormatException) Listing 6.8 implements the hex2Dec(String
 * hexString) method, which converts a hex string into a decimal number.
 * Implement the hex2Dec method to throw a NumberFormatException if the
 * string is not a hex string.
 */

public class MainActivity extends AppCompatActivity {

    Button submitBtn;
    EditText inputTxt;
    TextView resultLB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submitBtn = (Button) findViewById(R.id.submitBtn);
        inputTxt = (EditText) findViewById(R.id.inputTxt);
        resultLB = (TextView) findViewById(R.id.resultLB);

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hex = inputTxt.getText().toString().toUpperCase();

                if(hex.isEmpty()){
                    showSimpleDialog("Value is required");
                    inputTxt.requestFocus();
                }
                else{
                    try {
                        int value = Integer.parseInt(hex, 16);
                        resultLB.setText("Decimal value is: "+convertToHex(hex));
                    } catch (NumberFormatException ex) {
                        showSimpleDialog(hex + " is not a valid hex");
                    }
                }
            }
        });
    }

    /**
     * Convert to Hex method
     */
    private int convertToHex(String hex){

        int decimalValue = 0;
        for (int i = 0; i < hex.length(); i++) {
            char hexChar = hex.charAt(i);
            decimalValue = decimalValue * 16 + hexCharToDecimal(hexChar);
        }
        return decimalValue;
    }

    /**
     * Get value of hex char
     * @param ch
     * @return
     */
    private int hexCharToDecimal(char ch) {
        if(ch >= 'A' && ch <= 'F')
            return 10 + ch - 'A';
        else // ch is '0', '1', ..., or '9'
            return ch - '0';

    }

    /**
     * Alert box
     * @param Message
     */

    public void showSimpleDialog(String Message) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setCancelable(false);
        builder.setTitle("Error");
        builder.setMessage(Message);
        builder.setPositiveButton("Got it!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
    }
}
