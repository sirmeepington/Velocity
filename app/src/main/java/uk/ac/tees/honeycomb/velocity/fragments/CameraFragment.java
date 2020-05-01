package uk.ac.tees.honeycomb.velocity.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;

import uk.ac.tees.honeycomb.velocity.R;

public class CameraFragment extends Fragment {

    private CodeScanner cs;
    private CodeScannerView scv;
    private EditText qrCodeNameField;
    public String name; // ?

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View parentView = inflater.inflate(R.layout.fragment_camera, container, false);

        scv = parentView.findViewById(R.id.sView);
        cs = new CodeScanner(parentView.getContext(), scv);


        qrCodeNameField = parentView.findViewById(R.id.qrCodeName);

        InputMethodManager imm = (InputMethodManager) parentView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        qrCodeNameField.setImeOptions(EditorInfo.IME_ACTION_DONE);
        qrCodeNameField.setOnEditorActionListener((v, actionId, event) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE && imm != null){
                qrCodeNameField.setVisibility(View.GONE);
                imm.hideSoftInputFromWindow(qrCodeNameField.getWindowToken(), 0);
                return true;
            }
            return false;
        });

        cs.setDecodeCallback((v) -> name = qrCodeNameField.getText().toString());

        scv.setOnClickListener(v -> cs.startPreview());

        return parentView;
    }

    @Override
    public void onResume() {
        super.onResume();

        cs.startPreview();
    }

}
