package com.example.mysudokito;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.media.MediaExtractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysudokito.entidades.Usuario;

import org.w3c.dom.Text;

import java.util.HashMap;

public class Sudoku extends AppCompatActivity {
    private GeneradorSudoku sudoku;
    private GeneradorSudoku respuestaSudoku;
    private LinearLayout tablero;
    private EditText casilla;
    private HashMap<Integer,EditText> casillasMap;
    private ComprobadorSudoku comprobadorSolucion;

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
        this.comprobadorSolucion = new ComprobadorSudoku(this.sudoku);

    }

    public void crearTablero(){
        int maxLength = 1;
        InputFilter[] fArray = new InputFilter[1];
        fArray[0] = new InputFilter.LengthFilter(maxLength);
        for(int x =0;x<9;x++){
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            row.setGravity(Gravity.CENTER);

            for(int y =0;y<9;y++){
                casilla = new EditText(this);
                casilla.setInputType(InputType.TYPE_CLASS_NUMBER);

                if(this.sudoku.getTablero()[x][y].getNumero()==0) casilla.setText("");
                else{
                    casilla.setText(this.sudoku.getTablero()[x][y].getNumero()+"");
                    casilla.setEnabled(false);
                    casilla.setTypeface(null, Typeface.BOLD);
                }
                casilla.setLayoutParams(new LinearLayout.LayoutParams(111, LinearLayout.LayoutParams.WRAP_CONTENT));
               // casilla.setBackgroundResource(R.layout.);

                ShapeDrawable sd = new ShapeDrawable();

                // Specify the shape of ShapeDrawable
                sd.setShape(new RectShape());

                // Specify the border color of shape
                sd.getPaint().setColor(Color.BLACK);

                // Set the border width
                sd.getPaint().setStrokeWidth(10f);

                // Specify the style is a Stroke
                sd.getPaint().setStyle(Paint.Style.STROKE);

                // Finally, add the drawable background to TextView
                casilla.setBackground(sd);

                casilla.setTextColor(Color.BLACK);
                casilla.setGravity(Gravity.CENTER);
                casilla.setFilters(fArray);
                //casilla.setEnabled(false);
                String concatenacionid = x+""+y;
                int id = Integer.parseInt(concatenacionid);
                casilla.setId(id);
                row.addView(casilla);
                this.casillasMap.put(id,casilla);


            }
            this.tablero.addView(row);
        }
    }

    public void reiniciarTablero(View view){
        System.out.println("Borrar Tablerito");
        tablero.removeAllViews();
        this.crearTablero();
        this.sudoku.printarTablero();
    }

    public boolean comprobar(View view){
        this.respuestaSudoku = new GeneradorSudoku();
        EditText casilla;
        for(int x = 0; x <9;x++) {
            for(int y=0 ; y < 9 ;y++){
                casilla = this.casillasMap.get(Integer.parseInt(x+""+y));
                if(TextUtils.isEmpty(casilla.getText())){
                    Toast.makeText(this,"Hay casillas vacias", Toast.LENGTH_SHORT).show();
                    return false;
                } else{
                    this.respuestaSudoku.getTablero()[x][y].setNumero(Integer.parseInt(casilla.getText().toString()));
                }
            }
        }

        this.comprobadorSolucion.setRespuestaSudoku(respuestaSudoku);
        if(this.comprobadorSolucion.comprobarRespuestaSudoku()){
            Toast.makeText(this,"Solución Correcta", Toast.LENGTH_SHORT).show();
            return true;
        }
            Toast.makeText(this,"Solución Incorrecta", Toast.LENGTH_SHORT).show();
           return false;
        }

}
