/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author duong
 */
public class JsonWriter {

	public static void WriteJSONfile(String s, String filePath) {
		try {
			FileWriter writer = new FileWriter(filePath);
			writer.write(s);
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
