package com.example.mysudokito;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mysudokito.entidades.Usuario;
import com.example.mysudokito.utilidades.Utilidades;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

public class Sudoku extends AppCompatActivity {
    private GeneradorSudoku sudoku;
    private GeneradorSudoku respuestaSudoku;
    private LinearLayout tablero;
    private TextView contadorText;
    private EditText casilla;
    private HashMap<Integer,EditText> hashMapCasillas;
    private Usuario jugador;
    String nombreUsuario = "Prova";
    private ComprobadorSudoku comprobadorSolucion;
    private Timer contador;

    int segundos = 0 ,minutos = 0, horas = 0, count = 0;
    String textContador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sudoku);
        System.out.println("tercero " + nombreUsuario);
        this.nombreUsuario = getIntent().getExtras().getString("nombreJugador");

        System.out.println("segundo intento" + nombreUsuario);
        this.sudoku = new GeneradorSudoku();
        this.contador = new Timer();
        this.contadorText = (TextView) findViewById(R.id.contadorText);
        this.sudoku.vaciarCasillas();
        this.sudoku.printarTablero();
        this.jugador = new Usuario();
        this.tablero = findViewById(R.id.tableroSudoku);
        this.hashMapCasillas = new HashMap<>();
        this.crearTablero();
        this.empezarContadorTiempo();
        this.comprobadorSolucion = new ComprobadorSudoku(this.sudoku);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.example, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"Webview Selected ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, web_view.class);
                startActivity(intent);
                return true;
            case  R.id.item2:
                Toast.makeText(this,"Webview Selected ", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(this, web_view_xWing.class);
                startActivity(intent2);
                return true;
            case  R.id.item3:
                Toast.makeText(this,"Webview Selected ", Toast.LENGTH_SHORT).show();
                Intent intent3 = new Intent(this, web_view_Swordfish.class);
                startActivity(intent3);
                return true;
        }

        return super.onOptionsItemSelected(item);
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
                this.hashMapCasillas.put(id,casilla);


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
                casilla = this.hashMapCasillas.get(Integer.parseInt(x+""+y));
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
            jugador.setPuntuacionSegundos(segundos);
            jugador.setPuntuacionMinutos(minutos);
            registrarPuntuacion();
            finish();
            return true;
        }
            Toast.makeText(this,"Solución Incorrecta", Toast.LENGTH_SHORT).show();
           return false;
        }

    public void empezarContadorTiempo(){

        this.contador.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if(minutos>0){
                    textContador = minutos+"m "+segundos +"s";
                }
                else if(horas > 0){

                    textContador = horas+"m "+ minutos+"m "+segundos +"s";
                }
                else{

                    textContador = segundos +"s";
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(segundos==60){
                            segundos = 0;
                            minutos++;

                        }
                        if(minutos == 60){
                            minutos = 0;
                            horas++;
                        }
                        contadorText.setText(textContador);
                        segundos++;
                        count++;
                    }
                });
            }
        },1000,1000);
    }

    public void registrarPuntuacion(){
        ConexiónSQLiteHelper conn= new ConexiónSQLiteHelper(this,"bd_sudoku",null,5);

        SQLiteDatabase db=conn.getWritableDatabase();


        ContentValues values = new ContentValues();
        Date currentTime = Calendar.getInstance().getTime();
        values.put(Utilidades.CAMPO_ID, 1);
        values.put(Utilidades.CAMPO_NOMBRE, nombreUsuario);
        values.put(Utilidades.CAMPO_PUNTUACIONES_SEGUNDOS, segundos);
        values.put(Utilidades.CAMPO_PUNTUACIONES_MINUTOS, minutos);
        values.put(Utilidades.CAMPO_FECHA, currentTime.toString());

        Long idResultante = db.insert(Utilidades.TABLA_PUNTUACIONES, Utilidades.CAMPO_ID,values);

        Toast.makeText(getApplicationContext(), "Id Registro: " +idResultante, Toast.LENGTH_SHORT).show();
        db.close();

    }


}
