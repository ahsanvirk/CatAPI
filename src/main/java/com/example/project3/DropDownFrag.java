package com.example.project3;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.project3.databinding.FragmentDropDownBinding;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;


public class DropDownFrag extends Fragment {

    private FragmentDropDownBinding binding;
    private RequestQueue requestQueue;
    DropDownFrag.selectionListener activityCallback;

    interface selectionListener{
        void selected(String sel);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activityCallback = (selectionListener) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDropDownBinding.inflate(inflater,container,false);
        requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://api.thecatapi.com/v1/breeds?attach_breed=0",
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        List<String> list = new ArrayList<String>();
                        list.add("Select a breed");
                        for(int i = 0; i< response.length(); i++){
                            try{
                                JSONObject obj = response.getJSONObject(i);
                                list.add(obj.getString("name"));
                                //Picasso.get().load(catURL).into()
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.spinner.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        List<String> list = new ArrayList<String>();
                        list.add("ERROR! NO LIST FOUND");
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, list);
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        binding.spinner.setAdapter(adapter);
                    }
                }
        );
        requestQueue.add(request);

        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                activityCallback.selected(binding.spinner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        return binding.getRoot();
    }

}
