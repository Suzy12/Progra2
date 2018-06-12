package com.example.poo.progra2.xml;



import android.os.Environment;

import android.util.Log;

import android.util.Xml;

import org.xmlpull.v1.XmlSerializer;
import java.io.File;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.StringWriter;

abstract class DAO {
    protected static File file;
    protected static String fileName;
    public abstract void parseXml();
    public abstract void writeXml();

    public static boolean isExternalStorageReadOnly() {
        String extStorageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(extStorageState)) {
            return true;
        }
        return false;
    }

    public static boolean isExternalStorageAvailable() {

        String extStorageState = Environment.getExternalStorageState();

        if (Environment.MEDIA_MOUNTED.equals(extStorageState)) {

            return true;
        }
        return false;

    }

}