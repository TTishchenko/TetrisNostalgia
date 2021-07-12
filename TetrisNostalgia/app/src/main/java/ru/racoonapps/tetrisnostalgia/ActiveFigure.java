package ru.racoonapps.tetrisnostalgia;


public class ActiveFigure {

    public int id, pos;
    public FieldBlock point;
    public FieldBlock points[];


    public ActiveFigure() {
        this.id = ActiveFigure.random(0, 3);
        this.pos = 0;
        this.point = new FieldBlock(5, 0);
    }

    public void setFields() {
        //положения фигур
        switch (this.id)
        {

            case 0:
                //_
                if (this.points == null) this.points = new FieldBlock[4];
                switch(this.pos){
                    case 0:
                        this.points[0] = new FieldBlock(this.point.x - 1, this.point.y);
                        this.points[1] = new FieldBlock(this.point.x, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x + 1, this.point.y);
                        this.points[3] = new FieldBlock(this.point.x + 2, this.point.y);
                        break;
                    case 1:
                        this.points[0] = new FieldBlock(this.point.x, this.point.y - 1);
                        this.points[1] = new FieldBlock(this.point.x, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x, this.point.y + 1);
                        this.points[3] = new FieldBlock(this.point.x, this.point.y + 2);
                        break;
                }


                //Log.e ("T1", "F_");
                break;
            case 1:
                //Z
                if (this.points == null) this.points = new FieldBlock[4];
                switch(this.pos){
                    case 0:
                        this.points[0] = new FieldBlock(this.point.x - 1, this.point.y);
                        this.points[1] = new FieldBlock(this.point.x, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x, this.point.y + 1);
                        this.points[3] = new FieldBlock(this.point.x + 1, this.point.y + 1);
                        break;
                    case 1:
                        this.points[0] = new FieldBlock(this.point.x, this.point.y - 1);
                        this.points[1] = new FieldBlock(this.point.x, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x - 1, this.point.y);
                        this.points[3] = new FieldBlock(this.point.x - 1, this.point.y + 1);
                        break;
                }


                // Log.e ("T1", "FZ");
                break;
            case 2:
                //Г
                if (this.points == null) this.points = new FieldBlock[4];

                switch(this.pos){
                    case 0:
                        this.points[0] = new FieldBlock(this.point.x, this.point.y);
                        this.points[1] = new FieldBlock(this.point.x - 1, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x + 1, this.point.y);
                        this.points[3] = new FieldBlock(this.point.x + 1, this.point.y + 1);
                        break;
                    case 1:
                        this.points[0] = new FieldBlock(this.point.x, this.point.y);
                        this.points[1] = new FieldBlock(this.point.x, this.point.y - 1);
                        this.points[2] = new FieldBlock(this.point.x, this.point.y + 1);
                        this.points[3] = new FieldBlock(this.point.x - 1, this.point.y + 1);
                        break;
                    case 2:
                        this.points[0] = new FieldBlock(this.point.x - 1, this.point.y - 1);
                        this.points[1] = new FieldBlock(this.point.x - 1, this.point.y);
                        this.points[2] = new FieldBlock(this.point.x, this.point.y);
                        this.points[3] = new FieldBlock(this.point.x + 1, this.point.y);
                        break;
                    case 3:
                        this.points[0] = new FieldBlock(this.point.x, this.point.y - 1);
                        this.points[1] = new FieldBlock(this.point.x + 1, this.point.y - 1);
                        this.points[2] = new FieldBlock(this.point.x, this.point.y);
                        this.points[3] = new FieldBlock(this.point.x, this.point.y + 1);
                        break;
                }


                // Log.e ("T1", "FГ");
                break;
            case 3:
                //[]
                if (this.points == null) this.points = new FieldBlock[4];
                this.points[0] = new FieldBlock(this.point.x - 1, this.point.y);
                this.points[1] = new FieldBlock(this.point.x, this.point.y);
                this.points[2] = new FieldBlock(this.point.x - 1, this.point.y + 1);
                this.points[3] = new FieldBlock(this.point.x, this.point.y + 1);


                // Log.e ("T1", "F[]");
                break;
        }

    }


    public void changePos() {
        //изменение положения фигуры
        switch (this.id)
        {

            case 0:
                //_
                this.pos = (this.pos == 1) ? 0 : 1;
                break;
            case 1:
                //Z
                this.pos = (this.pos == 1) ? 0 : 1;
                break;
            case 2:
                //Г

                switch(this.pos){
                    case 0:
                        this.pos = 1;
                        break;
                    case 1:
                        this.pos = 2;
                        break;
                    case 2:
                        this.pos = 3;
                        break;
                    default:
                        this.pos = 0;
                        break;
                }

                break;
            case 3:
                //[]
                break;
        }

    }

    public static int random(int min, int max) {
        return (int) (Math.random() * (max - min + 1)) + min;
    }


}
