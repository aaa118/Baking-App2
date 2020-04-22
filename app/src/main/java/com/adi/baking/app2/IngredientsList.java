package com.adi.baking.app2;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

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
//    private static final String RECIPE_LIST = "recipeList";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<String> name, ArrayList<RecipeName> recipeNameArrayList) {

        CharSequence widgetText = "TEST";
        // Construct the RemoteViews object
        Log.i(TAG, "updateAppWidget: " + name);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.ingredients_list);
//        views.setTextViewText(R.id.appwidget_text, name.get(0));

        Intent svcIntent = new Intent(context, ListViewService.class);
        //TODO fix parcelable
        svcIntent.putStringArrayListExtra(WIDGET_LIST, name);
//        svcIntent.putParcelableArrayListExtra(ARG_ITEM_ID_LIST, recipeNameArrayList);
        views.setRemoteAdapter(R.id.list_view, svcIntent);


//        Intent clickIntent = new Intent(context, ItemDetailActivity.class);


        Intent clickIntent = new Intent(context, ItemDetailActivity.class);
//                intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, recipe.getId());
//        clickIntent.putParcelableArrayListExtra(ItemDetailFragment.ARG_ITEM_ID, recipeNameArrayList);
        Log.i(TAG, "updateAppWidget: "+recipeNameArrayList);
//        context.startActivity(clickIntent);
        PendingIntent clickPI = PendingIntent
                .getActivity(context, 0,
                        clickIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        views.setPendingIntentTemplate(R.id.list_view, clickPI);
//        if (name.get(1) !=null) {
//            views.setTextViewText(R.id.appwidget_text1, name.get(1));
//        }
//        if (name.get(2) !=null) {
//
//            views.setTextViewText(R.id.appwidget_text2, name.get(2));
//        }
//        if (name.get(3) !=null) {
//
//            views.setTextViewText(R.id.appwidget_text3, name.get(3));
//        }
//        views.setRemoteAdapter();

//        Intent intent = new Intent(context, ItemListActivity.class);
//        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//
//        views.setOnClickPendingIntent(R.id.list_view, pendingIntent);
        // Add the wateringservice click handler
//        Intent wateringIntent = new Intent(context, PlantWateringService.class);
//        wateringIntent.setAction(PlantWateringService.ACTION_WATER_PLANTS);
//        PendingIntent wateringPendingIntent = PendingIntent.getService(context, 0, wateringIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//        views.setOnClickPendingIntent(R.id.widget_water_button, wateringPendingIntent);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

//    public static void updateWidgets(WidgetService widgetService, AppWidgetManager appWidgetManager, String name, int[] appWidgetIds) {
//
//    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
//        for (int appWidgetId : appWidgetIds) {
//            updateAppWidget(context, appWidgetManager, appWidgetId, name);
//        }
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

