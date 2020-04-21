package com.example.accountmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;

import static com.example.accountmanagement.ValuePrivate.password;

public class ResetPass extends BaseActivity {
    @BindView(R.id.reset_pass)
    EditText resetpass;

    @BindView(R.id.newpass)
    Button newpass;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_reset_pass;
    }

    @Override
    protected void setupListener() {
        Reset();
    }

    @Override
    protected void populateData() {
        HideTitle();
    }
    private void Reset()
    {
        newpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(password.equals(""))
                {
                    Toast.makeText(ResetPass.this, "không được để trống ô newpass", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pass",resetpass.getText().toString());
                    editor.commit();

                    Toast.makeText(ResetPass.this,"successfully",Toast.LENGTH_LONG);
                    startActivity(new Intent(ResetPass.this,MainActivity.class));
                }
            }
        });
    }
}
