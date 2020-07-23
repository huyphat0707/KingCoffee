package com.example.kingcoffee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kingcoffee.Main2Activity;
import com.example.kingcoffee.R;
import com.example.kingcoffee.databaseDAO.NguoiDungDAO;
import com.facebook.FacebookSdk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginActivity extends AppCompatActivity {
    EditText eduser, edpass;
    Button btnLogin, btnsign,btnface;
    CheckBox chkRememberPass;
    String strUser, strPass;
    NguoiDungDAO nguoiDungDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        eduser = (EditText) findViewById(R.id.edUserName);
        edpass = (EditText) findViewById(R.id.edtpassword);
        btnLogin = (Button) findViewById(R.id.buttonlogin);
        btnface = findViewById(R.id.id_login);
        btnface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, Main2Activity.class);
                startActivity(i);
            }
        });
        btnsign = findViewById(R.id.buttonsign);
        chkRememberPass = (CheckBox) findViewById(R.id.checkpass);
        nguoiDungDAO = new NguoiDungDAO(LoginActivity.this);
    }
    public void rememberUser(String u, String pass, boolean status){
        SharedPreferences pref =    getSharedPreferences("USER_FILE", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if (!status){
            editor.clear();
        } else {
            editor.putString("USERNAME", u);
            editor.putString("PASSWORD", pass);
//            editor.putBoolean("REMEMBER", status);
        }

        editor.commit();
    }

    public void btnlogin(View view) {
                strUser = eduser.getText().toString();
                strPass = edpass.getText().toString();
                boolean checkLG = nguoiDungDAO.Luu(strUser, strPass);
                if (strUser.isEmpty() || strPass.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Vui lòng điền đầy đủ thông tin !", Toast.LENGTH_LONG).show();
                }else {
                    if(checkLG == true ){
                        rememberUser(strUser, strPass, chkRememberPass.isChecked());
                        Toast.makeText(getApplicationContext(), "Đăng nhập thành công", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                    else if (strUser.equalsIgnoreCase("admin")&&strPass.equalsIgnoreCase("admin")){
                        Intent intent = new Intent(LoginActivity.this, Main2Activity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_LONG).show();
                    }
                }

    }

    public void sign(View view) {
        Intent intent = new Intent(LoginActivity.this,UserActivity.class);
        startActivity(intent);
    }
}
