package mizuki.uchiyama.kit.pd;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class kankouchi extends AppCompatActivity {

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
        setContentView(R.layout.activity_kankouchi);

        Button kankouchi1 =(Button)findViewById(R.id.kankouchi_menu);
        kankouchi1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kankouchi1 = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(kankouchi1);
            }
        });

        Button kankouchi2 =(Button)findViewById(R.id.tugi);
        kankouchi2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kankouchi2 = new Intent(getApplicationContext(),kankouchi2.class);
                startActivity(kankouchi2);
            }
        });

        ImageButton kit =(ImageButton)findViewById(R.id.kit1);
        kit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent kit = new Intent(getApplicationContext(),page1.class);
                startActivity(kit);
            }
        });

        Button page1 =(Button)findViewById(R.id.page1);
        page1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page1 = new Intent(getApplicationContext(),page1.class);
                startActivity(page1);
            }
        });
        ImageButton iseki =(ImageButton)findViewById(R.id.iseki);
        iseki.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent iseki = new Intent(getApplicationContext(),page2.class);
                startActivity(iseki);
            }
        });
        Button page2 =(Button)findViewById(R.id.page2);
        page2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page2 = new Intent(getApplicationContext(),page2.class);
                startActivity(page2);
            }
        });
        ImageButton siryoukan =(ImageButton)findViewById(R.id.siryoukan);
        siryoukan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent siryoukan = new Intent(getApplicationContext(),page3.class);
                startActivity(siryoukan);
            }
        });
        Button page3 =(Button)findViewById(R.id.page3);
        page3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page3 = new Intent(getApplicationContext(),page3.class);
                startActivity(page3);
            }
        });

        ImageButton rekisikan =(ImageButton)findViewById(R.id.rekisikan);
        rekisikan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent rekisikan = new Intent(getApplicationContext(),page4.class);
                startActivity(rekisikan);
            }
        });
        Button page4 =(Button)findViewById(R.id.page4);
        page4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page4 = new Intent(getApplicationContext(),page4.class);
                startActivity(page4);
            }
        });

        ImageButton tyankare =(ImageButton)findViewById(R.id.tyankare);
        tyankare.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent tyanakare = new Intent(getApplicationContext(),page5.class);
                startActivity(tyanakare);
            }
        });
        Button page5 =(Button)findViewById(R.id.page5);
        page5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page5 = new Intent(getApplicationContext(),page5.class);
                startActivity(page5);
            }
        });

        ImageButton zyonkara =(ImageButton)findViewById(R.id.zyonkara);
        zyonkara.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent zyonkara = new Intent(getApplicationContext(),page6.class);
                startActivity(zyonkara);
            }
        });
        Button page6 =(Button)findViewById(R.id.page6);
        page6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent page6 = new Intent(getApplicationContext(),page6.class);
                startActivity(page6);
            }
        });
    }

}
