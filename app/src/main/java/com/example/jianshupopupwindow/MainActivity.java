package com.example.jianshupopupwindow;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
	private LinearLayout firstView;
	private LinearLayout secondView;
	private int fHeight;
	private int sHeight;
	private Button mShowButton;
	private Button mHiddenButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		firstView = (LinearLayout)findViewById(R.id.first_view);
		secondView = (LinearLayout)findViewById(R.id.second_view);
		mShowButton = (Button)findViewById(R.id.btn_anim3_show);
		mHiddenButton = (Button)findViewById(R.id.btn_anim3_hidden);
		firstView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	            @Override
	            public void onGlobalLayout() {
	                                                                                                                                                                                                                                      
	            	fHeight = firstView.getHeight();
	            	firstView.getViewTreeObserver()
	                        .removeOnGlobalLayoutListener(this);
	            }
		});
		secondView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
	            @Override
	            public void onGlobalLayout() {
	                                                                                                                                                                                                                                      
	            	sHeight = secondView.getHeight();
	            	secondView.getViewTreeObserver()
	                        .removeOnGlobalLayoutListener(this);
	            }
		});	
		
		mShowButton.setOnClickListener(new View.OnClickListener() {
			@Override			
			public void onClick(View v){
				initShowAnim();
			}
		});
		mHiddenButton.setOnClickListener(new View.OnClickListener() {
			@Override			
			public void onClick(View v){
				initHiddenAnim();
			}
		});		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
 
	private void initShowAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",1.0f,0.8f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",1.0f,0.8f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",1.0f,0.5f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(firstView,"translationY",0,-0.1f* fHeight);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",sHeight,0);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                secondView.setVisibility(View.VISIBLE);
            }
        });
        AnimatorSet showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
        showAnim.start();
    }	
	 
	private void initHiddenAnim(){
        ObjectAnimator fViewScaleXAnim=ObjectAnimator.ofFloat(firstView,"scaleX",0.8f,1.0f);
        fViewScaleXAnim.setDuration(350);
        ObjectAnimator fViewScaleYAnim=ObjectAnimator.ofFloat(firstView,"scaleY",0.8f,1.0f);
        fViewScaleYAnim.setDuration(350);
        ObjectAnimator fViewAlphaAnim=ObjectAnimator.ofFloat(firstView,"alpha",0.5f,1.0f);
        fViewAlphaAnim.setDuration(350);
        ObjectAnimator fViewRotationXAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 0f, 10f);
        fViewRotationXAnim.setDuration(200);
        ObjectAnimator fViewResumeAnim = ObjectAnimator.ofFloat(firstView, "rotationX", 10f, 0f);
        fViewResumeAnim.setDuration(150);
        fViewResumeAnim.setStartDelay(200);
        ObjectAnimator fViewTransYAnim=ObjectAnimator.ofFloat(firstView,"translationY",-0.1f* fHeight,0);
        fViewTransYAnim.setDuration(350);
        ObjectAnimator sViewTransYAnim=ObjectAnimator.ofFloat(secondView,"translationY",0,sHeight);
        sViewTransYAnim.setDuration(350);
        sViewTransYAnim.addListener(new AnimatorListenerAdapter() {  
            @Override  
            public void onAnimationEnd(Animator animation) {  
            	 super.onAnimationEnd(animation);
            	 secondView.setVisibility(View.INVISIBLE);
            }              
        });
        AnimatorSet showAnim=new AnimatorSet();
        showAnim.playTogether(fViewScaleXAnim,fViewRotationXAnim,fViewResumeAnim,fViewTransYAnim,fViewAlphaAnim,fViewScaleYAnim,sViewTransYAnim);
        showAnim.start();
    }	
}
