package com.example.project_1;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.WindowDecorActionBar;

public class PopUpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        //타이틀바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //UI 객체 생성

        //데이터 가져오기

        //닫기 버튼 클릭시 팝업 닫기

    }

    //바깥 레이어 클릭시 안닫히게
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {
            return false;
        }
        return true;
    }
}
