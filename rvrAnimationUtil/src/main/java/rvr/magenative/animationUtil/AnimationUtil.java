package rvr.magenative.animationUtil;


import android.app.Activity;

public class AnimationUtil {


    public static void activityIntentAnimation(Activity activity,IntentAnimation animation){

        switch (animation){
            case UP_TO_DOWN:
                activity.overridePendingTransition(R.anim.slide_up_animation,R.anim.slide_in_animation);
                break;
            case DOWN_TO_UP:
                activity.overridePendingTransition(R.anim.slide_down_animation,R.anim.slide_in_animation);
                break;
            case LEFT_TO_RIGHT:
                activity.overridePendingTransition(R.anim.slide_right_animation,R.anim.slide_in_animation);
                break;
            case RIGHT_TO_LEFT:
                activity.overridePendingTransition(R.anim.slide_left_animation,R.anim.slide_in_animation);
                break;
            case FLIP_RIGHT:
                activity.overridePendingTransition(R.anim.flip_animation,R.anim.fade_out_animation);
                break;
            case FADE_IN:
                activity.overridePendingTransition(R.anim.fade_in_animation,R.anim.fade_out_animation);
                break;
            case FADE_OUT:
                activity.overridePendingTransition(R.anim.fade_out_animation,R.anim.fade_in_animation);
                break;
            default:
        }

    }









}
