/*
 * Copyright 2017 androidtools Jusenr
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jusenr.toolslibrary;

import android.os.Build;

/**
 * UncaughtException processing class, when the program has a Uncaught exception,
 * it has the class to take over the program and record the error report.
 * <p>
 * Created by sunnybear on 15/3/13.
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = CrashHandler.class.getSimpleName();

    //The default UncaughtException processing class of the system.
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    //CrashHandler instance.
    private static CrashHandler INSTANCE = new CrashHandler();
    //Custom exception handling callback.
    private OnCrashHandler mOnCrashHandler;
    //Format the date as part of the log file name
//    private static DateFormat format = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss", Locale.getDefault());

    /**
     * Ensure that there is only one instance of CrashHandler.
     */
    private CrashHandler() {
    }

    /**
     * Get the CrashHandler instance, singleton mode.
     *
     * @return CrashHandler instance
     */
    public static CrashHandler instance() {
        return INSTANCE;
    }

    /**
     * initialise
     */
    public void init(OnCrashHandler onCrashHandler) {
        mOnCrashHandler = onCrashHandler;
        //Gets the default UncaughtException processor for the system.
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        //Set the CrashHandler as the default processor for the program.
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable t) {
        if (handlerException(t) && mDefaultHandler != null)
            mDefaultHandler.uncaughtException(thread, t);//If the user is not processing, let the default processor of the system handle it.
        if (mOnCrashHandler != null)
            mOnCrashHandler.onCrashHandler(getPhoneInfo(), t);
    }

    /**
     * Custom error handling, error collection, error reporting, and other operations are completed here.
     *
     * @param t Exception
     * @return If this exception message is processed, otherwise true. is returned.
     */
    private boolean handlerException(Throwable t) {
        return false;
    }

    /**
     * Custom exception handling callback.
     */
    public interface OnCrashHandler {

        void onCrashHandler(String phoneInfo, Throwable e);
    }

    /**
     * Get device information
     *
     * @return device information
     */
    private String getPhoneInfo() {
        StringBuffer buffer = new StringBuffer();
        String brand = Build.BRAND;
        String model = Build.MODEL;
        String sdk = Build.VERSION.SDK;
        String release = Build.VERSION.RELEASE;
        buffer.append("BRAND:")
                .append(brand)
                .append("  MODEL:")
                .append(model)
                .append("  SDK:")
                .append(sdk)
                .append("  RELEASE:")
                .append(release)
                .append("\n");
        return buffer.toString();
    }
}