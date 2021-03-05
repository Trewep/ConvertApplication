package be.trewep.convertapplication;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FragmentTemperature extends Fragment {

    public interface FragmentListener {
        void onInputSent(String input);
    }

    private EditText etTemp;
    private FragmentListener listener;

    public FragmentTemperature() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_temperature, container, false);

        etTemp = v.findViewById(R.id.et_celcius);
        v.findViewById(R.id.button_to_fahrnheit).setOnClickListener(bv -> {
            String input = etTemp.getText().toString();

            //stuur naar Fragment
            try {
                input = etTemp.getText().toString();
                listener.onInputSent(input);
            }catch (NumberFormatException e){
                etTemp.setError("Please enter a number");
                return;
            }

        });

        return v;
    }
    public void setOnListener(FragmentListener listener){
        this.listener = listener;
    }

    public void updateTe (String Temp){
        etTemp.setText(Temp);
    }

    /*
    //Ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt
    public void updateCelcius(String input){
        etTemp.setText(input);
    }
    public void updateCelcius(String input, char originTemperature){

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch(originTemperature){
            case 'f': //If the origin is fahrenheit
                convertedInput = (convertedInput -32)/1.8;
                break;

            case 'k': //if the origin is kelvin
                convertedInput = convertedInput - 273.15;
                break;
        }

        convertedInput = Math.round(convertedInput*100.0)/100.0;

        etTemp.setError(null);
        etTemp.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentListener){
            listener = (FragmentListener)context;
        }else{
            throw new RuntimeException(
                    String.format ("%s must implement FragmentAListener", context.toString())
            );
        }
    }
    //public void setFragmentListener(FragmentAListener listener){
      //  this.listener = listener;
    //}
    @Override
    public void onDetach(){
        super.onDetach();
        listener = null;
    }*/

}