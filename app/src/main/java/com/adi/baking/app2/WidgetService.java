package com.adi.baking.app2;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;

import java.util.ArrayList;
import java.util.List;

public class WidgetService extends IntentService {
    private static final String ACTION_UPDATE_WIDGETS = "update_widgets";
    private static final String TAG = "AA_WIDGET";
    public static final String PLANT_NAME = "plantName";
    public static final String RECIPES_LIST = "recipesList";
    ArrayList<String> namesList = new ArrayList<>();


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     */
    public WidgetService() {
        super("WIdget");
    }

    /**
     * Starts this service to perform  action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateWidgets(Context context, String test, List<RecipeName> recipesList) {
        Intent intent = new Intent(context, WidgetService.class);
//        Log.i(TAG, "startActionUpdateWidgets: "+test);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        intent.putExtra(PLANT_NAME, test);
        intent.putParcelableArrayListExtra(RECIPES_LIST, (ArrayList<? extends Parcelable>) recipesList);
        context.startService(intent);
    }

    /**
     * Starts this service to perform  action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateWidgetsWithId(Context context, int position) {
//        Intent intent = new Intent(context, WidgetService.class);
//        intent.setAction(ACTION_UPDATE_WIDGETS);
//        Log.i("AA_", "startActionUpdateWidgetsWithId: "+position);
//        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String name = intent.getStringExtra(PLANT_NAME);
            ArrayList<RecipeName> recipeNameArrayList = intent.getParcelableArrayListExtra(RECIPES_LIST);
            Log.i(TAG, "onHandleIntent: " + recipeNameArrayList);
            namesList.add(name);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsList.class));
            //Trigger data update to handle the GridView widgets and force a data refresh
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_view);
            //Now update all widgets
            for (int appWidgetId : appWidgetIds) {
                IngredientsList.updateAppWidget(this, appWidgetManager, appWidgetId, recipeNameArrayList);
            }
        }
    }
}
