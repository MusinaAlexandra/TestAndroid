package com.example.testandoid;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // Получаем объект Intent, который запустил данную activity
        Intent intent = getIntent();
        // Получаем сообщение из объекта intent
        String message = intent.getStringExtra("message");
        // Получаем TextView по его id
        TextView messageText = (TextView) findViewById(R.id.messageText);
        // устанавливаем текст для TextView
        messageText.setText(message);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    /* alternative for menu creation
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add("Настройки");
        menu.add("Открыть");
        menu.add("Сохранить");
        return true;
    }
    */

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        TextView headerView = (TextView) findViewById(R.id.selectedMenuItem);
        switch(id){
            case R.id.action_settings :
                // go to jokeAPI
                Intent intent = new Intent(this, APIactivity.class);
                startActivity(intent);
                return true;
            case R.id.nothing_settings:
                headerView.setText("Nothing");
                return true;
            case R.id.logout_settings:
                // go to main activity
                Intent intent = new Intent(this,  MainActivity.class);
                startActivity(intent);
                return true;
        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }
    /* view title of menu item
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        TextView headerView = (TextView) findViewById(R.id.selectedMenuItem);
        headerView.setText(title);

        return super.onOptionsItemSelected(item);
    }
    */
}
