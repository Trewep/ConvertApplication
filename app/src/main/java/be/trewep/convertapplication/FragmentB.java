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
            listener.onInputBSent(input);

        });

        return v;
    }

    //Ontvangt data van buitenaf (bvb als er in fragment B op "ok" wordt gedrukt
    public void updateFahrnheit(String input){
        etFahrenheit.setText(input);
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