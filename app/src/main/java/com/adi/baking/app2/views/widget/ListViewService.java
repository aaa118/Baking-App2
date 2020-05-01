package com.adi.baking.app2.views.widget;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.adi.baking.app2.views.widget.ListViewFactory;

public class ListViewService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return(new ListViewFactory(getApplicationContext(),
                intent));
    }
}
