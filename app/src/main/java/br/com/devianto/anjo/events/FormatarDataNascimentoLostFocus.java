package br.com.devianto.anjo.events;

import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.EditText;
import android.widget.Toast;


import org.apache.commons.validator.GenericValidator;

import br.com.devianto.anjo.utilities.Constante;

public class FormatarDataNascimentoLostFocus
        implements OnFocusChangeListener {


    @Override
    public void onFocusChange(View view, boolean flag) {
        if (!flag) {
            String s = ((EditText) view).getText().toString();
            if (!GenericValidator.isDate(s, Constante.DATE_LONG_FORMAT, true)) {
                Toast.makeText(view.getContext(),
                        "A data de nascimento está errada", Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
}
