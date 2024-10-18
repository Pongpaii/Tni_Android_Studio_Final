package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Home;
import com.example.myapplication.R;

public class LoginWallet extends AppCompatActivity {
    EditText etWalletName;
    Button btnCreateWallet;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.myapplication.R.layout.activity_login_wallet);

        etWalletName = findViewById(R.id.etWalletName);
        btnCreateWallet = findViewById(R.id.btnCreateWallet);

        sharedPreferences = getSharedPreferences("WalletApp", MODE_PRIVATE);

        btnCreateWallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String walletName = etWalletName.getText().toString();
                if (!walletName.isEmpty()) {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("wallet_name", walletName);
                    editor.apply();

                    Intent intent = new Intent(LoginWallet.this, Home.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(LoginWallet.this, "Please enter wallet name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
