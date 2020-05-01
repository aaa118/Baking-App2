package com.adi.baking.app2.views.widget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

import com.adi.baking.app2.R;
import com.adi.baking.app2.model.Ingredient;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsList extends AppWidgetProvider {

    private static final String TAG = "AA_IngredientsList";
    public static final String WIDGET_LIST = "widgetList";
    public static final String ARG_ITEM_ID_LIST = "recipeList";
    public static final String INGREDIENTS_LIST = "IngredientsList";
    public static final String INGREDIENTS_LIST_KEY = "ingredientsListKey";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<Ingredient> recipeNameArrayList) {

        // Construct the RemoteViews object
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_list);

        Intent svcIntent = new Intent(context, ListViewService.class);
        ArrayList<String> ingredientsNamesList = new ArrayList<>();
        for (Ingredient ingredient : recipeNameArrayList) {
            ingredientsNamesList.add(ingredient.getIngredient());
        }
        svcIntent.putStringArrayListExtra(WIDGET_LIST, ingredientsNamesList);
        Log.i(TAG, "intentToPass: "+ingredientsNamesList);
        Set<String> set = new HashSet<>();
        for (String setElement : ingredientsNamesList) {
            set.add(setElement);
        }
        SharedPreferences sharedPref = context.getSharedPreferences(INGREDIENTS_LIST, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(INGREDIENTS_LIST_KEY, set);
        editor.apply();
        views.setRemoteAdapter(R.id.list_view, svcIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

