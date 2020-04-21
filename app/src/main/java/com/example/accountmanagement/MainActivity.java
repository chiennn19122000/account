package com.example.accountmanagement;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import butterknife.BindView;

import static com.example.accountmanagement.ValuePrivate.password;

public class MainActivity extends BaseActivity {
    @BindView(R.id.pass_main)
    EditText passmain;

    @BindView(R.id.login)
    Button login;

    @BindView(R.id.forget)
    Button forget;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void setupListener() {

        dialog();
        Login();
        Forget();
    }

    @Override
    protected void populateData() {
        HideTitle();
    }

    private void Login()
    {
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
                if(passmain.getText().toString().equals(preferences.getString("pass","")) || passmain.getText().toString().equals(password))
                {
                    passmain.setText("");
                    startActivity(new Intent(MainActivity.this,SaveAccountPersonal.class));
                }
                else
                {
                    Toast.makeText(MainActivity.this,"sai pass đăng nhập",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void Forget()
    {
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ForgetPass.class));
            }
        });
    }

    private void dialog()
    {
        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        boolean check = preferences.getBoolean("check_dialog", true);

        if(check)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Thông báo");
            builder.setMessage("Pass lần đầu đăng nhập là : 28101997 \nSau khi đăng nhập hãy đổi lại pass, nếu không lần sau đăng nhập vẫn vẫn mặc định là: 28101997" );
            builder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    DataLogin();
                    dialog.dismiss();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }

    private void DataLogin()
    {
        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("pass","28101997");
        editor.putString("cauhoibaomat","chưa có câu hỏi bảo mật");
        editor.putString("passcauhoibaomat","28101997");
        editor.putBoolean("check_dialog",false );
        editor.commit();
    }
}
