package com.softreare.gm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;



public class LoginActivity extends Activity implements View.OnClickListener {



    private Button login,request;
    private EditText id,pass,showpassbtn;
    private String cid,cpass, createIDForAccount, createPassForAccount;
    private SharedPreferences idsh,idForAccount;
    private SharedPreferences passsh,passForAccount;
    private String sid,spass;
    private CheckBox checkBox;
    private int c=0;

    private String LoginModeCheck,TableName;


    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);



        login=findViewById(R.id.login);
        login.setOnClickListener(this);

        request=findViewById(R.id.request);
        request.setOnClickListener(this);

        id=findViewById(R.id.id);
        pass=findViewById(R.id.pass);
        checkBox =(CheckBox)findViewById(R.id.checkBox);

        idsh=getSharedPreferences("id",MODE_PRIVATE);
        passsh=getSharedPreferences("pass",MODE_PRIVATE);
        idForAccount=getSharedPreferences("idForAccount",MODE_PRIVATE);
        passForAccount=getSharedPreferences("passForAccount",MODE_PRIVATE);




        createIDForAccount = idForAccount.getString("idForAccount","12345");
        createPassForAccount =passForAccount.getString("passForAccount","12345");






        sid=idsh.getString("id","");
        spass=passsh.getString("pass","");
        id.setText(sid+"");
        pass.setText(spass+"");
        Log.i("siid","sid is "+sid);




        CheckBox showPassword = (CheckBox) findViewById(R.id.showPass);
        showPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    pass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    // showPassword.setBackgroundColor(Color.parseColor("#000"));


                }
                else
                {
                    pass.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });


    }
    @Override
    public void onClick( View v) {


        switch (v.getId())
        {


            case R.id.login:


                cid=id.getText().toString();
                cpass=pass.getText().toString();
                if(!TextUtils.isEmpty(cid) &&!TextUtils.isEmpty(cpass))
                {
                    Log.i("usId",""+cid);
                    // findViewById(R.id.loginMenu).setVisibility(View.GONE);

//                    idsh.edit().putString("savedid",cid).commit();
//                    passsh.edit().putString("savedpass",cpass).commit();



                    if(checkBox.isChecked())
                    {


                        SharedPreferences.Editor editID=idsh.edit();
                        editID.putString("id",cid);
                        editID.apply();
                        editID.commit();

                        SharedPreferences.Editor editpass=passsh.edit();
                        editpass.putString("pass",cpass);
                        editpass.apply();
                        editpass.commit();

                        Toast.makeText(getApplicationContext(),"Password Saved!!!",Toast.LENGTH_LONG).show();


                    }

                    if(createIDForAccount.equals(cid) && createPassForAccount.equals(cpass))
                    {
                        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        Toast.makeText(LoginActivity.this,"Successfully Login!!!",Toast.LENGTH_LONG).show();
                        finish();

                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Wrong Password!!!",Toast.LENGTH_LONG).show();
                    }

                }
                else
                {
                    c++;
                    if(TextUtils.isEmpty(cid) &&TextUtils.isEmpty(cpass)){
                        Toast.makeText(getApplicationContext(),"ID and Password Missing!!!",Toast.LENGTH_LONG).show();
                    }
                    else if(TextUtils.isEmpty(cid)){
                        Toast.makeText(getApplicationContext(),"ID Missing!!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password Missing!!!",Toast.LENGTH_LONG).show();
                    }
                    //Toast.makeText(getApplicationContext(),"Incorrect ID or Password!!!",Toast.LENGTH_LONG).show();
                }


                break;
            case R.id.request:

                //Toast.makeText(getApplicationContext(),"Account Created",Toast.LENGTH_LONG).show();

                cid=id.getText().toString();
                cpass=pass.getText().toString();
                Log.i("nid",""+cid);
                Log.i("nidPass",cpass);

                if(!TextUtils.isEmpty(cid) &&!TextUtils.isEmpty(cpass))
                {

                    SharedPreferences.Editor editID=idForAccount.edit();
                    editID.putString("idForAccount", cid);
                    editID.apply();
                    editID.commit();

                    SharedPreferences.Editor editpass=passForAccount.edit();
                    editpass.putString("passForAccount", cpass);
                    editpass.apply();
                    editpass.commit();

                    createIDForAccount = idForAccount.getString("idForAccount","1103047");
                    createPassForAccount =passForAccount.getString("passForAccount","12345");

                    if(checkBox.isChecked())
                    {


                        SharedPreferences.Editor editNewID=idsh.edit();
                        editNewID.putString("id",cid);
                        editNewID.apply();
                        editNewID.commit();

                        SharedPreferences.Editor editNewpass=passsh.edit();
                        editNewpass.putString("pass",cpass);
                        editNewpass.apply();
                        editNewpass.commit();

                        Toast.makeText(getApplicationContext(),"Password Saved!!!",Toast.LENGTH_LONG).show();


                    }



//
                    Toast.makeText(LoginActivity.this,"Added Your Account",Toast.LENGTH_SHORT).show();
                    // SharedPreferences.Editor passSp=getSharedPreferences(cpass,MODE_PRIVATE).edit();
                }
                else
                {
                    if(TextUtils.isEmpty(cid) &&TextUtils.isEmpty(cpass)){
                        Toast.makeText(getApplicationContext(),"ID and Password Missing!!!",Toast.LENGTH_LONG).show();
                    }
                    else if(TextUtils.isEmpty(cid)){
                        Toast.makeText(getApplicationContext(),"ID Missing!!!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"Password Missing!!!",Toast.LENGTH_LONG).show();
                    }
                }

                break;


        }







    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

    public String getUserName(){
        return  id.getText().toString();

    }

}
