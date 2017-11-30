package com.example.aldi.androidkopiku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.aldi.androidkopiku.Common.Common;
import com.example.aldi.androidkopiku.model.Category;
import com.example.aldi.androidkopiku.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignIn extends AppCompatActivity {

    private MaterialEditText SignUpNomorHp,SignUpPassword;
    private Button buttonMasuk,buttonDaftar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);



        buttonMasuk = (Button)findViewById(R.id.btnSignIn);
        buttonDaftar = (Button)findViewById(R.id.btnSignUp);
        SignUpNomorHp = (MaterialEditText)findViewById(R.id.SignUpNomorHp);
        SignUpPassword =(MaterialEditText)findViewById(R.id.SignUpPassword);

        //Init Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = db.getReference("User");

        buttonDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent daftar = new Intent(SignIn.this,SignUp.class);
                startActivity(daftar);
            }
        });

        buttonMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog dialog = new ProgressDialog(SignIn.this);
                dialog.setMessage("Sabar ya...");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //Check If user doesn't exist
                        if (dataSnapshot.child(SignUpNomorHp.getText().toString()).exists())
                        {
                            //Get User Information
                            dialog.dismiss();
                            User user = dataSnapshot.child(SignUpNomorHp.getText().toString()).getValue(User.class);
                            if (user.getPassword().equals(SignUpPassword.getText().toString()))
                            {
                                Intent home_activity = new Intent(SignIn.this, Home.class);
                                Common.CurrentUser = user;
                                startActivity(home_activity);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(SignIn.this, "Password salah !", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                        else
                        {
                            dialog.dismiss();
                            Toast.makeText(SignIn.this, "Belum terdaftar !", Toast.LENGTH_SHORT).show();
                            return;

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
