package com.example.filmbase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WantInfoEditActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_info_edit);

        Button btnAddWantEdit = findViewById(R.id.btnAddWantEdit);
        Button btnBackWantEdit = findViewById(R.id.btnBackWantEdit);
        btnAddWantEdit.setOnClickListener(this);
        btnBackWantEdit.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddWantEdit:

                break;
            case R.id.btnBackWantEdit:
                Intent intentMain = new Intent(this, MainActivity.class);
                startActivity(intentMain);
                break;
        }

    }
}
