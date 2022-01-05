package petrik.hu.qrkod;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonQRScan, buttonQRGenerate;
    private TextView textViewQRResult;
    private ImageView imageViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonQRScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Olyan esemény lesz, ahol a kamerát fogjuk meghívni (scan kamera meghívása)...
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.setPrompt("QR Code Scanner by Petrik");
                intentIntegrator.setBeepEnabled(false);

                //Elindítás
                intentIntegrator.initiateScan();
            }
        });
    }

    //Amikor a szkennelés elindul akkor hívódik meg
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //meg kell vizsgálnunk, hogy az intentIntegratoro hívja e meg a kamerát
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            String seged = textViewQRResult.getText().toString();

            //Ha kilépünk a szkennelésből, írja ki ezt a felhasználónak
            if (result.getContents() == null) {
                Toast.makeText(this, "Kiléptél a szkennelésből", Toast.LENGTH_SHORT).show();
            } else {
                //A kapott értéket a textView-ba rakja
                textViewQRResult.setText((result.getContents()));
                try {
                    Uri uri = Uri.parse(seged);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                } catch (Exception e) {
                    Log.d("URI ERROR", e.toString());
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void init() {
        editTextInput = findViewById(R.id.editTextInput);
        buttonQRScan = findViewById(R.id.buttonQRScan);
        buttonQRGenerate = findViewById(R.id.buttonQRGenerate);
        textViewQRResult = findViewById(R.id.textViewQRResult);
        imageViewOutput = findViewById(R.id.imageViewOutput);
    }
}