package yamaha.catalogomotos;

import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import yamaha.catalogomotos.adapter.LVAdapter;
import yamaha.catalogomotos.model.Moto;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Activity mActivity;

    private ListView mListView;
    private LVAdapter mAdapter;

    private ArrayList<Moto> data;
    private TextView tvAux = null;
    private ImageView isImage;

    private EditText Nombres, Apellidos, Telefono, Correo;
    ImageButton btn;

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
        Nombres.setPadding(15,0,0,0);

        Apellidos = (EditText) findViewById(R.id.editText2);
        Apellidos.setHint("Apellidos");
        Apellidos.setGravity(View.FOCUS_RIGHT);
        Apellidos.setHighlightColor(Color.WHITE);
        Apellidos.setTextColor(Color.WHITE);
        Apellidos.setHintTextColor(Color.WHITE);
        Apellidos.setBackground(getDrawable(R.drawable.edittext_drawable));
        Apellidos.setTextSize(24);
        Apellidos.setPadding(15,0,0,0);

        Telefono = (EditText) findViewById(R.id.editText3);
        Telefono.setHint("Teléfono");
        Telefono.setGravity(View.FOCUS_RIGHT);
        Telefono.setHighlightColor(Color.WHITE);
        Telefono.setTextColor(Color.WHITE);
        Telefono.setHintTextColor(Color.WHITE);
        Telefono.setBackground(getDrawable(R.drawable.edittext_drawable));
        Telefono.setTextSize(24);
        Telefono.setPadding(15,0,0,0);

        Correo = (EditText) findViewById(R.id.editText4);
        Correo.setHint("Correo");
        Correo.setGravity(View.FOCUS_RIGHT);
        Correo.setHighlightColor(Color.WHITE);
        Correo.setTextColor(Color.WHITE);
        Correo.setHintTextColor(Color.WHITE);
        Correo.setBackground(getDrawable(R.drawable.edittext_drawable));
        Correo.setTextSize(24);
        Correo.setPadding(15,0,0,0);
        mActivity = MainActivity.this;

        mListView = (ListView) findViewById(R.id.list_view);
        isImage = (ImageView) findViewById(R.id.isImagen);

        populateMotorbikes();
        mAdapter = new LVAdapter(mActivity, data);
        mListView.setAdapter(mAdapter);
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
        TextView tv = view.findViewById(R.id.tvName);
        if(tvAux != null)
            tvAux.setTextColor(Color.WHITE);
        tvAux = tv;
        tv.setTextColor(Color.BLACK);
        Moto motoAux = data.get(i);
        isImage.setImageResource(motoAux.getImage());
    }

    //Definimos un metodo para ejecutarlos al precionar boton

    public void Enviar(View v){
        if (Nombres.getText().toString().isEmpty()){
            Nombres.setBackground(getDrawable(R.drawable.edittext_drawable_2));
            Nombres.setPadding(15,0,0,0);
            Nombres.setHint("Nombres");

        }else
            Nombres.setBackground(getDrawable(R.drawable.edittext_drawable));
        Nombres.setPadding(15,0,0,0);
        Nombres.setHint("Debe ingresar los nombres");
        Nombres.setCompoundDrawablesWithIntrinsicBounds(null, null,
                getResources().getDrawable(R.drawable.pencil_5,null),
                null);


        {
            if (Apellidos.getText().toString().isEmpty()){
                Apellidos.setBackground(getDrawable(R.drawable.edittext_drawable_2));
                Apellidos.setPadding(15,0,0,0);
                Apellidos.setHint("Apellidos");

            }else
                Apellidos.setBackgroundResource(R.drawable.edittext_drawable);
                Apellidos.setPadding(15,0,0,0);
                Apellidos.setHint("Debe ingresar los apellidos");
                Apellidos.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    getResources().getDrawable(R.drawable.pencil_5,null),
                    null);


            {
                if (Telefono.getText().toString().isEmpty()){
                    Telefono.setBackgroundResource(R.drawable.edittext_drawable_2);
                    Telefono.setPadding(15,0,0,0);
                    Telefono.setHint("Teléfono");

                }else
                    Telefono.setBackgroundResource(R.drawable.edittext_drawable);
                    Telefono.setPadding(15,0,0,0);
                    Telefono.setHint("Debe ingresar el teléfono");
                    Telefono.setCompoundDrawablesWithIntrinsicBounds(null, null,
                        getResources().getDrawable(R.drawable.pencil_5,null),
                        null);

                {
                    if (Correo.getText().toString().isEmpty()){
                        Correo.setBackgroundResource(R.drawable.edittext_drawable_2);
                        Correo.setPadding(15,0,0,0);
                        Correo.setHint("Correo");

                    }else
                        Correo.setBackgroundResource(R.drawable.edittext_drawable);
                    Correo.setPadding(15,0,0,0);
                    Correo.setHint("Debe ingresar el correo");
                    Correo.setCompoundDrawablesWithIntrinsicBounds(null, null,
                            getResources().getDrawable(R.drawable.pencil_5,null),
                            null);


                }
            }
        }
    }

    public void fadeMoreDetails(View button) {
        startActivity(new Intent(this, MoreDetailsActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
