package com.lcgao.music_module.music;

import android.media.AudioManager;
import android.media.MediaPlayer;

import com.lcgao.music_module.util.LogUtil;

import java.io.IOException;

public class MediaManager {
    private static final String TAG = "MediaManager: ";

    private static MediaPlayer mMediaPlayer;
    private static boolean isPause;
    private static String mFilePath;
    public static void playMusic(String filePath,
                                 MediaPlayer.OnCompletionListener onCompletionListener){
        mFilePath = filePath;
        LogUtil.d("MediaManager playMusic...  " + mFilePath);
        if(mMediaPlayer == null){
            mMediaPlayer = new MediaPlayer();
            mMediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaPlayer.reset();
                    return false;
                }
            });
        }else {
            mMediaPlayer.reset();
        }

        try {
            mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaPlayer.setOnCompletionListener(onCompletionListener);
            mMediaPlayer.setDataSource(filePath);
            mMediaPlayer.prepareAsync();
            mMediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                }
            });

        }catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void pause(){
        LogUtil.d("MediaManager pause...  " + mFilePath);

        if(mMediaPlayer != null && mMediaPlayer.isPlaying()){
            mMediaPlayer.pause();
            isPause = true;
        }
    }

    public static void resume(){
        LogUtil.d("MediaManager resume...  " + mFilePath);

        if(mMediaPlayer != null && isPause){
            mMediaPlayer.start();
            isPause = false;
        }
    }

    public static void release(){
        LogUtil.d("MediaManager release...  " + mFilePath);

        if(mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    public static boolean isPause() {
        return isPause;
    }

    public static void setIsPause(boolean isPause) {
        MediaManager.isPause = isPause;
    }

    /**
     * 指定播放的位置
     * @param msec 播放位置时间
     */
    public static void seekTo(int msec){
        if(mMediaPlayer != null) {
            mMediaPlayer.seekTo(msec);
        }
    }

    public static long getCurrentTime(){
        if(mMediaPlayer != null){
            LogUtil.d(TAG + "getCurrentPosition=" + mMediaPlayer.getCurrentPosition());
            return mMediaPlayer.getCurrentPosition();
        }
        return 0;
    }
}
