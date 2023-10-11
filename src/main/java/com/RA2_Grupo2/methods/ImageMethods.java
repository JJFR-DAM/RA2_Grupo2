package com.RA2_Grupo2.methods;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

public class ImageMethods {
	
	private static String pathToImages="src/main/resources/images/";
	
	public static String addImage(String originalImagePath)
	{
		Path originalPath=Paths.get(originalImagePath);
		String newPathString=pathToImages+Instant.now().toString()+originalPath.getFileName();
		Path newPath=Paths.get(newPathString);
		try {
			Files.copy(originalPath,newPath);
		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return newPathString;
	}
	
	public static boolean deleteImage(String imagePath)
	{
		try {
			Files.delete(Paths.get(imagePath));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static String replaceImage(String oldImagePath,String newImageOriginalPath)
	{
		if(!deleteImage(oldImagePath)) return "";
		return addImage(newImageOriginalPath);
	}
}
