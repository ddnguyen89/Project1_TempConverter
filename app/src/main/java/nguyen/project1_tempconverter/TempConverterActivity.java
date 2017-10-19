package nguyen.project1_tempconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import java.text.NumberFormat;

public class TempConverterActivity extends AppCompatActivity implements OnEditorActionListener {
    //define member variables for the widgets
    private TextView celciusTV;
    private EditText fahrenheitET;

    //string instance variables
    private String fahrenheitString = "";
    private float fahrenheit;
    private char degreeSymbol = (char)0x00B0;

    //format for degrees
    NumberFormat degree = NumberFormat.getNumberInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);

        //get reference to the widget
        celciusTV = (TextView) findViewById(R.id.celciusTV);
        fahrenheitET = (EditText) findViewById(R.id.fahrenheitET);

        //set listeners
        fahrenheitET.setOnEditorActionListener(this);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        calculateAndDisplay();
        return false;
    }

    private void calculateAndDisplay() {
        fahrenheitString = fahrenheitET.getEditableText().toString();

        if(fahrenheitString.equals("")) {
            fahrenheit = 0;
        } else {
            fahrenheit = Float.parseFloat(fahrenheitString);
        }

        //calculate celcius
        float celcius = (fahrenheit - 32) * 5 / 9;

        //display the results
        celciusTV.setText(degree.format(celcius) + degreeSymbol);
    }
}
