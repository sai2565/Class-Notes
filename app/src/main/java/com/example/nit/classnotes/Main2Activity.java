package com.example.nit.classnotes;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;

public class Main2Activity extends AppCompatActivity {
    public static String APP_ID= "B65B1F1E-6196-1D5A-FFB5-FE042EC7CF00";
    public static String SECRET_KEY = "A51DBC04-E0E0-C86D-FF30-F22B2CBE9B00";
    public static String VERSION = "v1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);


        Button registerButton = (Button) findViewById( R.id.registerButton );

        View.OnClickListener registerButtonClickListener = createRegisterButtonClickListener();

        registerButton.setOnClickListener(registerButtonClickListener);
    }
    public View.OnClickListener createRegisterButtonClickListener()
    {
        return new View.OnClickListener()
        {
            @Override
            public void onClick( View v )
            {
                EditText nameField = (EditText) findViewById( R.id.nameField );
                EditText emailField = (EditText) findViewById( R.id.emailField );
                EditText passwordField = (EditText) findViewById( R.id.passwordField );
                EditText passwordConfirmField = (EditText) findViewById( R.id.passwordConfirmField );

                CharSequence name = nameField.getText();
                CharSequence email = emailField.getText();
                CharSequence password = passwordField.getText();
                CharSequence passwordConfirmation = passwordConfirmField.getText();

                if( isRegistrationValuesValid( name, email, password, passwordConfirmation ) )
                {
                    LoadingCallback<BackendlessUser> registrationCallback = createRegistrationCallback();

                    registrationCallback.showLoading();
                    registerUser( name.toString(), email.toString(), password.toString(), registrationCallback );
                }
            }
        };
    }
    public LoadingCallback<BackendlessUser> createRegistrationCallback()
    {
        return new LoadingCallback<BackendlessUser>( this, getString( R.string.loading_register ) )
        {
            @Override
            public void handleResponse( BackendlessUser registeredUser )
            {
                super.handleResponse( registeredUser );
                Toast.makeText(Main2Activity.this, String.format(getString(R.string.info_registered), registeredUser.getObjectId()), Toast.LENGTH_LONG).show();
            }
        };
    }
    public boolean isRegistrationValuesValid( CharSequence name, CharSequence email, CharSequence password,
                                              CharSequence passwordConfirm )
    {
        return Validator.isNameValid( this, name )
                && Validator.isEmailValid( this, email )
                && Validator.isPasswordValid( this, password )
                && isPasswordsMatch( password, passwordConfirm );
    }
    public boolean isPasswordsMatch( CharSequence password, CharSequence passwordConfirm )
    {
        if( !TextUtils.equals(password, passwordConfirm) )
        {
            Toast.makeText( this, getString( R.string.warning_passwords_do_not_match ), Toast.LENGTH_LONG ).show();
            return false;
        }

        return true;
    }
    public void registerUser( String name, String email, String password,
                              AsyncCallback<BackendlessUser> registrationCallback )
    {
        BackendlessUser user = new BackendlessUser();
        user.setEmail( email );
        user.setPassword( password );
        user.setProperty( "name", name );

        //Backendless handles password hashing by itself, so we don't need to send hash instead of plain text
        Backendless.UserService.register( user, registrationCallback );
    }
}
