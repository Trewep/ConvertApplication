package be.trewep.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener, FragmentC.FragmentCListener {

    private FragmentA fragmentA;//celcius
    private FragmentB fragmentB;//fahrenheit
    private FragmentC fragmentC;//kelvin

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();
        fragmentC = new FragmentC();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentA)
                .replace(R.id.layoutB, fragmentB)
                .replace(R.id.layoutC, fragmentC)
                .commit();
    }

    @Override
    public void onInputBSent(String input) {
        fragmentA.updateCelcius(input, 'f');
        fragmentC.updateKelvin(input, 'f');
    }

    @Override
    public void onInputASent(String input) {
        fragmentB.updateFahrnheit(input, 'c');
        fragmentC.updateKelvin(input, 'c');
    }

    @Override
    public void onInputCSent(String input){
        fragmentA.updateCelcius(input, 'k');
        fragmentB.updateFahrnheit(input, 'k');
    }
}