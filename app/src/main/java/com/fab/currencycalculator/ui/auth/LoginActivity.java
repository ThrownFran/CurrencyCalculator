package com.fab.currencycalculator.ui.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.main.MainActivity;
import com.fab.currencycalculator.ui.base.BaseActivity;

import javax.inject.Inject;

import butterknife.BindView;

public class LoginActivity extends BaseActivity implements LoginContract.View {

    @BindView(R.id.login_edit_username)
    EditText editUsername;
    @BindView(R.id.login_edit_password)
    EditText editPassword;
    @BindView(R.id.login_button_signin)
    Button buttonLogin;

    @Inject
    LoginContract.Presenter presenter;

    @Override
    public int getLayout () {
        return R.layout.activity_login;
    }

    @Override
    public void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupEditListeners();
        setupButtonListener();
        hideActionBar();
    }

    private void hideActionBar () {
        getSupportActionBar().hide();
    }

    @Override
    protected void injectDependencies () {
        BaseApplication.getInstance().getAppComponent()
                .plusLogin()
                .create(this)
                .inject(this);
    }

    private void setupButtonListener () {
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                clickLogin();
            }
        });
    }

    private void clickLogin () {
        String username = null;
        if(editUsername.getText() != null){
            username = editUsername.getText().toString();
        }

        String password = null;
        if(editPassword.getText() != null){
            password = editPassword.getText().toString();
        }

        presenter.clickLogin(username,password);
        editUsername.clearFocus();
        editPassword.clearFocus();
    }

    @Override
    public void showUsernameEmptyMessage () {
        editUsername.setError(getString(R.string.login_username_empty));
    }

    @Override
    public void showPasswordEmptyMessage () {
        editPassword.setError(getString(R.string.login_password_empty));
    }

    @Override
    public void showPasswordInvalidLength () {
        editPassword.setError(getString(R.string.login_password_length_invalid));
    }

    @Override
    public void showGenericMessage (String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToHome () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void setupEditListeners () {
        editPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction (TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    clickLogin();
                }
                return false;
            }
        });
    }



}