package com.example.testandoid;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CustomDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Ошибка при вводе имени")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Проверьте имя и попробуйте сновва")
                .setPositiveButton("OK", null)
                .create();
    }
}
