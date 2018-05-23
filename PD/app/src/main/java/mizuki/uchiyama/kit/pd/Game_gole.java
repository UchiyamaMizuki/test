package mizuki.uchiyama.kit.pd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;

/**
 * Created by uchiyan on 2017/11/24.
 */

public class Game_gole extends Activity{
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
        setContentView(R.layout.gamegole);//layoutの設定

        Button gamegolemenu =(Button)findViewById(R.id.gamegolemenu);
        gamegolemenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent gamegolemenu = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(gamegolemenu);
            }
        });
    }
}
