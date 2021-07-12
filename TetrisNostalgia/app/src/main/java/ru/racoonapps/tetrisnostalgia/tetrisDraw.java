package ru.racoonapps.tetrisnostalgia;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;


@SuppressLint("DrawAllocation")
public class tetrisDraw  extends View {

    //размеры ставкана
    int glassX = 15;
    int glassY = 25;
    //размер клетки поля
    public static int blockWidth = 40;
    //начальная скорость, постепенно уменьшается до 100
    public static int startDelay = 500;
    //координаты стакана на экране дивайса
    public static int leftMargin = 0;
    public static int topMargin = 0;
    //массив клеток
    FieldBlock[][] fields;
    //активная фигура
    ActiveFigure activeF;
    //размеры экрана
    int widthDisp;
    float halfWidthDisp;

    public tetrisDraw(Context context) {
        super(context);

        Log.i ("T1", "tetrisDraw");


        //создаем поле для игры
        this.fields = new FieldBlock[glassX][glassY];
        for (int i = 0; i < glassX; i++) {
            for (int j = 0; j < glassY; j++) {
                this.fields[i][j] = new FieldBlock(i, j, 0);
            }
        }

        //при необходимости создаем новую фигуру
        this.activeF = new ActiveFigure();
        this.activeF.setFields();


        //запуск игры
        MyTimer timer = new MyTimer();
        timer.start();
    }

    //размер экрана
    void setDistWidth (int wd) {
        this.widthDisp = wd;
        this.halfWidthDisp = wd/2;

        //отступ слева
        tetrisDraw.leftMargin = (int)(this.halfWidthDisp-((glassX/2) * tetrisDraw.blockWidth));
        Log.i ("T1", "leftMargin -> " + tetrisDraw.leftMargin);
    }

    public void KillLine() {
        int i, j;
        boolean f;

        for(i = 0; i < glassY; i++){
            f = true;

            for (j = 0; j < glassX; j++){

                if (this.fields[j][i].state == 0){
                    f = false;
                    break;
                }
            }

            if (f){

                for(j = 0; j < glassX; j++) this.fields[j][i].state = 0;

                for (int l = i-1; l >= 0; l--){

                    for (j = 0; j < glassX; j++){
                        if (this.fields[j][l].state == 2) {
                            this.fields[j][l].state = 0;
                            this.fields[j][l+1].state = 2;
                        }

                    }

                }

            }

        }

    }


    public boolean ifNotStuck_Move(int shiftX, int shiftY) {

        boolean stuck = false;

        if (this.activeF.points == null) {Log.i ("T1", "ifNotStuck_Move:this.activeF.points -> null");return false;}

        for (int i = 0; i < this.activeF.points.length; i++) {
            int newX = this.activeF.points[i].x + shiftX;
            int newY = this.activeF.points[i].y + shiftY;

            //проверка на касание дна или стенок
            if (newX < 0 || newX >= this.glassX)	{Log.i ("T1", "ifNotStuck_Move:WALL!!");return false;}
            if (newY >= this.glassY)	{stuck = true; break;}
            else {
                //проверка на касание фигуры
                if (this.fields[newX][newY].state == 2)	{stuck = true; break;}
            }
        }


        if (stuck) {
            //препятствие

            if (shiftX == 0 && shiftY > 0) {
                //остановка
                for (int i = 0; i < this.activeF.points.length; i++)	{
                    Log.i ("T1", this.activeF.points[i].x + "," + this.activeF.points[i].y);
                    this.fields[this.activeF.points[i].x][this.activeF.points[i].y].state = 2;
                }
                this.activeF = new ActiveFigure();
                this.activeF.setFields();
                Log.i ("T1", "this.activeF -> new");
            }

            KillLine();
            this.activeF = new ActiveFigure();
            this.activeF.setFields();
            Log.i ("T1", "this.activeF -> new");

        }
        else {
            //сдвигаем
            this.activeF.points = null;
            this.activeF.point = new FieldBlock(this.activeF.point.x + shiftX, this.activeF.point.y + shiftY);
            this.activeF.setFields();


            return true;
        }

        return false;

    }

    public void EndFigure(){

        if (this.fields[4][1].state == 2) {

            Context context = getContext();
            Intent i = new Intent(context, GameActivity.onStop);
            context.startActivity(i);

        }

    }


//    public void DropFigure() {
//
//
//
//    }


    @Override
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < glassX; i++) {
            for (int j = 0; j < glassY; j++) {
                //Log.i ("T1", "draw " + i + "," + j);
                this.fields[i][j].draw(canvas, this.activeF.points);
            }
        }
    }


    class MyTimer extends CountDownTimer {
        MyTimer() {
            super(100000, tetrisDraw.startDelay);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            ifNotStuck_Move(0, 1);
            invalidate();
        }

        @Override
        public void onFinish() {
            // TODO Auto-generated method stub
            Log.i ("T1", "onFinish");
            if (tetrisDraw.startDelay > 100 )	tetrisDraw.startDelay -= 10;
            MyTimer timer = new MyTimer();
            timer.start();

        }

    }


}
