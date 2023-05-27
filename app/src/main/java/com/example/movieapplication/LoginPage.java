package com.example.movieapplication;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginPage extends AppCompatActivity {

    ImageView googleBtn;
    private GoogleSignInOptions gso;
    private GoogleSignInClient gsc;

    public static final String SHARED_PREFS = "shared_prefs";
    public static final String EMAIL_KEY = "email_key";
    public static final String PASSWORD_KEY = "password_key";

    SharedPreferences sharedpreferences;

    EditText txtUsername;
    EditText txtPassword;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        txtUsername = (EditText) findViewById(R.id.username);
        txtPassword = (EditText) findViewById(R.id.password);
        btnLogin = (Button) findViewById(R.id.btnlogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                btnLogin.setEnabled(false);


                AndroidNetworking.post("https://mediadwi.com/api/latihan/login")
                        .addBodyParameter("username", username)
                        .addBodyParameter("password", password)
                        .setPriority(Priority.MEDIUM)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.d("sukses login", "onResponse: " + response.toString());

                                try {
                                    boolean status = response.getBoolean("status");
                                    String message = response.getString("message");

                                    if (status) {
                                        Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();

                                        // atau silahkan buat dialog
                                        sharedpreferences = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                                        SharedPreferences.Editor editor = sharedpreferences.edit();
                                        editor.putString(EMAIL_KEY, username.toString());
                                        editor.putString(PASSWORD_KEY, "");

                                        // to save our data with key and value.
                                        editor.apply();

                                        startActivity(new Intent(LoginPage.this, HomePage.class));
                                        finish();
                                    } else {
                                        Toast.makeText(LoginPage.this, message, Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                mProgressBar.setVisibility(View.GONE);
                                btnLogin.setEnabled(true);
                            }

                            @Override
                            public void onError(ANError anError) {

                            }
                        });
            }
        });

        googleBtn = findViewById(R.id.googleButton);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("247877701147-bkmeqsnk05g77hp937bn5nl0ku96d5s3.apps.googleusercontent.com")
                .requestEmail()
                .build();
        gsc = GoogleSignIn.getClient(this, gso);

        googleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    void signIn() {
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                if (account != null) {
                    navigateToSecondActivity();
                } else {
                    Toast.makeText(getApplicationContext(), "Sign In Failed", Toast.LENGTH_SHORT).show();
                }
            } catch (ApiException e) {
                int statusCode = e.getStatusCode();
                Log.e("LoginActivity", "Google Sign in Failed", e);
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

        public void navigateToSecondActivity(){
            finish();
            Intent intent = new Intent(LoginPage.this, HomePage.class);
            startActivity(intent);
        }
    }