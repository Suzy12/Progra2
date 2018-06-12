package com.example.poo.progra2.xml;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.example.poo.progra2.logica.Profesor;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;

public class ProfesorDAO extends DAO{
    public static ArrayList<Profesor> profesores = new ArrayList<Profesor>();

    public ProfesorDAO(Context context){
        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            Log.d("ProfesorDao", "External storage not available or you don't have permission to write");
        }else{
            fileName = "profesores.xml";
            file =  new File(context.getFileStreamPath(fileName).getPath());
            Log.d("ProfesoresDao", context.getFileStreamPath(fileName).getPath());
            //Profesor nuevo = new Profesor("Susana", "susasna@gmail.com","8746512354","susanaRules");
            //profesores.add(nuevo);
        }
    }

    public boolean logIn(String id, String contra){
        for(Profesor profesor:profesores){
            if(profesor.getNombre().equals(id) && profesor.getContrasena().equals(contra)){
                return true;
            }
        }
        return false;
    }
    public void registrarProfesor(String pNombre, String pCorreo, String pTel, String pContra){
        Profesor nuevo =  new Profesor(pNombre,pCorreo,pTel,pContra);
        profesores.add(nuevo);

    }


    public void parseXml(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(fileInputStream, null);
            Profesor profesor = null;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("ProfesoresDao", "El xml empezo a leerse");
                        break;
                    case XmlPullParser.START_TAG:
                        if (tag.equalsIgnoreCase("profesor")) {
                            Log.d("ProfesorDAO", "Profesor nuevo creado");
                            profesor = new Profesor();
                        } else if (profesor != null) {
                            if (tag.equalsIgnoreCase("nombre")) {
                                profesor.setNombre(parser.nextText());
                            }else if(tag.equalsIgnoreCase("email")){
                                profesor.setEmail(parser.nextText());
                            }else if(tag.equalsIgnoreCase("telefono")){
                                profesor.setTelefono(parser.nextText());
                            }else if(tag.equalsIgnoreCase("contrasena")){
                                profesor.setContrasena(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase("profesor") && profesor != null) {
                            Log.d("ProfesorDAO", "Profesor agregado a la lista");
                            profesores.add(profesor);
                        }
                        break;
                }
                eventType = parser.next();
            }
        }catch (XmlPullParserException | IOException e){
            e.printStackTrace();
        }
    }

    public void writeXml(){
        try{
            FileOutputStream fileOutputStream =  new FileOutputStream(file);
            XmlSerializer xmlSerializer  = Xml.newSerializer();
            StringWriter writer = new StringWriter();
            xmlSerializer.setOutput(writer);
            xmlSerializer.startDocument("UTF-8", true);
            xmlSerializer.startTag(null,"root");
            for(Profesor profesor:profesores){
                xmlSerializer.startTag(null,"profesor");

                xmlSerializer.startTag(null,"nombre");
                xmlSerializer.text(profesor.getNombre());
                xmlSerializer.endTag(null, "nombre");

                xmlSerializer.startTag(null,"email");
                xmlSerializer.text(profesor.getEmail());
                xmlSerializer.endTag(null,"email");

                xmlSerializer.startTag(null,"telefono");
                xmlSerializer.text(profesor.getTelefono());
                xmlSerializer.endTag(null,"telefono");

                xmlSerializer.startTag(null,"contrasena");
                xmlSerializer.text(profesor.getContrasena());
                xmlSerializer.endTag(null,"contrasena");

                xmlSerializer.endTag(null,"profesor");
            }
            xmlSerializer.endTag(null,"root");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite =  writer.toString();
            fileOutputStream.write(dataWrite.getBytes());
            fileOutputStream.close();
            Log.d("ProfesorDao","El xml termino de escribirse sin problemas");

        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
}
