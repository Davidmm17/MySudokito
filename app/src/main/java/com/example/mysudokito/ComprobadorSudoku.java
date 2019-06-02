package com.example.mysudokito;

public class ComprobadorSudoku {
    private GeneradorSudoku sudoku;
    private GeneradorSudoku respuestaSudoku;

    public ComprobadorSudoku(GeneradorSudoku sudoku) {
        this.sudoku = sudoku;
    }

    public GeneradorSudoku getSudoku() {
        return sudoku;
    }

    public void setSudoku(GeneradorSudoku sudoku) {
        this.sudoku = sudoku;
    }

    public GeneradorSudoku getRespuestaSudoku() {
        return respuestaSudoku;
    }

    public void setRespuestaSudoku(GeneradorSudoku respuestaSudoku) {
        this.respuestaSudoku = respuestaSudoku;
    }


    public boolean comprobarRespuestaSudoku(){
        for(int x = 0; x < 9; x++){
            for (int y = 0; y < 9 ; y++){
                if(this.sudoku.getTablero()[x][y].getNumero() != this.respuestaSudoku.getTablero()[x][y].getNumero()){
                    return false;
                }
            }
        }
        return true;
    }

}
