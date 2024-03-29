package com.yuejian.meet.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.yuejian.meet.R;

public class VideoPlayer extends StandardGSYVideoPlayer {


    public VideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public VideoPlayer(Context context) {
        super(context);
        mDismissControlTime = 0;
    }

    public VideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);

        //改变进度条位置
//        RelativeLayout.LayoutParams layoutParams1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getContext().getResources().getDisplayMetrics()));
//        layoutParams1.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        mBottomProgressBar.setMax(100);
//        mBottomProgressBar.setProgressDrawable(getContext().getResources().getDrawable(R.drawable.progressbar_bg));
//        mBottomProgressBar.setLayoutParams(layoutParams1);
        mDismissControlTime = 0;


    }


    @Override
    protected void init(Context context) {
        super.init(context);


    }

    public View getMoreButton() {
        return this.findViewById(R.id.video_more);
    }

    public View getDiscussButton() {
        return this.findViewById(R.id.video_discuss);
    }


    @Override
    public int getLayoutId() {
        return R.layout.widget_video_player;
    }

//    @Override
//    public void startPlayLogic() {
////        setSeekOnStart(0);
//        if (mVideoAllCallBack != null) {
//            Debuger.printfLog("onClickStartThumb");
//            mVideoAllCallBack.onClickStartThumb(mOriginUrl, mTitle, VideoPlayer.this);
//        }
//        prepareVideo();
//        startDismissControlViewTimer();
//    }


    /**
     * 隐藏的控件
     */
    @Override
    protected void hideAllWidget() {
//        super.hideAllWidget();
        setViewShowState(mTitleTextView, GONE);
        setViewShowState(mBackButton, GONE);
        setViewShowState(mLoadingProgressBar, GONE);
        setViewShowState(mCurrentTimeTextView, GONE);
        setViewShowState(mTotalTimeTextView, GONE);
        setViewShowState(mProgressBar, GONE);
        setViewShowState(mFullscreenButton, GONE);
        setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mBottomProgressBar, VISIBLE);
        setViewShowState(mTopContainer, GONE);
        setViewShowState(mBottomContainer, GONE);
    }

    /**
     * 正常时的状态
     */
    @Override
    protected void changeUiToNormal() {
        super.changeUiToNormal();
        setViewShowState(mTopContainer, GONE);
        setViewShowState(mBottomContainer, GONE);

    }


    /**
     * 准备时的状态
     */
    @Override
    protected void changeUiToPreparingShow() {
        super.changeUiToPreparingShow();
        setViewShowState(mTopContainer, GONE);
        setViewShowState(mBottomContainer, GONE);
    }

    /**
     * 开始时的状态
     */
    @Override
    protected void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        setViewShowState(mTopContainer, GONE);
        setViewShowState(mBottomContainer, GONE);
    }

    /**
     * 暂停时的状态
     */
    @Override
    protected void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        setViewShowState(mTopContainer, GONE);
        setViewShowState(mBottomContainer, GONE);
        setViewShowState(mStartButton, VISIBLE);
    }

    /**
     * 错误时的状态
     */
    @Override
    protected void changeUiToError() {

    }

    /**
     * 完成时的状态
     */
    @Override
    protected void changeUiToCompleteShow() {

    }

    /**
     * 获取数据时的状态
     */
    @Override
    protected void changeUiToPlayingBufferingShow() {
        super.changeUiToPlayingBufferingShow();
        mCurrentPosition = 0;
        resetProgressAndTime();

    }

    /**
     * 网络WIFI
     */
    @Override
    protected void showWifiDialog() {

    }
//

    /**
     * 触摸进度
     */

    @Override
    protected void showProgressDialog(float deltaX, String seekTime, int seekTimePosition, String totalTime, int totalTimeDuration) {

    }
//

    /**
     * 触摸进度
     */

    @Override
    protected void dismissProgressDialog() {

    }
//

    /**
     * 声量
     */
    @Override
    protected void showVolumeDialog(float deltaY, int volumePercent) {

    }
//

    /**
     * 声量
     */
    @Override
    protected void dismissVolumeDialog() {

    }
//

    /**
     * 亮度
     */

    @Override
    protected void showBrightnessDialog(float percent) {

    }

    /**
     * 亮度
     */

    @Override
    protected void dismissBrightnessDialog() {

    }
//


    @Override
    protected void touchDoubleUp() {
//        super.touchDoubleUp();
//        switch (mCurrentState) {
//            case CURRENT_STATE_PAUSE:
//                setViewShowState(mStartButton, VISIBLE);
//                Log.e("touch","CURRENT_STATE_PAUSE");
//
//                break;
//
//            case CURRENT_STATE_PLAYING:
//                setViewShowState(mStartButton, GONE);
//                //暂停播放
//                Log.e("touch","CURRENT_STATE_PLAYING");
//
//                break;
//        }
    }

    /**
     * 点击触摸显示和隐藏逻辑(只限界面显示逻辑)
     */
//    @Override
    protected void onClickUiToggle() {


        switch (mCurrentState) {
            case CURRENT_STATE_PAUSE:
                try {
                    mCurrentState = CURRENT_STATE_PLAYING;
                    getGSYVideoManager().start();
                    setViewShowState(mBottomProgressBar, VISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;

            case CURRENT_STATE_PLAYING:

                try {
                    onVideoPause();
                    setViewShowState(mBottomProgressBar, VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }


                break;
        }


    }


    protected void updateStartImage() {


        if (mStartButton != null) {
            if (mStartButton instanceof ImageView) {
                switch (mCurrentState) {
                    case CURRENT_STATE_PLAYING:
                        setViewShowState(mStartButton, GONE);
                        break;

                    case CURRENT_STATE_PAUSE:
                        setViewShowState(mStartButton, VISIBLE);
                        break;

                }

            }
        }
    }
}
