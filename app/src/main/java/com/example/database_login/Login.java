package com.example.database_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private Button login;
    private Button register;
   // private Button signout;
    private EditText email;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        findAllViewsfromLayout();
        handleLogin();
    }

    private void findAllViewsfromLayout() {
        //message =findViewById(R.id.loginmessage);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        //signout = findViewById(R.id.signout);
    }

    private void handleLogin(){
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser(String.valueOf(email.getText()), String.valueOf(password.getText()));
            }
        });
    }

    private void createNewUser(String email, String password){
       Intent register = new Intent(this, Register.class);
       startActivity(register);
    }

    private void loginUser(String email, String password){
        // TODO: Login with Email and Password on Firebase.
        if (email.length()==0 || password.length()==0){
            Toast.makeText(getApplicationContext(), "Email and password cannot be empty",
                    Toast.LENGTH_LONG).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("MapleLeaf", "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //message.setText("User "+ user.getEmail() + " is now Logged In");
                            //setButtonStatus(true);
                           // gotoRead();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("MapleLeaf", "signInWithEmail:failure", task.getException());
                            Toast.makeText(getApplicationContext(), "Authentication failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                        // ...
                    }
                });
    }

}
