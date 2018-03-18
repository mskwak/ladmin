package com.daou.ladmin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import org.apache.tika.exception.TikaException;
import org.apache.tika.io.TikaInputStream;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

public class TikaMain {
	public static void main(String[] args) throws IOException, SAXException, TikaException {
	    AutoDetectParser parser = new AutoDetectParser();
	    BodyContentHandler handler = new BodyContentHandler();
	    Metadata metadata = new Metadata();


	    File file = new File("C:\\Users\\MSKW\\Downloads");
	    File[] files = file.listFiles();
	    Arrays.stream(files).filter(f -> f.isFile()).forEach(f -> {
	    	InputStream stream = null;
			try {
				stream = TikaInputStream.get(f);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				parser.parse(stream, handler, metadata, new ParseContext());
			} catch (IOException | SAXException | TikaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.println(metadata.get(Metadata.CONTENT_TYPE));
	    });
	}
}
