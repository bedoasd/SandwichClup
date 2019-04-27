package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {
        if(json==null ||json.isEmpty()) {
            return null;
        }
        Sandwich sandwich=new Sandwich();

        try {
            JSONObject sandwichjsonobject=new JSONObject(json);
            //name parsing
            JSONObject namejsonojest=sandwichjsonobject.getJSONObject("name");
            sandwich.setMainName(namejsonojest.getString("mainName"));
            JSONArray alsoKnownAs=namejsonojest.getJSONArray("alsoKnownAs");
            if(alsoKnownAs !=null){
                List<String> alsoKnownas=new ArrayList<>();
                for (int i=0;i<alsoKnownAs.length();i++){
                alsoKnownas.add(alsoKnownAs.getString(i));
                sandwich.setAlsoKnownAs(alsoKnownas);

                }
            }
            //place of origin ..description..image
            sandwich.setPlaceOfOrigin(sandwichjsonobject.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichjsonobject.getString("description"));
            sandwich.setImage(sandwichjsonobject.getString("image"));
            //ingredients parsing
                    JSONArray ingredientsgsonarray=sandwichjsonobject.getJSONArray("ingredients");
                    if(ingredientsgsonarray!=null){
                        List<String>ingrredientslist=new ArrayList<>();
                        for(int i=0;i<ingredientsgsonarray.length();i++){
                        ingrredientslist.add(ingredientsgsonarray.getString(i));


                        }
                        sandwich.setIngredients(ingrredientslist);
                    }

        return  sandwich;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
