package com.adi.baking.app2;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.util.ArrayList;

import static com.adi.baking.app2.IngredientsList.WIDGET_LIST;

public class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {

    public static final String RECIPE_NAME = "recipeName";
    public static final String RECIPE = "recipe";
    private static final String TAG = "AA_ListViewFactory";

    private Context ctxt;
    private ArrayList<String> recipeNameList;


    public ListViewFactory(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
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
        return (recipeNameList.size());
    }

    @Override
    public RemoteViews getViewAt(int position) {
        RemoteViews row = new RemoteViews(ctxt.getPackageName(),
                R.layout.row);

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
    }
}
