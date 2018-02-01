package com.aid.trade.view.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.aid.trade.R;
import com.aid.trade.databinding.ActivityLoginBinding;
import com.aid.trade.handler.LoginViewHandler;
import com.aid.trade.viewmodel.LoginViewModel;

public class ActivityLogin extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginViewModel viewModel = new LoginViewModel(this);
        binding.setViewModel(viewModel);
        binding.setViewHandler(new LoginViewHandler(viewModel));
    }
}
