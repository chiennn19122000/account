package com.example.accountmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import butterknife.BindView;

import static com.example.accountmanagement.ValuePrivate.passcauhoibaomat;

public class ForgetPass extends BaseActivity {
    @BindView(R.id.cauhoi)
    TextView cauhoi;

    @BindView(R.id.dapan)
    EditText dapan;

    @BindView(R.id.conTinue)
    Button Continue;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_forget_pass;
    }

    @Override
    protected void setupListener() {
        forget();
    }

    @Override
    protected void populateData() {
        HideTitle();
    }

    private void forget()
    {
        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        cauhoi.setText(preferences.getString("cauhoibaomat",""));

        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dapan.getText().toString().equals(preferences.getString("passcauhoibaomat","")) || dapan.getText().toString().equals(passcauhoibaomat))
                {
                    startActivity(new Intent(ForgetPass.this,ResetPass.class));
                }
                else
                {
                    Toast.makeText(ForgetPass.this,"không phải câu trả lời !!!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
