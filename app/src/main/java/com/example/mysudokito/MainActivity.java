package com.example.mysudokito;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import com.example.mysudokito.entidades.Usuario;
import com.example.mysudokito.utilidades.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    public EditText nombreJugador;
    ListView listaRanking;
    ConexiónSQLiteHelper conn;
    String jugador;
    ArrayList<String> listaJugadores;
    ArrayList<Usuario> listaUsuarios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        conn= new ConexiónSQLiteHelper(this,"bd_sudoku",null,5);
/*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);*/
        listaRanking = findViewById(R.id.listViewRanking);
        nombreJugador = findViewById(R.id.nombreJugador);

        consultarRainking();

        ArrayAdapter adaptador = new ArrayAdapter(this,android.R.layout.simple_list_item_1, listaJugadores);
        listaRanking.setAdapter(adaptador);
    }

    private void consultarRainking(){
        SQLiteDatabase db = conn.getReadableDatabase();

        Usuario usuario = null;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor= db.rawQuery("SELECT * FROM " + Utilidades.TABLA_PUNTUACIONES+" ORDER BY "
                + Utilidades.CAMPO_PUNTUACIONES_MINUTOS +" + 0 ASC , "+Utilidades.CAMPO_PUNTUACIONES_SEGUNDOS+"+ 0  ASC limit 10",null);


        while(cursor.moveToNext()){
            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setPuntuacionSegundos(cursor.getInt(2));
            usuario.setPuntuacionMinutos(cursor.getInt(3));
            usuario.setFecha(cursor.getString(4));

            listaUsuarios.add(usuario);
        }

        System.out.println("Estoy haciendo la query de  la lista del ranking");
        obtenerLista();
    }

    public void obtenerLista(){
        listaJugadores = new ArrayList<String>();

        for(int i = 0; i <listaUsuarios.size(); i++){
            int posicion = i +1;
            listaJugadores.add(posicion+". "+listaUsuarios.get(i).getNombre() + " - " +
                    listaUsuarios.get(i).getPuntuacionMinutos()+"m "+listaUsuarios.get(i).getPuntuacionSegundos() +"s"
                    //listaUsuarios.get(i).getId()+" - "+listaUsuarios.get(i).getNombre()
            );
        }
        System.out.println("Estoy obteniendo la lista del ranking");
    }

    public void startSudoku(View view) {

        jugador = nombreJugador.getText().toString();
        Log.d(LOG_TAG, "Button clicked!");
        Intent intent = new Intent(this, Sudoku.class);

        if(nombreJugador.getText() == null){
            intent.putExtra("nombreJugador","Prova");
        }
        else {
            intent.putExtra("nombreJugador",jugador);
        }

        startActivity(intent);
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



}
