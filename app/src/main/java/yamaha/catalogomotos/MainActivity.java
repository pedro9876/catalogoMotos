package yamaha.catalogomotos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

import yamaha.catalogomotos.Dialogs.DialogMensaje;
import yamaha.catalogomotos.adapter.LVAdapter;
import yamaha.catalogomotos.model.Moto;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener,
        View.OnFocusChangeListener, TextWatcher {

    private Activity mActivity;

    private ListView mListView;
    private LVAdapter mAdapter;

    private ArrayList<Moto> data;
    private TextView tvAux = null;
    private ImageView isImage;

    private EditText Nombres, Apellidos, Telefono, Correo;
    ImageButton btn;

    Timer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //este codigo elimina la barra de notificaciones
        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_main);


        //Asignamos los valores de cada componente de la interfaz a las variables
        Nombres = (EditText) findViewById(R.id.editText1);
        Nombres.setHint("Nombres");
        Nombres.setHighlightColor(Color.WHITE);
        Nombres.setTextColor(Color.WHITE);
        Nombres.setHintTextColor(Color.WHITE);
        Nombres.setBackground(getDrawable(R.drawable.edittext_drawable));
        Nombres.setTextSize(24);
        Nombres.setPadding(15, 0, 0, 0);
        Nombres.setOnFocusChangeListener(this);

        Apellidos = (EditText) findViewById(R.id.editText2);
        Apellidos.setHint("Apellidos");
        Apellidos.setGravity(View.FOCUS_RIGHT);
        Apellidos.setHighlightColor(Color.WHITE);
        Apellidos.setTextColor(Color.WHITE);
        Apellidos.setHintTextColor(Color.WHITE);
        Apellidos.setBackground(getDrawable(R.drawable.edittext_drawable));
        Apellidos.setTextSize(24);
        Apellidos.setPadding(15, 0, 0, 0);
        Apellidos.setOnFocusChangeListener(this);


        Telefono = (EditText) findViewById(R.id.editText3);
        Telefono.setHint("Teléfono");
        Telefono.setGravity(View.FOCUS_RIGHT);
        Telefono.setHighlightColor(Color.WHITE);
        Telefono.setTextColor(Color.WHITE);
        Telefono.setHintTextColor(Color.WHITE);
        Telefono.setBackground(getDrawable(R.drawable.edittext_drawable));
        Telefono.setTextSize(24);
        Telefono.setPadding(15, 0, 0, 0);
        Telefono.setOnFocusChangeListener(this);

        Correo = (EditText) findViewById(R.id.editText4);
        Correo.setHint("Correo");
        Correo.setGravity(View.FOCUS_RIGHT);
        Correo.setHighlightColor(Color.WHITE);
        Correo.setTextColor(Color.WHITE);
        Correo.setHintTextColor(Color.WHITE);
        Correo.setBackground(getDrawable(R.drawable.edittext_drawable));
        Correo.setTextSize(24);
        Correo.setPadding(15, 0, 0, 0);
        Correo.setOnFocusChangeListener(this);
        Correo.addTextChangedListener(this);
        mActivity = MainActivity.this;

        mListView = (ListView) findViewById(R.id.list_view);
        isImage = (ImageView) findViewById(R.id.isImagen);

        populateMotorbikes();
        mAdapter = new LVAdapter(mActivity, data);
        mListView.setAdapter(mAdapter);
        mListView.setSelection(0);

        mListView.setOnItemClickListener(this);

        mListView.performItemClick(
                mListView.getAdapter().getView(0, null, null),
                0,
                mListView.getAdapter().getItemId(0));

    }

    public void populateMotorbikes() {
        data = new ArrayList<>();
        String[] arrayNames = getResources().getStringArray(R.array.name);
        TypedArray arrayImages = getResources().obtainTypedArray(R.array.images);

        for (int i = 0; i < arrayNames.length; i++) {
            Moto moto = new Moto();
            moto.setName(arrayNames[i]);
            moto.setImage(arrayImages.getResourceId(i, -1));
            data.add(moto);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        select(view, i);
    }

    private void select(View v, int i) {
        TextView tv = v.findViewById(R.id.tvName);
        if (tvAux != null) {
            tvAux.setTextColor(Color.WHITE);
        }
        tvAux = tv;
        tv.setTextColor(Color.BLACK);
        Moto motoAux = data.get(i);
        isImage.setImageResource(motoAux.getImage());
    }

    //Definimos un metodo para ejecutarlos al precionar boton

    public void Enviar(View v) {
        boolean isNom, isAp, isTel, isCorr, isCorr2;
        isNom = TextUtils.isEmpty(Nombres.getText().toString());
        isAp = TextUtils.isEmpty(Apellidos.getText().toString());
        isTel = TextUtils.isEmpty(Telefono.getText().toString());
        isCorr = TextUtils.isEmpty(Correo.getText().toString());
        isCorr2=validCorreo(Correo.getText().toString());
        if (isNom)
            cambiarError(Nombres);
        if (isAp)
            cambiarError(Apellidos);
        if (isTel)
            cambiarError(Telefono);
        if (isCorr)
            cambiarError(Correo);
        if(!isCorr2)
            cambiarError(Correo);

        if (!isNom && !isAp && !isTel && !isCorr && isCorr2) {
            DialogMensaje.newInstance("Su solicitud sera enviada, Telefono: "+Telefono.getText().toString()+
                        " - Correo: "+Correo.getText().toString())
                .show(getSupportFragmentManager(),DialogMensaje.TAG);
        }

    }

    private void cambiarError(EditText editText) {
        String hint = "";
        switch (editText.getId()) {
            case R.id.editText1:
                hint = "Debe ingresar los nombres";
                break;
            case R.id.editText2:
                hint = "Debe ingresar los apellidos";
                break;
            case R.id.editText3:
                hint = "Debe ingresar el teléfono";
                break;
            case R.id.editText4:
                hint = "Debe ingresar el correo";
                break;
        }
        editText.setBackground(getDrawable(R.drawable.edittext_drawable_error));
        editText.setPadding(15, 0, 0, 0);
        editText.setHint(hint);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(R.drawable.pencil_5, null),
                null);
    }

    private void cambiarNormal(EditText editText) {
        String hint = "";
        switch (editText.getId()) {
            case R.id.editText1:
                hint = "Nombres";
                break;
            case R.id.editText2:
                hint = "Apellidos";
                break;
            case R.id.editText3:
                hint = "Teléfono";
                break;
            case R.id.editText4:
                hint = "Correo";
                break;
        }
        editText.setBackground(getDrawable(R.drawable.edittext_drawable));
        editText.setPadding(15, 0, 0, 0);
        editText.setHint(hint);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                null,
                null);
    }

    private void cambiarNormal2(EditText editText) {
        String hint = "";
        switch (editText.getId()) {
            case R.id.editText1:
                hint = "Nombres";
                break;
            case R.id.editText2:
                hint = "Apellidos";
                break;
            case R.id.editText3:
                hint = "Teléfono";
                break;
            case R.id.editText4:
                hint = "Correo";
                break;
        }
        editText.setBackground(getDrawable(R.drawable.edittext_drawable));
        editText.setPadding(15, 0, 0, 0);
        editText.setHint(hint);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(R.drawable.check_ok, null),
                null);
    }

    private void changeOk(final EditText editText) {
        editText.setBackground(getDrawable(R.drawable.edittext_drawable_ok));
        editText.setPadding(15, 0, 0, 0);
        editText.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(R.drawable.check_ok, null),
                null);

        final Runnable setBackgoundEditText = new Runnable() {
            public void run() {
                cambiarNormal2(editText);
            }
        };
        TimerTask task = new TimerTask() {
            public void run() {
                MainActivity.this.runOnUiThread(setBackgoundEditText);
            }
        };
        timer = new Timer();
        timer.schedule(task, 1000);
    }

    public void fadeMoreDetails(View button) {
        startActivity(new Intent(this, MoreDetailsActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


    /**
     * Método para detectar el cambio de foco del editText
     */



    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        final EditText editFocus = (EditText) v;
        if (hasFocus) {
            cambiarNormal(editFocus);
        } else {
            if (TextUtils.isEmpty(editFocus.getText().toString())) {
                cambiarError(editFocus);
            } else {
                changeOk(editFocus);
            }
        }
    }

    /**
     * Métodos para detectar cambios en EditText (Correo)
     */


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(validCorreo(s.toString()))
            changeOk(Correo);
        else
            cambiarError(Correo);
    }

    /**
     * Método para validar el correo
     */
    private boolean validCorreo(String correo) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return correo.length()>=8 && pattern.matcher(correo).matches();
    }
}
