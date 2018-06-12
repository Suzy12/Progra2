package com.example.poo.progra2.xml;



import android.content.Context;

import android.util.Log;

import android.util.Xml;



import com.example.poo.progra2.logica.Calendario;

import com.example.poo.progra2.logica.Entregable;

import com.example.poo.progra2.logica.Periodo;

import com.example.poo.progra2.logica.Profesor;



import org.xmlpull.v1.XmlPullParser;

import org.xmlpull.v1.XmlPullParserException;

import org.xmlpull.v1.XmlPullParserFactory;

import org.xmlpull.v1.XmlSerializer;



import java.io.File;

import java.io.FileInputStream;

import java.io.FileNotFoundException;

import java.io.FileOutputStream;

import java.io.IOException;

import java.io.StringWriter;

import java.util.ArrayList;



public class PeriodoDAO extends DAO {



    public static ArrayList<Periodo> periodos = new ArrayList<Periodo>();



    public PeriodoDAO(Context context){

        if(!isExternalStorageAvailable() || isExternalStorageReadOnly()){

            Log.d("PeriodoDao", "External storage not available or you don't have permission to write");

        }else{

            fileName = "periodos.xml";

            file =  new File(context.getFileStreamPath(fileName).getPath());

            if(!file.exists()){

                writeXml();

            }

            Log.d("PeriodoDao", context.getFileStreamPath(fileName).getPath());

        }

    }



    public void agregarPeriodo(String pSemestre,String pAno){

        Periodo nuevo = new Periodo(pSemestre,pAno);
        periodos.add(nuevo);

    }

    public Periodo buscarPeriodo(String pSemestre, String pAno){
        for(int i = 0; i<periodos.size(); i++){
            if(periodos.get(i).getSemestre().equals(pSemestre)){
                if(periodos.get(i).getAno().equals(pAno)){
                    return periodos.get(i);
                }
            }
        }
        return null;
    }



    public void parseXml() {

        try {

            FileInputStream fileInputStream = new FileInputStream(file);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser parser = factory.newPullParser();

            parser.setInput(fileInputStream, null);

            Periodo periodo = null;

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tag = parser.getName();

                switch (eventType) {

                    case XmlPullParser.START_DOCUMENT:

                        Log.d("PeriodoDao", "El xml empezo a leerse");

                        break;

                    case XmlPullParser.START_TAG:

                        if (tag.equalsIgnoreCase("periodo")) {

                            Log.d("PeriodoDAO", "Periodo nuevo creado");

                            periodo = new Periodo();

                        } else if (periodo != null) {

                            if (tag.equalsIgnoreCase("semestre")) {

                                periodo.setSemestre(parser.nextText());

                            } else if (tag.equalsIgnoreCase("ano")) {

                                periodo.setAno(parser.nextText());

                            } else if (tag.equalsIgnoreCase("calendario")) {

                                leerCalendario(parser, periodo);

                            }

                        }

                        break;

                    case XmlPullParser.END_TAG:

                        if (tag.equalsIgnoreCase("profesor") && periodo != null) {

                            Log.d("ProfesorDAO", "Profesor agregado a la lista");

                            periodos.add(periodo);

                        }

                        break;

                }

                eventType = parser.next();

            }

        }catch (XmlPullParserException | IOException e){

            e.printStackTrace();

        }

    }



    public void leerCalendario(XmlPullParser parser, Periodo periodo) throws  XmlPullParserException,IOException{

        Entregable entregable = null;

        String tag = parser.getName();

        int eventType = parser.getEventType();

        while (eventType != XmlPullParser.END_TAG && tag.equalsIgnoreCase("calendario")){

            switch (eventType){

                case XmlPullParser.START_TAG:

                    if(tag.equalsIgnoreCase("entregable")){

                        Log.d("PeriodoDAO","Entregable creado");

                        entregable = new Entregable();

                    }else if(entregable != null){

                        if(tag.equalsIgnoreCase("titulo")){

                            entregable.setTitulo(parser.nextText());

                        }

                    }

            }

        }

    }

    public void writeXml(){

        try{

            FileOutputStream fileOutputStream =  new FileOutputStream(file);

            XmlSerializer xmlSerializer  = Xml.newSerializer();

            StringWriter writer = new StringWriter();

            xmlSerializer.setOutput(writer);

            xmlSerializer.setFeature("http://xmlpull.org/v1/doc/features.html#indent-output", true);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null,"root");

            for(Periodo periodo:periodos){

                xmlSerializer.startTag(null,"periodo");



                xmlSerializer.startTag(null,"semestre");

                xmlSerializer.text(periodo.getSemestre());

                xmlSerializer.endTag(null, "semestre");



                xmlSerializer.startTag(null,"ano");

                xmlSerializer.text(periodo.getAno());

                xmlSerializer.endTag(null,"ano");



                if(periodo.getCalendario() != null){

                    xmlSerializer.startTag(null,"calendario");



                    insertEntregables(xmlSerializer,periodo.getCalendario().entregables);



                    xmlSerializer.endTag(null, "calendario");

                }



                xmlSerializer.endTag(null,"periodo");

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



    private void insertEntregables(XmlSerializer xmlSerializer, ArrayList<Entregable> entregables) throws IOException{

        for(Entregable entregable:entregables) {

            xmlSerializer.startTag(null,"entregable");



            xmlSerializer.startTag(null,"titulo");

            xmlSerializer.text(entregable.getTitulo());

            xmlSerializer.endTag(null,"titulo");



            xmlSerializer.startTag(null,"descripcion");

            xmlSerializer.text(entregable.getDescripcion());

            xmlSerializer.endTag(null,"descripcion");



            xmlSerializer.startTag(null,"fechaDeEntrega");

            xmlSerializer.text(entregable.getFechaDeEntrega());

            xmlSerializer.endTag(null,"fechaDeEntrega");



            xmlSerializer.startTag(null,"tipo");

            if(entregable.isElectronico()) {

                xmlSerializer.text("true");

            }else {

                xmlSerializer.text("false");

            }

            xmlSerializer.endTag(null,"tipo");



            /*xmlSerializer.startTag(null,"nota");

            xmlSerializer.text(String.valueOf(entregable.getNota()));

            xmlSerializer.endTag(null,"nota");



            xmlSerializer.startTag(null,"fechaNota");

            xmlSerializer.text(entregable.getFechaNota());

            xmlSerializer.endTag(null,"fechaNota");



            xmlSerializer.startTag(null,"comentarios");

            xmlSerializer.text(entregable.getComentarios());

            xmlSerializer.endTag(null,"comentarios");*/



            xmlSerializer.endTag(null,"entregable");

        }

    }

}