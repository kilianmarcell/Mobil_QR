package petrik.hu.qrkod;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivity extends AppCompatActivity {

    private EditText editTextInput;
    private Button buttonQRScan, buttonQRGenerate;
    private TextView textViewQRResolution;
    private ImageView imageViewOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        buttonQRScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity.this);
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
                intentIntegrator.setPrompt("QR Code Scanner by Petrik");
                intentIntegrator.setBeepEnabled(false);
                intentIntegrator.initiateScan();
            }
        });
    }

    public void init() {
        editTextInput = findViewById(R.id.editTextInput);
        buttonQRScan = findViewById(R.id.buttonQRScan);
        buttonQRGenerate = findViewById(R.id.buttonQRGenerate);
        textViewQRResolution = findViewById(R.id.textViewQRResolution);
        imageViewOutput = findViewById(R.id.imageViewOutput);
    }
}