package be.trewep.convertapplication;

import android.content.Context;
import android.os.Bundle;
import android.text.NoCopySpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentC extends Fragment implements NoCopySpan {

    public interface FragmentCListener {
        void onInputCSent(String input);
    }

    private EditText etKelvin;
    private FragmentCListener listener;

    public FragmentC() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_c, container, false);

        etKelvin = v.findViewById(R.id.et_kelvin);
        v.findViewById(R.id.button_to_kelvin).setOnClickListener(bv -> {
            String input = etKelvin.getText().toString();

            //stuur naar Fragment A

            try {
                input = etKelvin.getText().toString();
                listener.onInputCSent(input);
            } catch(NumberFormatException e){
                etKelvin.setError("Please enter a number");
                return;
            }

        });

        return v;
    }

    //Ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt
    public void updateKelvin(String input, char originTemperature) {

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch (originTemperature) {
            case 'c': //If the origin is celsius
                convertedInput = convertedInput + 273.15;
                break;

            case 'f': //if the origin is fahrenheit
                convertedInput = ((convertedInput - 32) / 1.8) + 273.15;
                break;
        }
        convertedInput = Math.round(convertedInput*100.0)/100.0;

        etKelvin.setError(null);
        etKelvin.setText(convertedInput.toString());
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentCListener){
            listener = (FragmentCListener)context;
        }else{
            throw new RuntimeException(
                    String.format ("%s must implement FragmentCListener", context.toString())
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
    }

}