package com.adi.baking.app2;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;

import java.util.ArrayList;
import java.util.List;

import static com.adi.baking.app2.IngredientsList.ARG_ITEM_ID_LIST;
import static com.adi.baking.app2.IngredientsList.WIDGET_LIST;
import static com.adi.baking.app2.ItemDetailFragment.ARG_ITEM_ID;

public class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String[] items = {"lorem", "ipsum", "dolor",
            "purus"};
    public static final String RECIPE_NAME = "recipeName";
    public static final String RECIPE = "recipe";
    private static final String TAG = "AA_ListViewFactory";

    private Context ctxt;
    private int appWidgetId;
    private ArrayList<String> recipeNameList;
//    private ArrayList<Ingredient> ingredientsList;
//    private ArrayList<RecipeName> recipeList;

    public ListViewFactory(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
//        ingredientsList = intent.getParcelableArrayListExtra(WIDGET_LIST);
        Log.i(TAG, "ListViewFactory: "+recipeNameList);
//        recipeList = intent.getParcelableArrayListExtra(ARG_ITEM_ID_LIST);
    }

    @Override
    public void onCreate() {
        // no-op
    }

    @Override
    public void onDestroy() {
        // no-op
    }

    @Override
    public int getCount() {
        return (items.length);
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(ctxt.getPackageName(),
                R.layout.row);

//        row.setTextViewText(android.R.id.text1, ingredientsList.get(0).getIngredient());
        row.setTextViewText(android.R.id.text1, recipeNameList.get(position));

        Intent i = new Intent();
        Bundle extras = new Bundle();

        extras.putString(RECIPE_NAME, recipeNameList.get(position));
        i.putExtras(extras);

//        row.setOnClickFillInIntent(android.R.id.text1, i);

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
    }
}
