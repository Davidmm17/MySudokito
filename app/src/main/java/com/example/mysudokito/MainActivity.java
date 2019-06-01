package com.example.mysudokito;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.view.View;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    ListView listaRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConexiónSQLiteHelper conn= new ConexiónSQLiteHelper(this,"bd_sudoku",null,1);

    }

    public void startSudoku(View view) {
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Sudoku.class);
        startActivity(intent);
    }
}
