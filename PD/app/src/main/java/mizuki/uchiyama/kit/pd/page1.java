package mizuki.uchiyama.kit.pd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by uchiyan on 2017/12/05.
 */

public class page1 extends Activity{
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page1);

        final Button modoru = (Button) findViewById(R.id.page1_menu);
        modoru.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent modoru = new Intent(getApplicationContext(), kankouchi.class);
                startActivity(modoru);
            }
        });


    }
}
