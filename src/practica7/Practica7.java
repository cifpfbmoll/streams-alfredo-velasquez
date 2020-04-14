/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica7;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mamey
 */
public class Practica7 {
    static int counter=0;
    static boolean write=false;
    static boolean isWritten=false;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        int option=0; 
        int character=0;
        String line="";
        String route="";
        int i=0;
        while (option !=4){
            System.out.println("1.-Lectura y escritura del fichero de cartelera byte a byte (byte Streams)");
            System.out.println("2.-Lectura y escritura de fichero de cartelera carácter a carácter (character Streams)");
            System.out.println("3.-Lectura y escritura de fichero línea a línea con buffers (character Streams)");
            System.out.println("4.- Salir");
            System.out.println("Seleccionar opción: ");
            Scanner lector=new Scanner (System.in);
            option=Integer.parseInt(lector.nextLine());
            if (option!=4){
                System.out.println("Introducir ruta: ");
                route=lector.nextLine();
            }
            switch (option){                        
                case 1:
                    try{
                    try (FileInputStream fileInput = new FileInputStream(route)) {
                        if (!isWritten){
                            System.out.println("====================================");
                            System.out.println("=====    CARTELERA DE CINE     =====");
                            System.out.println("====================================");
                            System.out.print("----");
                        }
                        character = fileInput.read();
                        while (character!=-1){
                            characterTocharacter(character);
                            character = fileInput.read();
                        }
                        counter=0;
                        character=0;
                    }
                        line=write();
                        if (write){
                            try (FileOutputStream fileOutput = new FileOutputStream(route)) {
                                for (i=0;i<line.length();i++){
                                    fileOutput.write((int)line.charAt(i));
                                }
                                write=false;
                            }
                        } 
                    }catch(FileNotFoundException ex){
                        if (!"".equals(route)) {
                            try {
                                throw new ExceptionCustom(333);
                            } catch (ExceptionCustom newException) {
                                try {
                                    System.out.println(newException.getMessage());
                                    writeErrors(newException.getMessage());
                                } catch (Exception anotherException) {
                                    Logger.getLogger(Practica7.class.getName()).log(Level.SEVERE, null, anotherException);
                                }
                            }
                        } else {
                            System.err.println("Ruta no introducida");
                        }
                    }catch(IOException externalException){
                        System.err.println(externalException.getMessage()); 
                    }
                    break;
                case 2:
                    try{
                        File entrada=new File(route); 
                        try (FileReader reader = new FileReader(entrada)) {
                            if (!isWritten){
                                System.out.println("====================================");
                                System.out.println("===== CARTELERA DE CINE FBMOLL =====");
                                System.out.println("====================================");
                                System.out.print("----");
                            }
                            character = reader.read();
                            while (character!=-1){
                                characterTocharacter(character);
                                character = reader.read();
                            }
                            counter=0;
                            character=0;
                        }
                        line = write();
                        if (write) {
                            try (FileWriter writer = new FileWriter(entrada)) {
                                for (i = 0; i < line.length(); i++) {
                                    writer.write((int) line.charAt(i));
                                }
                                write = false;
                            }
                        }                           
                    }catch(FileNotFoundException ex){
                        if (route!=null){
                            try{
                                throw new ExceptionCustom(333);
                            }catch (ExceptionCustom newException){
                                try {
                                    System.out.println(newException.getMessage());
                                    writeErrors(newException.getMessage());
                                } catch (Exception anotherException) {
                                    Logger.getLogger(Practica7.class.getName()).log(Level.SEVERE, null, anotherException);
                                }
                            }
                        }else{
                            System.err.println("Ruta no introducida");
                        }    
                    }catch(IOException externalException){
                        System.err.println(externalException.getMessage()); 
                    }
                    break;
                case 3: 
                    try{
                        File entrada=new File(route); 
                        try (BufferedReader bufferReader = new BufferedReader(new FileReader(entrada))) {
                            if (!isWritten){
                                System.out.println("====================================");
                                System.out.println("===== CARTELERA DE CINE FBMOLL =====");
                                System.out.println("====================================");
                                System.out.print("----");
                            }
                            line=bufferReader.readLine();
                            while (line != null){
                                for (i=0;i<line.length();i++){
                                    characterTocharacter((int)line.charAt(i));
                                }
                                line=bufferReader.readLine();
                            }
                            counter=0;
                            character=0;
                            line="";
                        }
                        line=write();
                        if (write){
                            try (BufferedWriter bufferWriter = new BufferedWriter(new FileWriter(entrada))) {
                                bufferWriter.write(line);
                                write=false;
                            }
                        }
                    }catch(FileNotFoundException ex){
                        if (route != null) {
                            try {
                                throw new ExceptionCustom(333);
                            } catch (ExceptionCustom newException) {
                                try {
                                    System.out.println(newException.getMessage());
                                    writeErrors(newException.getMessage());
                                } catch (Exception anotherException) {
                                    Logger.getLogger(Practica7.class.getName()).log(Level.SEVERE, null, anotherException);
                                }
                            }
                        } else {
                            System.err.println("Ruta no introducida");
                        }
                    }catch(IOException externalException){
                        System.err.println(externalException.getMessage()); 
                    }
            }
        }
    }        
    
    public static void characterTocharacter(int car) throws IOException{
        switch ((char) car) {
            case '#':
                switch (counter) {
                    case 0:
                        System.out.println("Año: ");
                        break;
                    case 1:
                        System.out.println("Director: ");
                        break;
                    case 2:
                        System.out.println("Duración: ");
                        break;
                    case 3:
                        System.out.println("Sypnosis: ");
                        break;
                    case 4:
                        System.out.println("Reparto: ");
                        break;
                    case 5:
                        System.out.println("Sesión: ");
                        break;
                }
                counter++;
                break;
            case '{':
                System.out.println("----");
                counter = 0;
                break;
            default:
                if ((int) car != -1) {
                    System.out.print((char) car);
                break;    
                }
        }
    }   
    
    public static void writeErrors(String errorMessage) throws Exception{
        try{    
            File errorsFile=new File("C:\\Users\\Mamey\\Documents\\Errors.txt");
            try (FileWriter bugWriter = new FileWriter (errorsFile, true)) {
                Date date=new Date();
                bugWriter.write("\n"+errorMessage+" "+date.toString());
            }
        }catch(IOException newException){
            System.err.println("Error");
        }    
    }
    
    public static String write() {
        Scanner lector=new Scanner(System.in);
        String text="";
        String option;
        System.out.println("escribir?(S/N): ");
        option=lector.nextLine();
        if (option.equals("S")||option.equals("s")){
            System.out.println("Introducir texto: ");
            text=lector.nextLine();
            write=true;
            isWritten=true;
        }
        return text;
    }
}
