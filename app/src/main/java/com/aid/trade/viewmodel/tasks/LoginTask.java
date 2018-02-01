package com.aid.trade.viewmodel.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.aid.trade.db.AppDatabase;
import com.aid.trade.model.User;

/**
 * Task for user login in a background thread.
 */
public class LoginTask extends AsyncTask<Void, Void, Boolean> {

    /**
     * The User.
     */
    private final User user;

    /**
     * Current application context
     */
    private final Context context;

    /**
     * Callback to be notified when the login is finished
     */
    private LoginCallback callback;

    /**
     * Callback interface for client to implement.
     */
    public interface LoginCallback {
        /**
         * Called on login success.
         */
        void onSuccess();

        /**
         * Called on login failed
         */
        void onFailed();
    }

    /**
     * Creates a async task to login a user.
     *
     * @param context  current application context
     * @param user     the user
     * @param callback the callback that will be invoked when the login finishes
     */
    public LoginTask(Context context, User user, LoginCallback callback) {
        this.context = context;
        this.user = user;
        this.callback = callback;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        User dUser = AppDatabase.getAppDatabase(context).userDao().findByEmail(user.getEmail(), user.getPassword());
        return dUser != null;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (callback != null) {
            if (success) {
                callback.onSuccess();
            } else {
                callback.onFailed();
            }
        }
    }
}
