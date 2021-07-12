package ru.racoonapps.tetrisnostalgia;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;


public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_game);


        //ñîçäàåì âíåøíèé layout, ïîìåùàåì â íåãî tetrisDraw è gameWidgets - View ñ êíîïêàìè
        FrameLayout game = new FrameLayout(this);
        game.setBackgroundColor(R.color.green);
        final tetrisDraw mView = new tetrisDraw(this);
        RelativeLayout gameWidgets = new RelativeLayout (this);

        //кнопка - вниз
        Button dropBt = new Button(this);
        dropBt.setId(R.id.dropBtID);
        dropBt.setWidth(300);
        dropBt.setHeight(100);
        dropBt.setText("Drop");
        dropBt.setBackgroundColor(Color.GRAY);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        dropBt.setLayoutParams(params);
        gameWidgets.addView(dropBt);

        //кнопка - поворот
        Button rotateBt = new Button(this);
        rotateBt.setWidth(300);
        rotateBt.setId(R.id.rotateBtID);
        rotateBt.setHeight(100);
        rotateBt.setText("Rotate");
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        rotateBt.setLayoutParams(params);
        gameWidgets.addView(rotateBt);

        //кнопка - влево
        Button leftBt = new Button(this);
        leftBt.setId(R.id.leftBtID);
        leftBt.setWidth(300);
        leftBt.setHeight(100);
        leftBt.setText("Left");
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, rotateBt.getId());
        leftBt.setLayoutParams(params);
        gameWidgets.addView(leftBt);

        //кнопка - вправо
        Button rightBt = new Button(this);
        rightBt.setId(R.id.rightBtID);
        rightBt.setWidth(300);
        rightBt.setHeight(100);
        rightBt.setText("Right");
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, rotateBt.getId());
        rightBt.setLayoutParams(params);
        gameWidgets.addView(rightBt);

        Button test = new Button(this);
        test.setId(R.id.testID);
        test.setWidth(100);
        test.setHeight(100);
        test.setText("");
        test.setBackgroundColor(R.color.green);
        params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        params.addRule(RelativeLayout.ABOVE, rotateBt.getId());
        rightBt.setLayoutParams(params);
        gameWidgets.addView(test);

        // действия кнопок
        rightBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i ("T1", "onClick -> rightBt");
                mView.ifNotStuck_Move(1, 0);
            }
        });
        leftBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i ("T1", "onClick -> leftBt");
                mView.ifNotStuck_Move(-1, 0);
            }
        });
        rotateBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("T1", "onClick -> rotateBt");
                mView.activeF.changePos();
            }
        });
        dropBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.i("T1", "onClick -> dropBt");
                // mView.DropFigure();
            }
        });
        test.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(GameActivity.this, EndActivity.class);
                startActivity(intent);
            }
        });




        //ïîëó÷àåì ðàçìåðû ýêðàíà
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        mView.setDistWidth (size.x);

        //ïîäêëþ÷àåì Views
        game.addView(mView);
        game.addView(gameWidgets);
        setContentView(game);

//        public static void StAc() {
//            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
//            startActivity(intent);
//        }

    }

    @Override
    protected void onPause(){

        super.onPause();

    }

    @Override
    protected void onResume(){

        super.onResume();

    }

    @Override
    protected void onStop(){

        super.onStop();

        Intent intent = new Intent(GameActivity.this, EndActivity.class);
        startActivity(intent);

    }



}
