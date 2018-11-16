package com.romelapj.popularmovies.ui

import android.support.v7.widget.Toolbar

interface MainActivityDelegate {
    fun setupNavDrawer(toolbar: Toolbar)

    fun enableNavDrawer(enable: Boolean)
}