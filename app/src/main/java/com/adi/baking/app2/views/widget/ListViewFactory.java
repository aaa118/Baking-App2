package com.adi.baking.app2.views.widget;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.adi.baking.app2.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.adi.baking.app2.views.widget.IngredientsList.INGREDIENTS_LIST;
import static com.adi.baking.app2.views.widget.IngredientsList.INGREDIENTS_LIST_KEY;
import static com.adi.baking.app2.views.widget.IngredientsList.WIDGET_LIST;

public class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String RECIPE_NAME = "recipeName";
    public static final String RECIPE = "recipe";
    private static final String TAG = "AA_ListViewFactory";

    private Context context;
    private ArrayList<String> recipeNameList;
    private Intent intent;
    private SharedPreferences sharedPref;
    ArrayList<String> defaultList = new ArrayList<>();


    ListViewFactory(Context context, Intent intent) {
        Log.i(TAG, "ListViewFactory: ");
        this.context = context;
        this.intent = intent;
    }

    @Override
    public void onCreate() {
        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
        if (recipeNameList == null) {
            recipeNameList = defaultList;
        }
        defaultList.add("Click On a Recipe to display ingredients here");

        sharedPref = context.getSharedPreferences(INGREDIENTS_LIST, Context.MODE_PRIVATE);
        Set<String> recipesSet = sharedPref.getStringSet(INGREDIENTS_LIST_KEY, null);
        Log.i(TAG, "onCreate: " + recipesSet);
    }


    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return (recipeNameList.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(context.getPackageName(),
                R.layout.row);
//        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
//        Log.i(TAG, "ListViewFactory: " + recipeNameList);


        Log.i(TAG, "getViewAt: "+recipeNameList);
        row.setTextViewText(android.R.id.text1, recipeNameList.get(position));

        Intent i = new Intent();
        Bundle extras = new Bundle();

        extras.putString(RECIPE_NAME, recipeNameList.get(position));
        i.putExtras(extras);

        return (row);
    }

    @Override
    public RemoteViews getLoadingView() {
        return (null);
    }

    @Override
    public int getViewTypeCount() {
        return (1);
    }

    @Override
    public long getItemId(int position) {
        return (position);
    }

    @Override
    public boolean hasStableIds() {
        return (true);
    }

    @Override
    public void onDataSetChanged() {
        // no-op
        ArrayList<String> list = null;
        Set<String> recipesSet = sharedPref.getStringSet(INGREDIENTS_LIST_KEY, null);
        if (recipesSet != null) {
            recipeNameList = new ArrayList<>(recipesSet);
        }
        Log.i(TAG, "onDataSetChanged: Prefs" + recipesSet);

//        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
        Log.i(TAG, "onDataSetChanged: " + recipeNameList);

    }
}
