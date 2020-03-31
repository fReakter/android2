package hr.unidu.kz.pretvorbavaluta;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> pod;
    EditText unosVrijeme =  (EditText) findViewById(R.id.unosVrijeme);
    EditText unosDatum =  (EditText) findViewById(R.id.unosDatum);
    EditText unosTemperatura =  (EditText) findViewById(R.id.unosTemperatura);
    EditText editText = (EditText)findViewById(R.id.editText);
    int broj=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pod=new ArrayList<>();
        pod.add("test");

        Button buttonSave= findViewById(R.id.spremi);

        editText.setText(String.valueOf(broj));

        buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                pod.add("Datum: "+unosDatum.getText().toString());
                pod.add("Vrijme: "+unosVrijeme.getText().toString());
                pod.add("Tempertura: "+unosTemperatura.getText().toString());
                broj++;
                saveData();
            }
        });
    }
    private void saveData(){
        SharedPreferences sp=getSharedPreferences("Kljuc",MODE_PRIVATE);
        SharedPreferences.Editor edit=sp.edit();
        Gson gson=new Gson();
        String json=gson.toJson(pod);
        edit.putString("kljuc1",json);
        edit.apply();

    }

    private void loadData(){
        SharedPreferences sp=getSharedPreferences("Kljuc",MODE_PRIVATE);
        Gson gson=new Gson();
        String json=sp.getString("kljuc1",null);
        Type type =new TypeToken<ArrayList<String>>() {}.getType();
        pod=gson.fromJson(json,type);
    }
}
