package com.example.mysudokito.utilidades;

public class Utilidades {

    //Constantes

    public static final String TABLA_PUNTUACIONES ="puntuaciones";
    public static final String CAMPO_ID ="id";
    public static final String CAMPO_NOMBRE ="nombre";
    public static final String CAMPO_PUNTUACIONES_SEGUNDOS ="puntuacion_segundos";

    public static final String CAMPO_PUNTUACIONES_MINUTOS ="puntuacion_minutos";
    public static final String CAMPO_FECHA ="fecha";


    public static final String CREAR_TABLA_PUNTUACIONES="CREATE TABLE "+ TABLA_PUNTUACIONES+" ("+
            CAMPO_ID+" INTEGER, "+CAMPO_NOMBRE+" TEXT, "+CAMPO_PUNTUACIONES_SEGUNDOS+" TEXT, "+CAMPO_PUNTUACIONES_MINUTOS+" TEXT, "+CAMPO_FECHA+"TEXT)";


}
