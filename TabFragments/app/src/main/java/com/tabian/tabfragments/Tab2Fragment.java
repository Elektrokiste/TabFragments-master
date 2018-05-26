package com.tabian.tabfragments;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by User on 2/28/2017.
 */

public class Tab2Fragment extends Fragment {
    private static final String TAG = "Tab2Fragment";
    private EditText editText,editText2;
    public Button Speichern;
    public boolean st = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab2_fragment,container,false);
        editText = (EditText) view.findViewById(R.id.editText);
        editText2 = (EditText) view.findViewById(R.id.editText2);
        SharedPreferences mySPR = this.getActivity().getSharedPreferences("MyPSFLE", Context.MODE_PRIVATE);
        editText.setText(mySPR.getString("Name", "Ich bin der 1.Standardwert"));
        editText2.setText(mySPR.getString("myKey2", "Ich bin der 2.Standardwert"));
        final SharedPreferences.Editor editor = mySPR.edit();
        Speichern = (Button) view.findViewById(R.id.save);
        Speichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("Name", editText.getText().toString());
                editor.putString("myKey2", editText2.getText().toString());
                editor.commit();
                new Hintergrundservice();
                Toast.makeText(getActivity(), "Speichere",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }


}
