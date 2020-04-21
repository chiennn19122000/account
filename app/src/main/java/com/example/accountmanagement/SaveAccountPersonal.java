package com.example.accountmanagement;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import butterknife.BindView;

public class SaveAccountPersonal extends BaseActivity {
    @BindView(R.id.spinner_account)
    Spinner spinner;

    @BindView(R.id.user_account)
    EditText user;

    @BindView(R.id.pass_account)
    EditText pass;

    @BindView(R.id.note_account)
    EditText note;

    @BindView(R.id.add_account)
    Button add;

    @BindView(R.id.lv_account)
    ListView lv_account;



    private String filename = "account.txt";
    private String[] account_type = {"Facebook","Google","khác"};
    private ArrayList<String> arrAccount = new ArrayList<>();
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_save_data;
    }

    @Override
    protected void setupListener() {

        setSpinner();
        addAccount();

    }

    @Override
    protected void populateData() {
        settitle();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.changepass :
                changePass();
                break;
            case R.id.changecauhoibaomat :
                changeCauHoiBaoMat();
                break;
            case R.id.changepassbaomat :
                changePassBaoMat();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void changePass() {
        Dialog dialog = new Dialog(SaveAccountPersonal.this);
        dialog.setTitle("Change Pass");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change_pass);

        EditText oldpass = dialog.findViewById(R.id.oldpass);
        EditText updatepass1 = dialog.findViewById(R.id.updatepass1);
        EditText updatepass2 = dialog.findViewById(R.id.updatepass2);
        Button cancel = dialog.findViewById(R.id.cancel);
        Button save = dialog.findViewById(R.id.ok);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
                String checkpass = preferences.getString("pass","");
                if (checkpass.equals(oldpass.getText().toString()) && updatepass1.getText().toString().equals(updatepass2.getText().toString()) && updatepass1.getText().toString().equals("")==false)
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("pass",updatepass1.getText().toString());
                    editor.commit();
                    Toast.makeText(SaveAccountPersonal.this,"đã thay đổi thành công",Toast.LENGTH_LONG).show();
                    dialog.cancel();

                }

                if (checkpass.equals(oldpass.getText().toString())== false)
                {
                    Toast.makeText(SaveAccountPersonal.this,"nhập sai mật khẩu cũ!!!",Toast.LENGTH_LONG).show();
                }
                if (updatepass1.getText().toString().equals(""))
                {
                    Toast.makeText(SaveAccountPersonal.this,"Không được để trống mật khẩu mới",Toast.LENGTH_LONG).show();
                }
                if (updatepass1.getText().toString().equals(updatepass2.getText().toString())== false)
                {
                    Toast.makeText(SaveAccountPersonal.this,"Nhập lại mật khẩu mới",Toast.LENGTH_LONG).show();
                }

            }
        });
        dialog.show();
    }

    private void changeCauHoiBaoMat() {
        Dialog dialog = new Dialog(SaveAccountPersonal.this);
        dialog.setTitle("Change câu hỏi bảo mật");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change_cau_hoi_bao_mat);

        TextView textView = (TextView) dialog.findViewById(R.id.showtext);
        EditText chbm = (EditText) dialog.findViewById(R.id.newcauhoibaomat);
        Button cancel1 = (Button) dialog.findViewById(R.id.cancel1);
        Button save1 = (Button) dialog.findViewById(R.id.ok1);

        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        String s = preferences.getString("cauhoibaomat","");
        textView.setText("Câu hỏi bảo mật hiện tại là: " + s);

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        save1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (chbm.getText().toString().equals(""))
                {
                    Toast.makeText(SaveAccountPersonal.this,"Không được bỏ trống câu hỏi bảo mật",Toast.LENGTH_LONG).show();
                }
                else
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("cauhoibaomat",chbm.getText().toString());
                    editor.commit();
                    Toast.makeText(SaveAccountPersonal.this,"đã thay đổi thành công",Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
            }
        });
        dialog.show();
    }

    private void changePassBaoMat() {
        Dialog dialog = new Dialog(SaveAccountPersonal.this);
        dialog.setTitle("Change đáp án bảo mật");
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_change_pass_bao_mat);

        TextView textView = (TextView) dialog.findViewById(R.id.showtext2);
        EditText pass = (EditText) dialog.findViewById(R.id.passhientai);
        EditText ask = (EditText) dialog.findViewById(R.id.newpassbaomat);
        Button cancel = (Button) dialog.findViewById(R.id.cancel2);
        Button save = (Button) dialog.findViewById(R.id.ok2);

        SharedPreferences preferences = getSharedPreferences("data_login",MODE_PRIVATE);
        String s = preferences.getString("passcauhoibaomat","");
        String checkpass = preferences.getString("pass","");
        textView.setText("câu trả lời bảo mật hiện tại là: "+ s);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkpass.equals(pass.getText().toString()) && ask.getText().toString().equals("")==false)
                {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("passcauhoibaomat",ask.getText().toString());
                    editor.commit();
                    Toast.makeText(SaveAccountPersonal.this,"đã thay đổi thành công",Toast.LENGTH_LONG).show();
                    dialog.cancel();
                }
                if (checkpass.equals(pass.getText().toString())==false )
                {
                    Toast.makeText(SaveAccountPersonal.this,"nhập sai mật khẩu",Toast.LENGTH_LONG).show();

                }

                if (ask.getText().toString().equals(""))
                {
                    Toast.makeText(SaveAccountPersonal.this,"Không được bỏ trống câu trả lời bảo mật",Toast.LENGTH_LONG).show();

                }
            }
        });
        dialog.show();
    }

    private void setSpinner()
    {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,account_type);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(arrayAdapter);
        readData();
    }

    private void addAccount()
    {
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AccountPerson accountPerson = new AccountPerson(spinner.getSelectedItem().toString() + "  " +note.getText().toString(),user.getText().toString(),pass.getText().toString());
                arrAccount.add(accountPerson.toString());
                setLv_account();
                String s = accountPerson + "\n";
                writeData(s);
            }
        });

    }
    private void setLv_account()
    {
        ArrayAdapter adapter= new ArrayAdapter(SaveAccountPersonal.this,android.R.layout.simple_expandable_list_item_1,arrAccount);
        deleteAccount();
        lv_account.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void deleteAccount() {
        lv_account.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                dialogDelete(position);
                return false;
            }
        });
    }

    private void dialogDelete(int position) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(SaveAccountPersonal.this);
        builder.setTitle("You are delete account?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                arrAccount.remove(position);
                setLv_account();
                ghidedata(arrAccount);
            }
        });
        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void writeData(String s)
    {

        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            outputStream.write(s.getBytes());
            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    private void ghidedata(ArrayList<String> arrAccount)
    {
        FileOutputStream outputStream = null;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            int t = arrAccount.size();
            for(int i=0;i<t;i++)
            {
                outputStream.write(arrAccount.get(i).getBytes());
                outputStream.write("\n".getBytes());
            }
            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    private void readData()
    {
        try {
            FileInputStream in = this.openFileInput(filename);

            BufferedReader br= new BufferedReader(new InputStreamReader(in));

            StringBuffer buffer = new StringBuffer();
            String line = null;
            String acc="";
            int i =1;
            while((line= br.readLine())!= null)  {
                buffer.append(line).append("\n");
                if(i%3!=0)
                {
                    acc += line + "\n";
                }
                else {

                    acc += line ;
                    arrAccount.add(acc);
                    acc = "";
                }
                i++;
            }
//            buffer.toString() chính là chuỗi String thu đc toàn bộ từ file
            setLv_account();
        } catch (Exception e) {
            Toast.makeText(this,"Error:"+ e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void settitle()
    {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Menu");
    }
    @Override
    protected void onStop () {

        super.onStop();
        System.exit(0);
    }
}