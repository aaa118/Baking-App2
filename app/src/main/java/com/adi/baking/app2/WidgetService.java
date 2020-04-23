package com.adi.baking.app2;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Parcelable;
import android.util.Log;

import androidx.annotation.Nullable;

import com.adi.baking.app2.model.Ingredient;
import com.adi.baking.app2.model.RecipeName;

import java.util.ArrayList;
import java.util.List;

import static com.adi.baking.app2.adapters.RecipeRecyclerViewAdapter.ITEM_ID;
import static com.adi.baking.app2.adapters.RecipeRecyclerViewAdapter.ITEM_ID_PREFS;

public class WidgetService extends IntentService {
    private static final String ACTION_UPDATE_WIDGETS = "update_widgets";
    private static final String TAG = "AA_WIDGET";
    public static final String RECIPES_LIST = "recipesList";


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
    public static void startActionUpdateWidgets(Context context, ArrayList<Ingredient> recipesList) {
        Intent intent = new Intent(context, WidgetService.class);
        intent.setAction(ACTION_UPDATE_WIDGETS);
        intent.putParcelableArrayListExtra(RECIPES_LIST, recipesList);
        context.startService(intent);
    }

    /**
     * Starts this service to perform  action with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    public static void startActionUpdateWidgetsWithId(Context context, int position) {
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            ArrayList<Ingredient> recipeNameArrayList = intent.getParcelableArrayListExtra(RECIPES_LIST);

            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(this);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(this, IngredientsList.class));
            //Trigger data update to handle the GridView widgets and force a data refresh
            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds, R.id.list_view);
            //Now update all widgets
            Log.i(TAG, "onHandleIntent: All  "+recipeNameArrayList);
            for (int appWidgetId : appWidgetIds) {
                IngredientsList.updateAppWidget(this, appWidgetManager, appWidgetId, recipeNameArrayList );
            }
        }
    }
}
