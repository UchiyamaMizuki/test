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

/**
 * Created by uchiyan on 2017/11/07.
 */

public class Game_fin extends Activity implements game.Callback{
    private game game;
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
    protected void onCreate(Bundle savedInstanceState) {
        //タイトルバーを非表示にする（必ずsetContentViewの前にすること）
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_fin);//layoutの設定

        Button gamestart =(Button)findViewById(R.id.gamestart);
        gamestart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setscreen();
            }
        });

        Button menu =(Button)findViewById(R.id.menu);
        menu.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
                Intent menu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(menu);
            }
        });

    }
    private void setscreen() {
        game = new game(this);
        game.setCallback(this);
        setContentView(game);
    }

    class hand implements Runnable{
        public void run(){
            Intent intent= new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }

    public void onGameOver() {
        Handler handler=new Handler();
        Toast.makeText(this, "GameOver!", Toast.LENGTH_LONG).show();
        handler.postDelayed(new hand(),5000);

    }
}
