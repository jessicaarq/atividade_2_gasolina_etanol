package com.example.atividade_2_gasolina_etanol;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFORMAT =
            NumberFormat.getCurrencyInstance();

    private TextView gasolinaTextView;
    private SeekBar gasolinaSeekBar;
    private TextView etanolTextView;
    private SeekBar etanolSeekBar;
    private TextInputEditText resultTextInput;
    private ImageView fuelImageView;

    private double gasPrice;
    private double etanolPrice;
    private double percentDivider = .7;

    private int currentMax = 100, currentStep = 20;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gasolinaTextView = findViewById(R.id.gasolinaTextView);
        etanolTextView = findViewById(R.id.etanolTextView);
        resultTextInput = findViewById(R.id.resultTextInput);
        fuelImageView = findViewById(R.id.fuelImageView);

        //eventos observáveis
        gasolinaSeekBar = findViewById(R.id.gasolinaSeekBar);
        etanolSeekBar = findViewById(R.id.etanolSeekBar);

        //configurações iniciais
        gasolinaSeekBar.setMax(currentMax);
        gasolinaSeekBar.setProgress(gasolinaSeekBar.getMax()/2);
        etanolSeekBar.setMax(currentMax);
        etanolSeekBar.setProgress(etanolSeekBar.getMax()/2);

        gasPrice = gasolinaSeekBar.getProgress();
        etanolPrice = etanolSeekBar.getProgress();
        gasolinaTextView.setText(currencyFORMAT.format(gasPrice/currentStep));
        etanolTextView.setText(currencyFORMAT.format(etanolPrice/currentStep));
        checkFuel();

        gasolinaSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gasPrice = progress;
                gasolinaTextView.setText(currencyFORMAT.format(gasPrice/currentStep));
                checkFuel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        etanolSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                etanolPrice = progress;
                etanolTextView.setText(currencyFORMAT.format(etanolPrice/currentStep));
                checkFuel();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void checkFuel(){
        if ((gasPrice * percentDivider) <= etanolPrice){
            resultTextInput.setText(R.string.gas);
            fuelImageView.setImageResource(R.drawable.img_gasolina);
        }
        else{
            resultTextInput.setText(R.string.etanol);
            fuelImageView.setImageResource(R.drawable.img_etanol);
        }
    }
}