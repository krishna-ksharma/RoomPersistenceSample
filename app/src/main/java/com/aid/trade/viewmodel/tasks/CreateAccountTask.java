package com.aid.trade.viewmodel.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.aid.trade.db.AppDatabase;
import com.aid.trade.model.User;
import com.aid.trade.dao.UserDao;

/**
 * Task for saving user in a background thread.
 */
public class CreateAccountTask extends AsyncTask<Void, Void, Void> {
    /**
     * Current application context
     */
    private final Context context;

    /**
     * TRUE if account already exist.
     */
    private boolean accountAlreadyExist;

    /**
     * The user
     */
    private final User user;

    /**
     * Callback to be notified when the save is finished
     */
    private final CreateAccountCallback callback;

    /**
     * Creates a async task to save a user.
     *
     * @param context  current application context
     * @param user     the user has to be saved
     * @param callback the callback that will be invoked when the save
     *                 finishes
     */
    public CreateAccountTask(Context context, User user, CreateAccountCallback callback) {
        this.user = user;
        this.callback = callback;
        this.context = context;
        this.accountAlreadyExist = false;
    }

    @Override
    protected Void doInBackground(Void... params) {
        UserDao dao = AppDatabase.getAppDatabase(context).userDao();
        if (dao.findByEmail(user.getEmail()) == null) {
            AppDatabase.getAppDatabase(context).userDao().insertAll(user);
        } else {
            accountAlreadyExist = true;
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void uri) {
        if (callback != null) {
            if (accountAlreadyExist) {
                callback.onEmailConflict();
            } else {
                callback.onAccountCreated(user);
            }
        }
    }

    /**
     * Callback interface for client to implement.
     */
    public interface CreateAccountCallback {
        /**
         * Called on account created.
         *
         * @param user The user that was saved into persistence storage
         */
        void onAccountCreated(User user);

        /**
         * Called if found email is already taken while creation of new account.
         */
        void onEmailConflict();
    }


}
