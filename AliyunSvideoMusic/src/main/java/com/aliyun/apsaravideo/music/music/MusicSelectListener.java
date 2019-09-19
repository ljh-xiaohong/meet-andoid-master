package com.aliyun.apsaravideo.music.music;

import com.aliyun.svideo.base.http.MusicFileBean;

public interface MusicSelectListener {
    void onMusicSelect(MusicFileBean musicFileBean, long startTime);
    void onCancel();
}
