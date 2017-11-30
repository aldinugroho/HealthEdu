package com.example.aldi.androidkopiku;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aldi.androidkopiku.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import info.hoang8f.widget.FButton;

public class SignUp extends AppCompatActivity {

    private MaterialEditText SignUpHp,SignUpEmail,SignUpPassword,SignUpName;
    private Button tombolDaftar,tombolMasuk;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        tombolMasuk = (Button) findViewById(R.id.btnSignIn);
        tombolDaftar = (Button)findViewById(R.id.btnSignUp);
        SignUpHp = (MaterialEditText)findViewById(R.id.SignUpHp);
        SignUpEmail =(MaterialEditText)findViewById(R.id.SignUpEmail);
        SignUpPassword = (MaterialEditText)findViewById(R.id.SignUpPassword);
        SignUpName = (MaterialEditText)findViewById(R.id.SignUpName);


        //Init Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = db.getReference("User");

        tombolMasuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent masuk = new Intent(SignUp.this,SignIn.class);
                startActivity(masuk);
            }
        });

        tombolDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog dialog = new ProgressDialog(SignUp.this);
                dialog.setMessage("Sabar ya...");
                dialog.show();

                table_user.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        //check if not null
                        if (dataSnapshot.child(SignUpHp.getText().toString()).exists())
                        {
                            dialog.dismiss();
                            Toast.makeText(SignUp.this,"Email sudah terdaftar!",Toast.LENGTH_SHORT).show();
                            return;
                        } else
                        {
                            dialog.dismiss();
                            //insert data to firebase
                            User user = new User(SignUpEmail.getText().toString(),SignUpName.getText().toString(),SignUpPassword.getText().toString());
                            table_user.child(SignUpHp.getText().toString()).setValue(user);
                            Toast.makeText(SignUp.this, "Sukses !", Toast.LENGTH_SHORT).show();
                            finish();
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
