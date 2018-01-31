package com.lcgao.personal.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * Created by lcgao on 2018/1/31.
 */

public class DataCleanUtil {

    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSize(cacheSize);
    }

    public static void clearAllCache(Context context){
        deleteDir(context.getCacheDir());
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
            deleteDir(context.getExternalCacheDir());
        }
    }

    private static boolean deleteDir(File dir) {
        if(dir != null && dir.isDirectory()){
            String[] children = dir.list();
            for(int i = 0; i < children.length; i ++){
                boolean success = deleteDir(new File(dir, children[i]));
                if(!success){
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private static String getFormatSize(long size) {
        double kiloByte = size / 1024;
        if(kiloByte < 1){
            return "OK";
        }

        double megaByte = kiloByte / 1024;
        if(megaByte < 1){
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if(gigaByte < 1){
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2,BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraByte = gigaByte / 1024;
        if(teraByte < 1){
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraByte);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP)
                .toPlainString() + "TB";
    }

    private static long getFolderSize(File file) {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for(int i = 0; i < fileList.length; i ++){
                if(fileList[i].isDirectory()){
                    size += getFolderSize(fileList[i]);
                }else {
                    size += fileList[i].length();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return size;
    }
}
