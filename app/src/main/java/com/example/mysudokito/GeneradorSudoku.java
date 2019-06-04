package com.example.mysudokito;

import java.util.ArrayList;
import java.util.Random;

public class GeneradorSudoku implements  Cloneable {
    private Casilla[][] tablero = new Casilla[9][9];
    private Casilla[][] tableroRespuesta = new Casilla[9][9];
    public GeneradorSudoku() {
        this.initTablero();

    }

    public Casilla[][] getTableroRespuesta() {
        return tableroRespuesta;
    }

    public void setTableroRespuesta(Casilla[][] tableroRespuesta) {
        this.tableroRespuesta = tableroRespuesta;
    }

    public GeneradorSudoku(GeneradorSudoku another){
        this.tablero = another.tablero;
    }

    public void initTablero(){
        this.inicializarSudoku();
        this.llenarTableroConNumeros();
        this.intercambiarRows();
        this.intercambiarcolumnas();
        this.intercambiarMatrizesRow();
        this.intercambiarMatrizesColumn();
       // this.printarTablero();

    }

    public void inicializarSudoku(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                this.tablero[x][y] = new Casilla();
                this.tableroRespuesta[x][y] = new Casilla();
            }
        }
    }

    public void llenarTableroConNumeros() {
        int k = 1,n=1;
        for(int x=0;x<9;x++){
            k=n;
            for(int y=0;y<9;y++){
                if(k>9){
                    k=1;
                }
                this.tablero[x][y].setNumero(k);
                this.tableroRespuesta[x][y].setNumero(k);
                k++;
            }
            n=k+3;
            if(k==10)
                n=4;
            if(n>9)
                n=(n%9)+1;
        }
    }

    public void intercambiarRows(){
        Casilla[][] provisional = new Casilla[9][9];
        Casilla[][] provisional2 = new Casilla[9][9];
        Random r= new Random();
        int k1,k2,max=2,min=0;
        for(int i=0;i<3;i++) {
            k1=r.nextInt(max-min+1)+min;
            do{
                k2=r.nextInt(max-min+1)+min;
            }while(k1==k2);

            for(int j=0;j<9;j++)
            {
                provisional[k1][j] = this.tablero[k1][j];
                provisional2[k1][j] = this.tableroRespuesta[k1][j];
                this.tablero[k1][j] = this.tablero[k2][j];
                this.tableroRespuesta[k1][j] = this.tableroRespuesta[k2][j];
                this.tablero[k2][j] = provisional[k1][j];
                this.tableroRespuesta[k2][j] = provisional2[k1][j];
            }

            max+=3;min+=3;


        }
    }

    public void intercambiarcolumnas(){
        Casilla[][] provisional = new Casilla[9][9];
        Casilla[][] provisional2 = new Casilla[9][9];
        Random r= new Random();
        int k1,k2,max=2,min=0;
        for(int i=0;i<3;i++) {
            k1=r.nextInt(max-min+1)+min;
            do{
                k2=r.nextInt(max-min+1)+min;
            }while(k1==k2);

            for(int j=0;j<9;j++)
            {
                provisional[j][k1] = this.tablero[j][k1];
                provisional2[j][k1] = this.tableroRespuesta[j][k1];
                this.tablero[j][k1] = this.tablero[j][k2];
                this.tableroRespuesta[j][k1] = this.tableroRespuesta[j][k2];
                this.tablero[j][k2] = provisional[j][k1];
                this.tableroRespuesta[j][k2] = provisional2[j][k1];
            }

            max+=3;min+=3;


        }
    }

    public void intercambiarMatrizesRow(){
        int[] intArray = {0, 3, 6};
        int k1,k2;
        int idx = new Random().nextInt(intArray.length);
        k1 = intArray[idx];
        do{
            idx = new Random().nextInt(intArray.length);
            k2= intArray[idx];
        }while (k1==k2);
        Casilla[][] provisional = new Casilla[9][9];
        Casilla[][] provisional2 = new Casilla[9][9];
        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                provisional[k1][i] = this.tablero[k1][i];
                provisional2[k1][i] = this.tableroRespuesta[k1][i];
                this.tablero[k1][i] = this.tablero[k2][i];
                this.tableroRespuesta[k1][i] = this.tableroRespuesta[k2][i];
                this.tablero[k2][i] = provisional[k1][i];
                this.tableroRespuesta[k2][i] = provisional2[k1][i];

            }
            k1++;
            k2++;
        }

    }

    public void intercambiarMatrizesColumn(){
        int[] intArray = {0, 3, 6};
        int k1,k2;
        int idx = new Random().nextInt(intArray.length);
        k1 = intArray[idx];
        do{
            idx = new Random().nextInt(intArray.length);
            k2= intArray[idx];
        }while (k1==k2);
        Casilla[][] provisional = new Casilla[9][9];
        Casilla[][] provisional2 = new Casilla[9][9];

        for(int n=1;n<=3;n++)
        {
            for(int i=0;i<9;i++)
            {
                provisional[i][k1] = this.tablero[i][k1];
                provisional2[i][k1] = this.tableroRespuesta[i][k1];
                this.tablero[i][k1] = this.tablero[i][k2];
                this.tableroRespuesta[i][k1] = this.tableroRespuesta[i][k2];
                this.tablero[i][k2] = provisional[i][k1];
                this.tableroRespuesta[i][k2] = provisional2[i][k1];
            }
            k1++;
            k2++;
        }

    }

    public void vaciarCasillas(){
        Random r= new Random();
        int k1,k2,max=8,min=0;
        for(int x=0;x<40;x++){
            do{
                k1=r.nextInt(max-min+1)+min;
                k2=r.nextInt(max-min+1)+min;
            }while ( this.tablero[k1][k2].getNumero()==0 );
            this.tablero[k1][k2].setNumero(0);
        }

    }

    public void printarTablero(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                System.out.print(this.tablero[x][y].getNumero());
            }
            System.out.println("");

        }
    }
    public void printarTableroRespuesta(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                System.out.print(this.tableroRespuesta[x][y].getNumero());
            }
            System.out.println("");

        }
    }


    public void rellenarTableroRespuesta(){
        for(int x=0;x<9;x++){
            for(int y=0;y<9;y++){
                tableroRespuesta[x][y] = new Casilla();
               tableroRespuesta[x][y] = tablero[x][y];
            }

        }
    }
    @Override
    public GeneradorSudoku clone() throws CloneNotSupportedException {
        GeneradorSudoku tableroSudoku = (GeneradorSudoku) super.clone();

        // primitive fields are ignored, as their content is already copied
        // immutable fields like String are ignored

        // create new objects for any non-primitive, mutable fields
        //student.map = new HashMap<>(map);

        return tableroSudoku;		// return deep copy
    }




    public Casilla[][] getTablero() {
        return tablero;
    }


    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }
}
