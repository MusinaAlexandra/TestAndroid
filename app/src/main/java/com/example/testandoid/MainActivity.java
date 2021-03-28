package com.example.testandoid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Метод обработки нажатия на кнопку
    public void sendMessage(View view) {
        // Получаем текстовое поле в текущей Activity
        EditText editText = (EditText) findViewById(R.id.editText);
        // Получае текст данного текстового поля
        String message = editText.getText().toString();

        editText.setText("");

        if (message.equals("demo")) {
            // действия, совершаемые после нажатия на кнопку
            // Создаем объект Intent для вызова новой Activity
            Intent intent = new Intent(this, MessageActivity.class);
            // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
            // второй параметр - значение этого объекта
            intent.putExtra("message", message+"!");
            // запуск activity
            startActivity(intent);
        } else{
            CustomDialogFragment dialog = new CustomDialogFragment();
            dialog.show(getSupportFragmentManager(), "custom");
        }

    }
}