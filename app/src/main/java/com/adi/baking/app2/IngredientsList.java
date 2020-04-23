package com.adi.baking.app2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.widget.RemoteViews;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;

import java.util.ArrayList;
import java.util.List;

import static com.adi.baking.app2.ItemDetailFragment.ARG_ITEM_ID;

/**
 * Implementation of App Widget functionality.
 */
public class IngredientsList extends AppWidgetProvider {

    private static final String TAG = "AA_IngredientsList";
    public static final String WIDGET_LIST = "widgetList";
    public static final String ARG_ITEM_ID_LIST = "recipeList";

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

