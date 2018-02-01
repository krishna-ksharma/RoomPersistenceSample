package com.aid.trade.handler;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.ColorRes;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.aid.trade.R;
import com.aid.trade.model.User;
import com.aid.trade.util.Validator;
import com.aid.trade.viewmodel.LoginViewModel;
import com.aid.trade.viewmodel.tasks.CreateAccountTask;
import com.aid.trade.viewmodel.tasks.LoginTask;

/**
 * The LoginViewHandler class, holds the event listeners for the login view.
 */

public class LoginViewHandler {
    /**
     * The login view model.
     */
    private LoginViewModel viewModel;

    /**
     * Constructor.
     *
     * @param viewModel The login view model
     */
    public LoginViewHandler(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * The login click event
     *
     * @return The instance of View.OnClickListener
     */
    public View.OnClickListener onLoginClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoginTask(getContext(), User.createUser(viewModel.getEmail(), viewModel.getPassword()),
                        new LoginTask.LoginCallback() {
                            @Override
                            public void onSuccess() {
                                showAlert(getString(R.string.login_success));
                            }

                            @Override
                            public void onFailed() {
                                showAlert(getString(R.string.login_failed));
                            }
                        }).execute();
            }
        };
    }

    /**
     * The create account click event
     *
     * @return The instance of View.OnClickListener
     */
    public View.OnClickListener onCreateAccountClicked() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Validator.getInstance().validateEmail(viewModel.getEmail())) {
                    showAlert(getString(R.string.email_not_valid));
                } else if (!Validator.getInstance().validatePassword(viewModel.getPassword())) {
                    showAlert(getString(R.string.password_hint));
                } else {
                    new CreateAccountTask(viewModel.getContext(), User.createUser(viewModel.getEmail(), viewModel.getPassword()),
                            new CreateAccountTask.CreateAccountCallback() {
                                @Override
                                public void onAccountCreated(User user) {
                                    showAlert(getString(R.string.account_created));
                                }

                                @Override
                                public void onEmailConflict() {
                                    showAlert(getString(R.string.email_taken));
                                }
                            }).execute();
                }
            }
        };
    }

    /**
     * The email edit text focus change listener
     *
     * @return The instance of View.OnFocusChangeListener
     */
    public View.OnFocusChangeListener emailFocusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                @ColorRes int color = focused ? ContextCompat.getColor(getContext(), R.color.colorWhite60Percent)
                        : ContextCompat.getColor(getContext(), R.color.colorWhite);
                viewModel.setEmailTextColorHint(color);
            }
        };
    }

    /**
     * The password edit text focus change listener
     *
     * @return The instance of View.OnFocusChangeListener
     */
    public View.OnFocusChangeListener passwordFocusChangeListener() {
        return new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean focused) {
                @ColorRes int color = focused ? ContextCompat.getColor(getContext(), R.color.colorWhite60Percent)
                        : ContextCompat.getColor(getContext(), R.color.colorWhite);
                viewModel.setPasswordTextColorHint(color);
            }
        };
    }

    /**
     * Helper method, returns the context.
     */
    private Context getContext() {
        return viewModel.getContext();
    }

    /**
     * Shows alert dialog
     *
     * @param message The message
     */
    private void showAlert(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(getContext()).create();
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    /**
     * Helper method to get the string
     *
     * @param resId The string resource id
     * @return The string
     */
    private String getString(@StringRes int resId) {
        return getContext().getResources().getString(resId);
    }
}
