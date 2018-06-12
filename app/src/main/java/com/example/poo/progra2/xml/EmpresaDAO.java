package com.example.poo.progra2.xml;

import android.content.Context;
import android.util.Log;
import android.util.Xml;

import com.example.poo.progra2.logica.Empresa;

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


public class EmpresaDAO extends DAO {
    public static ArrayList<Empresa> empresas = new ArrayList<Empresa>();

    public EmpresaDAO(Context context){
        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()){
            Log.d("EmpresaDao", "External storage not available or you don't have permission to write");
        }else{
            fileName = "empresas.xml";
            file =  new File(context.getFileStreamPath(fileName).getPath());
            Log.d("EmpresasDAO", context.getFileStreamPath(fileName).getPath());
            //Profesor nuevo = new Profesor("Susana", "susasna@gmail.com","8746512354","susanaRules");
            //profesores.add(nuevo);
        }
    }

    public void agregarEmpresa(String pNombre,String pDir, String pTel, String pSup){
        Empresa nuevo = new Empresa(pNombre, pDir, pTel, pSup);
        empresas.add(nuevo);

    }

    public void parseXml(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(fileInputStream, null);
            Empresa empresa = null;
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tag = parser.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        Log.d("EmpresaDao", "El xml empezo a leerse");
                        break;
                    case XmlPullParser.START_TAG:
                        if (tag.equalsIgnoreCase("empresa")) {
                            Log.d("EmpresaDao", "Profesor nuevo creado");
                            empresa = new Empresa();
                        } else if (empresa != null) {
                            if (tag.equalsIgnoreCase("nombre")) {
                                empresa.setNombre(parser.nextText());
                            }else if(tag.equalsIgnoreCase("direccion")){
                                empresa.setDireccion(parser.nextText());
                            }else if(tag.equalsIgnoreCase("telefono")){
                                empresa.setTelefono(parser.nextText());
                            }else if(tag.equalsIgnoreCase("supervisor")){
                                empresa.setSupervisor(parser.nextText());
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (tag.equalsIgnoreCase("empresa") && empresa != null) {
                            Log.d("EmpresaDAO", "Empresa agregada a la lista");
                            empresas.add(empresa);
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
            for(Empresa empresa:empresas){
                xmlSerializer.startTag(null,"empresa");

                xmlSerializer.startTag(null,"nombre");
                xmlSerializer.text(empresa.getNombre());
                xmlSerializer.endTag(null, "nombre");

                xmlSerializer.startTag(null,"direccion");
                xmlSerializer.text(empresa.getDireccion());
                xmlSerializer.endTag(null,"direccion");

                xmlSerializer.startTag(null,"telefono");
                xmlSerializer.text(empresa.getTelefono());
                xmlSerializer.endTag(null,"telefono");

                xmlSerializer.startTag(null,"supervisor");
                xmlSerializer.text(empresa.getSupervisor());
                xmlSerializer.endTag(null,"supervisor");

                xmlSerializer.endTag(null,"empresa");
            }
            xmlSerializer.endTag(null,"root");
            xmlSerializer.endDocument();
            xmlSerializer.flush();
            String dataWrite =  writer.toString();
            fileOutputStream.write(dataWrite.getBytes());
            fileOutputStream.close();
            Log.d("EmpresaDAO","El xml termino de escribirse sin problemas");

        } catch (IllegalArgumentException | IllegalStateException | IOException e) {
            e.printStackTrace();
        }
    }
}
