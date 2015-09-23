package com.flash.questionnaire.Design;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.flash.questionnaire.Kiosk.SecureHelper;
import com.flash.questionnaire.R;

/**
 * Created by Anton on 23.09.2015.
 */
public class ExitDialog extends DialogFragment {

    public void setContext(Context context) {
        this.context = context;
    }

    Context context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_exit, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setCancelable(true);


        Button button_ok = (Button) view.findViewById(R.id.button_ok);
        button_ok.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                SecureHelper secureHelper = new SecureHelper(context);
                EditText editText = (EditText) view.findViewById(R.id.editText);
                if (secureHelper.checkPassword(editText.getText().toString())) {
                    ((ThanksActivity) (getActivity())).showChooser();
                    dismiss();
                } else {
                    Toast.makeText(context, "Неверный пароль, попробуйте снова", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button button_cancel = (Button) view.findViewById(R.id.button_cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dismiss();
            }
        });
        return view;
    }

}

