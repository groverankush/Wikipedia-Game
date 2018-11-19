package com.ankushgrover.letswiki.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RemoteViews;

import com.ankushgrover.letswiki.R;
import com.ankushgrover.letswiki.ui.signin.SignInActivity;

/**
 * Implementation of App Widget functionality.
 */
public class ScoreGlobalRankWidget extends AppWidgetProvider {

    static public void updateWidget(Context context, Long score, Long rank) {

        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        int[] ids = manager.getAppWidgetIds(new ComponentName(context, ScoreGlobalRankWidget.class));

        for (int id : ids) {
            updateAppWidget(context, manager, id, score, rank);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, Long score, Long rank) {

        // Construct the RemoteViews object
        //RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.score_global_rank_widget);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.layout_score_glabal_rank_widget);
        PendingIntent mainActivityIntent = PendingIntent.getActivity(context, 0, new Intent(context, SignInActivity.class), 0);

        views.setOnClickPendingIntent(R.id.ll_score_container, mainActivityIntent);

        views.setTextViewText(R.id.tv_score, String.valueOf(score));
        views.setTextViewText(R.id.tv_rank, String.valueOf(rank));
        views.setViewVisibility(R.id.iv_trophy, View.VISIBLE);
        int icon = -1;

        if (rank == null || rank <= -1 || rank > 3)
            views.setViewVisibility(R.id.iv_trophy, View.GONE);
        else if (rank == 1)
            icon = R.drawable.ic_trophy_gold;
        else if (rank == 2)
            icon = R.drawable.ic_trophy_silver;
        else if (rank == 3)
            icon = R.drawable.ic_trophy_bronze;

        views.setImageViewResource(R.id.iv_trophy, icon);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);

    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        /*for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }*/
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

