package com.example.a01_11_z2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.materialswitch.MaterialSwitch;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etAddress;
    private Spinner spPizza;
    private RadioGroup rgSize;
    private CheckBox cbCheese, cbMushrooms, cbOlives;
    private SeekBar sbQuantity;
    private TextView tvQuantityValue, tvSummary;
    private MaterialSwitch swDelivery;
    private LinearLayout llAddress;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etAddress = findViewById(R.id.etAddress);
        spPizza = findViewById(R.id.spPizza);
        rgSize = findViewById(R.id.rgSize);
        cbCheese = findViewById(R.id.cbCheese);
        cbMushrooms = findViewById(R.id.cbMushrooms);
        cbOlives = findViewById(R.id.cbOlives);
        sbQuantity = findViewById(R.id.sbQuantity);
        tvQuantityValue = findViewById(R.id.tvQuantityValue);
        tvSummary = findViewById(R.id.tvSummary);
        swDelivery = findViewById(R.id.swDelivery);
        llAddress = findViewById(R.id.llAddress);
        btnOrder = findViewById(R.id.btnOrder);

        setUpPizzaSpinner();
        setUpListeners();
        tvQuantityValue.setText(String.valueOf(sbQuantity.getProgress()));
    }

    private void setUpPizzaSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.pizzas, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spPizza.setAdapter(adapter);
    }

    private void setUpListeners() {
        // zależność: przełącznik dostawy pokazuje i ukrywa pole adresu
        swDelivery.setOnCheckedChangeListener((buttonView, isChecked) ->
                llAddress.setVisibility(isChecked ? View.VISIBLE : View.GONE));

        // suwak - trzy metody, więc klasa anonimowa zamiast lambdy
        sbQuantity.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvQuantityValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}