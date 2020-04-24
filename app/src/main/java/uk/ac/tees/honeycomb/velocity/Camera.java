package uk.ac.tees.honeycomb.velocity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.google.zxing.Result;

public class Camera extends AppCompatActivity {
    CodeScanner cs;
    CodeScannerView scv;
    EditText qrCodeNameField;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        scv = findViewById(R.id.sView);
        cs = new CodeScanner(this, scv);


        qrCodeNameField = (EditText) findViewById(R.id.qrCodeName);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(qrCodeNameField, InputMethodManager.SHOW_FORCED);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,InputMethodManager.HIDE_IMPLICIT_ONLY);
        qrCodeNameField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        qrCodeNameField.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    qrCodeNameField.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(qrCodeNameField.getWindowToken(), 0);

                    return true;
                }
                return false;
            }
        });

        cs.setDecodeCallback(new DecodeCallback() {

            @SuppressLint("WrongThread")
            @Override
            public void onDecoded(@NonNull final Result result) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        name = qrCodeNameField.getText().toString();

                        //QRCodeActivity.generateQRCode(result.getText(), name);
                    }
                });
            }
        });
        scv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cs.startPreview();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        cs.startPreview();
    }

}
