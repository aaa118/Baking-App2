package com.adi.baking.app2;


import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.adi.baking.app2.model.RecipeName;

import java.util.ArrayList;
import java.util.List;

import static com.adi.baking.app2.IngredientsList.WIDGET_LIST;

public class ListViewFactory implements RemoteViewsService.RemoteViewsFactory {
    private static final String[] items = {"lorem", "ipsum", "dolor",
            "purus"};

    private Context ctxt;
    private int appWidgetId;
    private ArrayList<String> recipeNameList;

    public ListViewFactory(Context ctxt, Intent intent) {
        this.ctxt = ctxt;
        recipeNameList = intent.getStringArrayListExtra(WIDGET_LIST);
//        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
//                AppWidgetManager.INVALID_APPWIDGET_ID);
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


        Log.i("AA_get", "getViewAt: "+recipeNameList);

//        row.setTextViewText(android.R.id.text1, recipeNameList.get(position).getName());
        row.setTextViewText(android.R.id.text1, recipeNameList.get(position));
//
//        Intent i=new Intent();
//        Bundle extras=new Bundle();
//
////        extras.putString(WidgetProvider.EXTRA_WORD, items[position]);
//        i.putExtras(extras);
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
