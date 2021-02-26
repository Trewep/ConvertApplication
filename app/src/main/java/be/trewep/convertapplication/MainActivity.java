package be.trewep.convertapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FragmentA.FragmentAListener, FragmentB.FragmentBListener {

    private FragmentA fragmentA;
    private FragmentB fragmentB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentA = new FragmentA();
        fragmentB = new FragmentB();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layoutA, fragmentA)
                .replace(R.id.layoutB, fragmentB)
                .commit();
    }

    @Override
    public void onInputASent(String input) {

        fragmentB.updateFahrnheit(input);

    }

    @Override
    public void onInputBSent(String input) {
        fragmentA.updateCelcius(input);
    }
}