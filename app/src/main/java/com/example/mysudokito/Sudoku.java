package com.example.mysudokito;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mysudokito.entidades.Usuario;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Sudoku extends AppCompatActivity {
    private GeneradorSudoku sudoku;
    private LinearLayout tablero;
    private EditText casilla;
    private HashMap<Integer,EditText> casillasMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);

        this.sudoku = new GeneradorSudoku();

        this.sudoku.vaciarCasillas();
        this.sudoku.printarTablero();

        this.tablero = findViewById(R.id.tableroSudoku);
        this.casillasMap = new HashMap<>();
        this.crearTablero();


    }

    public void crearTablero(){

        for(int x =0;x<9;x++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            for(int y =0;y<9;y++){
                casilla = new EditText(this);
                casilla.setInputType(InputType.TYPE_CLASS_NUMBER);
                if(this.sudoku.getTablero()[x][y].getNumero()==0) casilla.setText("");
                else{
                    casilla.setText(this.sudoku.getTablero()[x][y].getNumero()+"");
                    casilla.setEnabled(false);
                }
                casilla.setLayoutParams(new LinearLayout.LayoutParams(75, LinearLayout.LayoutParams.WRAP_CONTENT));

                String concatenacionid = x+""+y;
                int id = Integer.parseInt(concatenacionid);
                casilla.setId(id);
                row.addView(casilla);
                this.casillasMap.put(id,casilla);

            }
            this.tablero.addView(row);
        }
    }
}
