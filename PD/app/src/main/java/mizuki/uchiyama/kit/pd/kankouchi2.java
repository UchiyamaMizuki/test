package mizuki.uchiyama.kit.pd;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * Created by uchiyan on 2017/12/10.
 */

public class kankouchi2 extends Activity {
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
        setContentView(R.layout.kankouchi2);

        Button kankouchi1 = (Button) findViewById(R.id.kankouchi2_modoru);
        kankouchi1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kankouchi1 = new Intent(getApplicationContext(), kankouchi.class);
                startActivity(kankouchi1);
            }
        });

        Button kankouchi2 = (Button) findViewById(R.id.tugi2);
        kankouchi2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kankouchi2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(kankouchi2);
            }
        });
        Button page7 = (Button) findViewById(R.id.page7);
        page7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page7 = new Intent(getApplicationContext(), page7.class);
                startActivity(page7);
            }
        });

        ImageButton inuwashi = (ImageButton) findViewById(R.id.inuwashi);
        inuwashi.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent inuwashi= new Intent(getApplicationContext(), page7.class);
                startActivity(inuwashi);
            }
        });
        Button page8 = (Button) findViewById(R.id.page8);
        page8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page8 = new Intent(getApplicationContext(), page8.class);
                startActivity(page8);
            }
        });

        ImageButton  uramatu= (ImageButton) findViewById(R.id.suematu);
        uramatu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent uramatu= new Intent(getApplicationContext(), page8.class);
                startActivity(uramatu);
            }
        });
        Button page9 = (Button) findViewById(R.id.page9);
        page9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page9 = new Intent(getApplicationContext(), page9.class);
                startActivity(page9);
            }
        });

        ImageButton zyuutaku = (ImageButton) findViewById(R.id.zyuutaku);
        zyuutaku.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent zyuutaku= new Intent(getApplicationContext(), page9.class);
                startActivity(zyuutaku);
            }
        });
        Button page10 = (Button) findViewById(R.id.page10);
        page10.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page10 = new Intent(getApplicationContext(), page10.class);
                startActivity(page10);
            }
        });

        ImageButton nunonuno = (ImageButton) findViewById(R.id.nunonuno);
        nunonuno.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nunonuno= new Intent(getApplicationContext(), page10.class);
                startActivity(nunonuno
                );
            }
        });

    }
}