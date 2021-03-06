package be.trewep.convertapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class FragmentB extends Fragment {

    public interface FragmentBListener{
        void onInputBSent(String input);
    }

    private EditText etFahrenheit;
    private FragmentBListener listener;

    public FragmentB() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_b, container, false);

        etFahrenheit = v.findViewById(R.id.et_fahrenheit);
        v.findViewById(R.id.button_to_celcius).setOnClickListener(bv -> {
            String input = etFahrenheit.getText().toString();

            //stuur naar Fragment A

            try {
                input = etFahrenheit.getText().toString();
                listener.onInputBSent(input);
            } catch(NumberFormatException e){
                etFahrenheit.setError("Please enter a number");
                return;
            }

        });

        return v;
    }

    //Ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt

    public void updateFahrnheit(String input, char originTemperature){

        Double convertedInput = 0.0;
        convertedInput = Double.parseDouble(input);

        switch(originTemperature){
            case 'c': //If the origin is celsius
                convertedInput = convertedInput*1.8+32;
                break;

            case 'k': //if the origin is kelvin
                convertedInput = (convertedInput-273.15)*1.8+32;
                break;
        }

        convertedInput = Math.round(convertedInput*100.0)/100.0;


        etFahrenheit.setError(null);
        etFahrenheit.setText(convertedInput.toString());
    }
    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if (context instanceof FragmentBListener){
            listener = (FragmentBListener)context;
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
    }

}