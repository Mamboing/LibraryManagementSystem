package com.example.librarymanagementsystem.util

import android.util.Log

object LogUtil {
    const val VERBOSE = 1
    const val DEBUG = 2
    const val INFO = 3
    const val WARN = 4
    const val ERROR = 5
    const val NOTHING = 6
    var level = VERBOSE
    fun v(tag: String?, msg: String?) {
        if (level <= VERBOSE) {
            Log.v(tag, msg!!)
        }
    }

    fun d(tag: String?, msg: String?) {
        if (level <= DEBUG) {
            Log.d(tag, msg!!)
        }
    }

    fun i(tag: String?, msg: String?) {
        if (level <= INFO) {
            Log.i(tag, msg!!)
        }
    }

    fun w(tag: String?, msg: String?) {
        if (level <= WARN) {
            Log.w(tag, msg!!)
        }
    }

    fun e(tag: String?, msg: String?) {
        if (level <= ERROR) {
            Log.e(tag, msg!!)
            //            int maxLength = 1024;
//            while(msg.length() > 0){
//                String logContent = msg.substring(0, Math.min(msg.length(), maxLength));
//                Log.e(tag,logContent);
//                msg = msg.replace(logContent,"");
//            }
        }
    }

    fun complete_e(tag: String?, msg: String) {
        var msg = msg
        val maxLength = 1024 * 3
        if (level <= ERROR) {
            while (msg.length > 0) {
                val logContent = msg.substring(0, Math.min(msg.length, maxLength))
                Log.e(tag, logContent)
                msg = msg.replace(logContent, "")
            }
            Log.e(tag, msg)
        }
    }
}