package com.example.tugas5_recycleview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import com.example.tugas5_recycleview.databinding.ActivityApiBinding;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiActivity extends AppCompatActivity  implements View.OnClickListener{
    private ActivityApiBinding binding;
    String index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =
                ActivityApiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.fetchButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fetch_button){
            index = binding.inputId.getText().toString();
            try {
                getData();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void getData() throws MalformedURLException {
        Uri uri = Uri.parse("https://www.thecocktaildb.com/api/json/v1/1/search.php?s=margarita").buildUpon().build();
        URL url = new URL(uri.toString());
        new DOTask().execute(url);
    }
    class DOTask extends AsyncTask<URL, Void, String>{
        //connection request
        @Override
        protected String doInBackground(URL... urls) {
            URL url = urls [0];
            String data = null;
            try {
                data = NetworkUtils.makeHTTPRequest(url);
            }catch (IOException e){
                e.printStackTrace();
            }
            return data;
        }
        @Override
        protected void onPostExecute(String s){
            try {
                parseJson(s);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        //get data json
        public void parseJson(String data) throws JSONException{
            JSONObject jsonObject = null;
            try {
                jsonObject = new JSONObject(data);
            }catch (JSONException e){
                e.printStackTrace();
            }
//            JSONObject innerObj =
//                    jsonObject.getJSONObject("data");
            JSONArray cityArray = jsonObject.getJSONArray("drinks");
            for (int i =0; i <cityArray.length(); i++){
                JSONObject obj = cityArray.getJSONObject(i);
                String Sobj = obj.get("idDrink").toString();
                if (Sobj.equals(index)){
                    String name = obj.get("strInstructions").toString();
                    String description = obj.get("strInstructions").toString();
                    String created_at = obj.get("strCategory").toString();
                    String updated_at = obj.get("strAlcoholic").toString();
                    String qty = obj.get("strGlass").toString();
                    binding.resultName.setText(name);
                    binding.resultDescription.setText(description);
                    binding.resultCreated.setText(created_at);
                    binding.resultUpdated.setText(updated_at);
                    binding.resultQty.setText(qty);
                    break;
                }
                else{
                    binding.resultName.setText("Not Found");
                }
            }
        }
    }
}
