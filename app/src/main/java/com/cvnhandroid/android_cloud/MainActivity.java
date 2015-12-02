package com.cvnhandroid.android_cloud;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getName();
    @Bind(R.id.tvInfo)
    TextView tvInfo;
    @Bind(R.id.etInfo)
    EditText etInfo;
    @Bind(R.id.btnChange)
    Button btnChange;
    Firebase myFirebaseRef;

    //    https://www.firebase.com/docs/android/quickstart.html
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://crackling-inferno-3353.firebaseio.com/");
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                Log.e(TAG, "onDataChange: " + snapshot.getValue());
//                if (snapshot.getValue() != null) {
//                    tvInfo.setText(snapshot.getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError error) {
//            }
//        });

//        E5:4C:5F:6B:58:92 xanh
//        EF:66:66:04:11:7B tim1
        final String beaconXanh = "E5:4C:5F:6B:58:92";
        myFirebaseRef.child(beaconXanh).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.e(TAG, "beacon xanh: " + snapshot.getValue());
                if (snapshot.getValue() != null && snapshot.getValue().toString().equals("solved")==false) {
                    tvInfo.setText(snapshot.getValue().toString());
                    Toast.makeText(MainActivity.this, "Device " + snapshot.getValue().toString() + " has been detected", Toast.LENGTH_SHORT).show();
                    myFirebaseRef.child(beaconXanh).setValue("solved");
                }
            }

            @Override
            public void onCancelled(FirebaseError error) {
            }
        });
    }
    @OnClick(R.id.btnChange)
    public void btnChangeOnclick(){
        Log.e(TAG, "onClick: " + etInfo.getText().toString());
//        myFirebaseRef.child("message").setValue(etInfo.getText().toString());
    }

}
