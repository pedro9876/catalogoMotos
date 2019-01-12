package yamaha.catalogomotos.Dialogs;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import yamaha.catalogomotos.R;

public class DialogMensaje extends DialogFragment {
    public final static String TAG="DialogMensaje";
    private final static String MESS="Mensaje";

    public static DialogMensaje newInstance(@NonNull String mess){
        Bundle bn=new Bundle();
        bn.putString(MESS,mess);
        DialogMensaje dd=new DialogMensaje();
        dd.setArguments(bn);
        return dd;
    }

    private TextView mensaje,aceptar;
    private String mess;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder alert=new AlertDialog.Builder(getActivity());
        View view=getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_mensaje,null);
        mensaje=view.findViewById(R.id.mensaje);
        aceptar=view.findViewById(R.id.aceptar_dialog);

        if(getArguments()!=null) {
            mess = getArguments().getString(MESS);
            mensaje.setText(mess);
        }

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        alert.setView(view);
        return alert.create();
    }
}

