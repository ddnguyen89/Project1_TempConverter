package nguyen.project1_tempconverter;

import android.content.SharedPreferences;
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
    private float celcius;

    //format for degrees
    NumberFormat degree = NumberFormat.getNumberInstance();

    private SharedPreferences savedValues;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_converter);

        //get reference to the widget
        celciusTV = (TextView) findViewById(R.id.celciusTV);
        fahrenheitET = (EditText) findViewById(R.id.fahrenheitET);

        //set listeners
        fahrenheitET.setOnEditorActionListener(this);

        //get the shared preferences
        savedValues = getSharedPreferences("savedValues", MODE_PRIVATE);

        fahrenheitString = "";
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

            //calculate celcius
            celcius = (fahrenheit - 32) * 5 / 9;
        }
        //display the results
        celciusTV.setText(degree.format(celcius) + degreeSymbol);
    }

    @Override
    protected void onPause() {
        //save instance variables
        editor = savedValues.edit();
        editor.putString("fahrenheitString", fahrenheitString);
        editor.commit();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //get the instance variables
        fahrenheitString = savedValues.getString("fahrenheitString", "");

        //set the fahrenheit amount on its widget
        fahrenheitET.setText(fahrenheitString);

        //calculate and display
        calculateAndDisplay();
    }
}
