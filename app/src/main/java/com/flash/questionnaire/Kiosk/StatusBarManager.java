package com.flash.questionnaire.Kiosk;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.WindowManager;

/**
 * Created by Anton on 23.09.2015.
 */
public class StatusBarManager {

    private CustomViewGroup view;
    private WindowManager manager;
    private Context context;

//    public StatusBarManager INSTANCE = new StatusBarManager();

    public StatusBarManager(Context context) {
        this.context = context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public void hide() {

     //   ActivityManager activityManager = (ActivityManager) context
     //           .getSystemService(Context.ACTIVITY_SERVICE);

     //   activityManager.moveTaskToFront(context.getTaskId(), 0);



        manager = ((WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE));

        WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
        localLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;
        localLayoutParams.gravity = Gravity.TOP;
        localLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE |

                // this is to enable the notification to recieve touch events
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |

                // Draws over status bar
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN;

        localLayoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        localLayoutParams.height = (int) (50 * context.getResources()
                .getDisplayMetrics().scaledDensity);
        localLayoutParams.format = PixelFormat.TRANSPARENT;

        view = new CustomViewGroup(context);

        manager.addView(view, localLayoutParams);
    }

    public void show() {

        if(view!=null) manager.removeView(view);
    }
}
