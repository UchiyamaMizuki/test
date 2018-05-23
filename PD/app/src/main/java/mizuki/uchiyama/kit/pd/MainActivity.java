package mizuki.uchiyama.kit.pd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements game.Callback,game.Callback2{
        private game game;
    Handler handler = new Handler();
    Handler handler2 = new Handler();
    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {///////戻るボタンの機能停止
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        //タイトルバーを非表示にする（必ずsetContentViewの前にすること）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//layoutの設定

        Button gamebutton =(Button)findViewById(R.id.gamebutton);
        gamebutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setscreen();
            }
        });

        Button kankouchi =(Button)findViewById(R.id.kankouchi);
        kankouchi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kankouchi = new Intent(getApplicationContext(),kankouchi.class);
                startActivity(kankouchi);
            }
        });

         /*Button sukoa =(Button)findViewById(R.id.sukoa);
        sukoa.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent sukoa = new Intent(getApplicationContext(),sukoa.class);
                startActivity(sukoa);
            }
        });*/
    }

    private void setscreen() {
        game = new game(this);
        game.setCallback(this);
        game.setCallback2(this);
        setContentView(game);
    }

    class hand implements Runnable{
        public void run(){
            Intent intent = new Intent(MainActivity.this,Game_fin.class);
            startActivity(intent);
            }

    }


    public void onGameOver() {
        Toast.makeText(this, "GameOver!", Toast.LENGTH_LONG).show();
        handler.postDelayed(new hand(),5000);

    }

    public void onGameGole() {
        Toast.makeText(this, "Gole!", Toast.LENGTH_LONG).show();
        handler2.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gamegole = new Intent(MainActivity.this, Game_gole.class);
                startActivity(gamegole);
            }
        }, 5000);
    }

}