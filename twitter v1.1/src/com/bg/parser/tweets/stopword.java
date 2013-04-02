package com.bg.parser.tweets;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

public class stopword {
	private File Archivo;
	private List<String> words;
	
	public stopword(){
		words = new ArrayList<String>();
		Archivo =new File (stopwordGlobals.Path);
	}
	
	private void ReadFile(){
		String Cadena = null;
		BufferedReader bf = null;
		try {
			bf = new BufferedReader( new FileReader(Archivo) );
			while ( bf.ready() ){
				Cadena = bf.readLine();
				System.out.println(Cadena);
			}
		} catch (IOException e) {
				System.out.println("Error al Leer el archivo( ReadFile() ). -> " + e.toString());
				e.printStackTrace();
		}finally{
			try {
				bf.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public List<String> getWords(){
		ReadFile();
		return words;
	}
	
	
}
