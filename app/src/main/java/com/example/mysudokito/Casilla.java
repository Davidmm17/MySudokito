package com.example.mysudokito;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class Casilla{
    private int numero;

    public Casilla() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }
    /*
    private int numero;

    private Paint mPaint;

    public Casilla(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.BLACK);

        mPaint.setTextSize(60);

        Rect bounds = new Rect();
        mPaint.getTextBounds(String.valueOf(numero), 0,String.valueOf(numero).length(),bounds);

        canvas.drawText(String.valueOf(numero),(getWidth()-bounds.width()) /2, (getHeight() + bounds.height()) /2, mPaint );


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
*/
    /* private int numero;

    public Casilla() {
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }*/
}

