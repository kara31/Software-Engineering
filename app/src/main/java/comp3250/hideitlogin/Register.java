package comp3250.hideitlogin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;

import static android.widget.Toast.LENGTH_SHORT;

/* To Do List */

// store data here to database
//check password strength
// encrypt password


public class Register extends ActionBarActivity {
    private EditText fname;
    private EditText uname;
    private EditText email;
    private EditText phone;
    private EditText pass;
    private EditText vpass;
    private EditText secureAns;
    private Button register;

    private String hashPassword;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fname=(EditText)findViewById(R.id.fullname);
        uname=(EditText)findViewById(R.id.u_name);
        email=(EditText)findViewById(R.id.email);
        phone=(EditText)findViewById(R.id.phone);
        pass=(EditText) findViewById(R.id.pass);
        vpass=(EditText)findViewById(R.id.pass_verify);
        secureAns=(EditText)findViewById(R.id.securityQuestionAnswer);
        register=(Button)findViewById(R.id.signUpButton);


    }

    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            return md5;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5", e.getLocalizedMessage());
            return null;
        }
    }

    public void register(View view){
        // perform necessary checks on fields => all fields must be filled out excluding phone or security question
        if(fname.getText().toString().isEmpty() || uname.getText().toString().isEmpty() || email.getText().toString().isEmpty()
                || pass.getText().toString().isEmpty() || vpass.getText().toString().isEmpty() ){
            Toast.makeText(getApplicationContext(),"Missing field information",Toast.LENGTH_LONG).show();

        }
        else { // all fields filled out

                if ( !isEmailAddress(email.getText().toString())  ) {
                    Toast.makeText(getApplicationContext(), "Invalid email address", LENGTH_SHORT).show();

                } else if (!pass.getText().toString().equals(vpass.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Password does not match", LENGTH_SHORT).show();

                } else { // store all data
                      //  pass.getText().toString()=pass.getText().toString();

                    hashPassword=getMd5Hash(pass.getText().toString());


                    Toast.makeText(getApplicationContext(), "Registration  successful "+hashPassword, LENGTH_SHORT).show();

                    //registration successful divert towards signIn

                    // => already added listener on button on create
                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);

                }
        }
    }

    // is email of valid format
    public static boolean isEmailAddress(String address){
        if (TextUtils.isEmpty(address)) {
            return false;
        }
       // String s=extractAddrSpec(address); // does not work
        Matcher match= Patterns.EMAIL_ADDRESS.matcher(address);
        return match.matches();
    }

    //is phone of valid format
    public static boolean isPhone(String phoneNum){
        if (TextUtils.isEmpty(phoneNum)) {
            return false;
        }

        Matcher match= Patterns.PHONE.matcher(phoneNum);
        return match.matches();
    }


//    public void addListenerOnButton() {
//
//        final Context context = this;
//
//        register.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//
//                Intent intent = new Intent(context, MainActivity.class);
//                startActivity(intent);
//
//            }
//
//        });
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
