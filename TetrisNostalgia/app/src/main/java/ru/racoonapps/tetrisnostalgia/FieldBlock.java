package ru.racoonapps.tetrisnostalgia;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class FieldBlock {

    public int x, y;
    private int xPX, yPX;
    public int state;

    /* êîíñòðóêòîðû */
    public FieldBlock(int x, int y) {
        this.x = x;
        this.y = y;
        this.xPX = x*tetrisDraw.blockWidth;
        this.yPX = y*tetrisDraw.blockWidth;
        this.state = 0;
    }

    public FieldBlock(int x, int y, int st) {
        this.x = x;
        this.y = y;
        this.xPX = x*tetrisDraw.blockWidth;
        this.yPX = y*tetrisDraw.blockWidth;
        this.state = st;
    }
	/* êîíñòðóêòîðû end*/

    public void draw(Canvas canvas, FieldBlock[] activeFields) {
        Paint paint = new Paint();

        int color = Color.BLACK;
        boolean stActive = false;

        if (this.state == 2) color = Color.GRAY;
        else	{

            for (int i = 0; i < activeFields.length; i++)	{
                if (activeFields[i].x == this.x && activeFields[i].y == this.y) {
                    color = Color.RED;
                    stActive = true;
                    break;

                }
            }
        }

        paint.setColor(color);
        canvas.drawRect (this.xPX + tetrisDraw.leftMargin, this.yPX + tetrisDraw.topMargin, this.xPX + tetrisDraw.leftMargin + tetrisDraw.blockWidth, this.yPX + tetrisDraw.topMargin + tetrisDraw.blockWidth, paint);
        //разметка поля
        if (this.state == 0 && !stActive)	{
            paint.setColor(Color.WHITE);
            canvas.drawCircle(this.xPX+tetrisDraw.leftMargin + tetrisDraw.blockWidth/2, this.yPX + tetrisDraw.topMargin + tetrisDraw.blockWidth/2, 2, paint);
        }
    }



}

