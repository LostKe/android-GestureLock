package zs.com.gesturelock;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import zs.com.gesturelock.widget.DotViewGroup;
import zs.com.gesturelock.widget.GestureEnum;
import zs.com.gesturelock.widget.GestureLockViewGroup;

/**
 * Created by zhangshuqing on 16/9/24.
 */
public class GestureEditActivity extends AppCompatActivity implements View.OnClickListener{

    boolean hasEdit=true;

    List<Integer> answer=new ArrayList<Integer>();

    GestureLockViewGroup gesture_edit_lockView;

    DotViewGroup dotViewGroup;

    TextView resetTv;

    TextView gesture_edit_tip;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_edit);
        initView();
    }

    private void initView() {
        resetTv= (TextView) findViewById(R.id.rest_tv);
        resetTv.setOnClickListener(this);
        gesture_edit_tip= (TextView) findViewById(R.id.gesture_edit_tip);
        dotViewGroup= (DotViewGroup) findViewById(R.id.dotView);

        gesture_edit_lockView= (GestureLockViewGroup) findViewById(R.id.gesture_edit_lockView);
        gesture_edit_lockView.setGestureType(GestureEnum.EDIT);
        gesture_edit_lockView.setOnGestureLockViewListener(new GestureLockViewGroup.OnGestureLockViewListener() {
            @Override
            public void onBlockSelected(int cId) {

            }

            @Override
            public void onGestureEvent(boolean matched) {

            }

            @Override
            public void onUnmatchedExceedBoundary() {

            }

            @Override
            public void onChooseWay(List<Integer> list) {
                if(list==null || list.size()==0){
                    return;
                }
                if(hasEdit){//第一次编辑
                    dotViewGroup.setPath(list);
                    hasEdit=false;
                    answer.addAll(list);
                    resetTv.setVisibility(View.VISIBLE);

                }else{
                    //比较和第一的手势
                    if(compare(list,answer)){
                        //两次一致
                        Toast.makeText(GestureEditActivity.this,"设置成功",Toast.LENGTH_SHORT).show();
                        //TODO
                    }else {
                        //提示不一致
                        makeTip(false);
                    }

                }



            }
        });

    }

    public static  boolean compare(List<Integer> a, List<Integer> b) {
        if(a.size() != b.size()){
            return false;
        }

        for(int i=0;i<a.size();i++){
            if(!a.get(i).equals(b.get(i))){
                return false;
            }

        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rest_tv:
                resetTv.setVisibility(View.INVISIBLE);
                answer.clear();
                hasEdit=true;
                dotViewGroup.reset();
                makeTip(true);
                break;
        }
    }

    private void makeTip(boolean b){
        if(b){
            gesture_edit_tip.setText("绘制解锁图案");
            gesture_edit_tip.setTextColor(0xFF979999);
        }else{
            gesture_edit_tip.setText("两次绘制图案不一致，请重新绘制");
            gesture_edit_tip.setTextColor(Color.RED);
            Animation animation = AnimationUtils.loadAnimation(GestureEditActivity.this,R.anim.shake);
            gesture_edit_tip.startAnimation(animation);
        }
    }
}