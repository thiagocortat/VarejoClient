package br.com.devianto.anjo.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.devianto.anjo.MainActivity;
import br.com.devianto.anjo.R;
import br.com.devianto.anjo.repository.RestClient;
import br.com.devianto.anjo.restmodel.models.User;
import br.com.thiagocortat.mylibrary.activities.BaseActivity;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class LoginActivity extends BaseActivity {

    protected EditText usernameEditText;
    protected EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        usernameEditText = (EditText)findViewById(R.id.email);
        passwordEditText = (EditText)findViewById(R.id.password);
    }

    @Override
    public void onStart() {
        super.onStart();

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    public void loginUser(View view) {
        final String username = usernameEditText.getText().toString();
        final String password = passwordEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(LoginActivity.this,"Infome usário e senha", Toast.LENGTH_LONG).show();
        }
        else {
            showProgress();
            RestClient.setEmailAccount(username);
            RestClient.setPasswordAccount(password);

            RestClient client = new RestClient();
            client.getApiService().login(new Callback<User>() {
                @Override
                public void success(User user, Response response) {
                    user.setEmail(username);
                    user.setPassword(password);
                    User.saveCurrentUser(user);

                    Intent intent = new Intent(LoginActivity.this, PagamentoActivity.class);
                    startActivity(intent);
                    finish();

//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
                }

                @Override
                public void failure(RetrofitError error) {
                    Toast.makeText(LoginActivity.this,"ERRO: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    hideProgress();
                }
            });
        }
    }

//    public void loginUser(View view) {
//        String username = usernameEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//
//        if (username.isEmpty() || password.isEmpty()) {
//            Toast.makeText(LoginActivity.this,"Infome usário e senha", Toast.LENGTH_LONG).show();
//        }
//        else {
//            showProgress();
//            ParseUser.logInInBackground(username, password, new LogInCallback() {
//                @Override
//                public void done(ParseUser user, ParseException e) {
//                if (e == null) { // Success!
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                    startActivity(intent);
//                } else {//Fail
//                    Toast.makeText(LoginActivity.this,"ERRO!" + e.getMessage(), Toast.LENGTH_LONG).show();
//                }
//
//                hideProgress();
//                }
//            });
//        }
//    }

    public void signUpUser(View view) {
        Intent intent = new Intent(LoginActivity.this, ClienteActivity.class);
        startActivity(intent);
    }
}
