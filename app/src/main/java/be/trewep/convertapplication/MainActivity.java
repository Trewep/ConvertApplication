package be.trewep.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity  {

    private FragmentTemperature fragmentCelcius;//celcius
    private FragmentTemperature fragmentFahrenheit;//fahrenheit
    private FragmentTemperature fragmentKelvin;//kelvin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentCelcius = new FragmentTemperature();
        fragmentFahrenheit = new FragmentTemperature();
        fragmentKelvin = new FragmentTemperature();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentCelcius)
                .replace(R.id.layoutB, fragmentFahrenheit)
                .replace(R.id.layoutC, fragmentKelvin)
                .commit();

        fragmentCelcius.setOnListener(new FragmentTemperature.FragmentListener() {
            @Override
            public void onInputSent(String input) {
                   //update fahrnheit en Kelvin
                    fragmentFahrenheit.updateTe(input);
                    fragmentKelvin.updateTe(input);
            }
        });

        fragmentFahrenheit.setOnListener(new FragmentTemperature.FragmentListener() {
            @Override
            public void onInputSent(String input) {
                //update celcius en Kelvin
                    fragmentCelcius.updateTe(input);
                    fragmentKelvin.updateTe(input);
            }
        });

        fragmentKelvin.setOnListener(new FragmentTemperature.FragmentListener() {
            @Override
            public void onInputSent(String input) {
                //update fahrnheit en celcius
                    fragmentCelcius.updateTe(input);
                    fragmentFahrenheit.updateTe(input);
            }
        });

    }


}