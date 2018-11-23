package com.romelapj.popularmovies.ui;

import android.support.v7.widget.Toolbar;

public interface MainActivityDelegate {

    void setupNavDrawer(Toolbar toolbar);

    void  enableNavDrawer(Boolean enable);
}
