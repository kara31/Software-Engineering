package comp3250.hideitlogin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/* To Do List
*  Create shared preference for Welcome screen
*
*/
public class MainActivity extends Activity {
    private EditText  username=null;
    private EditText password=null;
    private TextView attemptNum;
  //  private TextView attempts;
    private Button login;
    private Button signIn;
    private Integer count=3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username=(EditText)findViewById(R.id.u_name);
        password=(EditText)findViewById(R.id.pass);
        login=(Button)findViewById(R.id.login_button);
        attemptNum=(TextView)findViewById(R.id.attemptNum);
        signIn=(Button)findViewById(R.id.signUpButton);
      //  attempts=(TextView)findViewById(R.id.attempts); // used to change colour of warning on last login attempt

        // create attempts on page rather than as  toast
        attemptNum.setText(Integer.toString(count));
        addListenerOnButton();
    }

//    public void setLogin(Button login) {
//        this.login = login;
//    }

    public void login(View view){
        // store valid usernames in list
        if(username.getText().toString().equals("admin") &&   password.getText().toString().equals("admin")) {
            Toast.makeText(getApplicationContext(), "Redirecting...",
                    Toast.LENGTH_SHORT).show();

            // will need to send data to next activity for a welcome acknowledgement

        }else{
            Toast.makeText(getApplicationContext(), "Invalid username/password",
                    Toast.LENGTH_SHORT).show();
                    count--;
                    attemptNum.setText(Integer.toString(count));
                    if(count==1) {
                        attemptNum.setBackgroundColor(Color.RED);
//                        attemptNum.setText(Color.BLUE);

                    }else
                    if(count==2)
                        attemptNum.setBackgroundColor(Color.GRAY);



                    if(count<1){ // number of attempts finished
                        login.setEnabled(false);
                        Toast.makeText(getApplicationContext(), "No more attempts available",
                                Toast.LENGTH_SHORT).show();


                    }
        }

    }

    public void signUp(){
        Toast.makeText(getApplicationContext(), "Redirecting...",
                Toast.LENGTH_SHORT).show();

    }


    public void addListenerOnButton() {

        final Context context = this;

       // signIn=(Button)findViewById(R.id.signUpButton);

        signIn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, Register.class);
                startActivity(intent);

            }

        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
