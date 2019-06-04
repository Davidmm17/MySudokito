package com.example.mysudokito;

public class ComprobadorSudoku implements Cloneable{
    private GeneradorSudoku sudokuTablero;
    private GeneradorSudoku respuestaSudokuTablero;

    public ComprobadorSudoku(GeneradorSudoku sudokuTablero) {
        this.sudokuTablero = sudokuTablero;
    }

    public GeneradorSudoku getSudokuTablero() {
        return sudokuTablero;
    }

    public void setSudokuTablero(GeneradorSudoku sudokuTablero) {
        this.sudokuTablero = sudokuTablero;
    }

    public GeneradorSudoku getRespuestaSudokuTablero() {
        return respuestaSudokuTablero;
    }

    public void setRespuestaSudokuTablero(GeneradorSudoku respuestaSudokuTablero) {
        this.respuestaSudokuTablero = respuestaSudokuTablero;
    }


    public boolean comprobarRespuestaSudoku(GeneradorSudoku tableroSudoku, GeneradorSudoku solucionSudoku ){

        for(int x = 0; x < 9; x++){
            for (int y = 0; y < 9 ; y++){
                if(this.getSudokuTablero().getTableroRespuesta()[x][y].getNumero() != this.getRespuestaSudokuTablero().getTablero()[x][y].getNumero()){
                    return false;
                }
            }
        }
        return true;
    }

}
