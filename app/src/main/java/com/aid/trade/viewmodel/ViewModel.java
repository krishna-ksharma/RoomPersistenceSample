package com.aid.trade.viewmodel;

import android.content.Context;
import android.databinding.BaseObservable;
import android.support.annotation.NonNull;

/**
 * The base view model class.
 */
public abstract class ViewModel extends BaseObservable {
    /**
     * The application context
     */
    private Context mContext;

    /**
     * Constructor.
     *
     * @param context The application context
     */
    public ViewModel(@NonNull Context context) {
        mContext = context;
    }

    /**
     * Gets the context.
     *
     * @return The context
     */
    public Context getContext() {
        return mContext;
    }
}
