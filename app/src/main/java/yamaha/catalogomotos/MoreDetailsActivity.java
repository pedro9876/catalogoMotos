package yamaha.catalogomotos;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.github.barteksc.pdfviewer.PDFView;

public class MoreDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (Build.VERSION.SDK_INT > 16) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        setContentView(R.layout.activity_more_details);


        PDFView pdfView = findViewById(R.id.pdfView);
        pdfView.fromAsset("yamaha.pdf")


                //definimos como sera el diseño del pdf

                .enableSwipe(true)
                .swipeHorizontal(true)
                .enableDoubletap(true)
                .defaultPage(0)
                .password(null)
                .scrollHandle(null)
                .enableAntialiasing(true)
                .autoSpacing(false)
                .pageSnap(true)
                .pageFling(true)
                .autoSpacing(true)
                .pages(0,1,2,3)
                .load();


    }

    public void fadeDetails(View button)
    {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }


}
