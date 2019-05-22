package com.whty.eschoolbag.draft;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * @author lu
 * @emil lhqwust@163.com
 * create at 2019/5/21 17:46
 * description:
 */
public class Draft {

    private static final String TAG = "draft";
    private AttachButton attachButton;
    private FrameLayout contentView;
    private Activity context;
    private ViewPager viewPager;
    private int currentFrgIndex;
    private boolean isReocrdHistory;


    public void attach(Activity context) {
        this.attach(context, false);
    }

    public void attach(Activity context, boolean isReocrdHistory) {
        this.context = context;
        this.isReocrdHistory = isReocrdHistory;
        addLifeListener(context);
    }


    public void setViewPager(ViewPager viewPager) {
        this.viewPager = viewPager;
    }


    public void setCurrentFrgIndex(int currentFrgIndex) {
        this.currentFrgIndex = currentFrgIndex;
    }

    private void addLifeListener(Activity activity) {
        LifeListenerFragment fragment = getLifeListenerFragment(activity);
        fragment.addLifeListener(mLifeListener);
    }

    private LifeListenerFragment getLifeListenerFragment(Activity activity) {
        FragmentManager manager = activity.getFragmentManager();
        LifeListenerFragment fragment = (LifeListenerFragment) manager.findFragmentByTag(TAG);
        if (fragment == null) {
            fragment = new LifeListenerFragment();
            manager.beginTransaction().add(fragment, TAG).commitAllowingStateLoss();
        }
        return fragment;
    }


    private LifeListener mLifeListener = new LifeListener() {

        @Override
        public void onCreate() {
            addDraftView();
        }

        @Override
        public void onStart() {
        }

        @Override
        public void onResume() {
            attachButton.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPause() {
            attachButton.setVisibility(View.GONE);
        }

        @Override
        public void onStop() {
        }

        @Override
        public void onDestroy() {
            removeDraftView();
            CanvasRecord.getRecord().release();
        }
    };


    private void addDraftView() {
        contentView = (FrameLayout) context.findViewById(android.R.id.content);

        attachButton = new AttachButton(context);
        FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT;

        contentView.addView(attachButton, lp);


        attachButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewPager != null) {
                    currentFrgIndex = viewPager.getCurrentItem();
                }
                CanvasActivity.launch(context, currentFrgIndex, isReocrdHistory);
            }
        });
    }

    private void removeDraftView() {
        if (contentView != null)
            contentView.removeView(attachButton);
    }


}
