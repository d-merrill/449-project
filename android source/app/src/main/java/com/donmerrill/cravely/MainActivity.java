package com.donmerrill.cravely;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String FOOD_URL = "http://donmerrill.com/cs449/cravely_mysql.php";

    RecyclerView rv;
    List<Food> foodList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);

        rv = findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));

        foodList = new ArrayList<>();

        // fetch json, parse it, display it in recyclerview
        loadFood();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu m) {
        MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.menu, m);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadFood(){
        StringRequest sr = new StringRequest(Request.Method.GET, FOOD_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    // converting the string to json array object
                    JSONArray array = new JSONArray(response);

                    // traverse through object
                    for (int i = 0; i < array.length(); i++) {
                        //getting food object from json array
                        JSONObject food = array.getJSONObject(i);

                        foodList.add(
                                new Food(
                                        food.getInt("foodID"),
                                        food.getString("foodName"),
                                        food.getString("origin"),
                                        food.getString("description")
                                ));
                    }

                    // create adapter object and setting to recycleview
                    FoodAdapter adapter = new FoodAdapter(MainActivity.this, foodList);
                    rv.setAdapter(adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener(){
            @Override
                    public void onErrorResponse(VolleyError error){

            }
                });

        Volley.newRequestQueue(this).add(sr);
    }
}
