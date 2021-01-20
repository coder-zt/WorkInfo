package com.working.domain;

import android.graphics.Color;

/**
 * 需要显示状态的数据
 */
interface IStatusShow {
    /**
     * 状态描述
     * @return
     */
    String getStringShow();

    /**
     * 状态颜色
     * @return
     */
    int getColorShow();
}
