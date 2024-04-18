package com.mygdx.imageeditor;

import java.io.FileOutputStream;
import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.math.Vector2;

public class ImageInputOutput {
	public static ImageInputOutput Instance;
	private byte[]  _fileHeader;
	private Pixmap _pixels;
	public ImageInputOutput() {
		Instance = this;
	}
	
	public Pixmap loadImage(String filePath) {
		byte[] fileBytes = Gdx.files.internal(filePath).readBytes();
		if(fileBytes[0] != 'B' || fileBytes[1] != 'M') {
			System.out.println(filePath + " is NOT a bitmap image");
			return null;
		}
		
		int[] fileIntData = Util.unsignBytes(fileBytes);
		
		byte[] fileSize = {fileBytes[2], fileBytes[3], fileBytes[4], fileBytes[5]};
		byte[] start = {fileBytes[10], fileBytes[11], fileBytes[12], fileBytes[13]};
		byte[] widthBytes = {fileBytes[18], fileBytes[19], fileBytes[20], fileBytes[21]};
		byte[] heightBytes = {fileBytes[22], fileBytes[23], fileBytes[24], fileBytes[25]};
		byte[] bitsPerPixel = {fileBytes[28], fileBytes[29]};
		int startPoint = Util.bytesToInt(start);
		_fileHeader = new byte[startPoint];
		int width = Util.bytesToInt(widthBytes);
		int height = Util.bytesToInt(heightBytes);
		int bytesPerPixel = Util.bytesToInt(bitsPerPixel) / 8;
		if(bytesPerPixel != 3) {System.out.println("Unsupported image pixel format. Incorrect bits per pixel"); return null;}
		
		Pixmap pixels = new Pixmap(width, height, Format.RGBA8888);
		int r,g,b;
		int x = 0;
		int y = height - 2;
		for(int i = 0; i < startPoint; i++) {
			_fileHeader[i] = (byte) fileIntData[i];
		}
		for(int i = startPoint; i < fileIntData.length - 3; i += 3) {
			b = fileIntData[i];
			g = fileIntData[i + 1];
			r = fileIntData[i + 2];
			pixels.setColor(new Color((r / 255f), (g / 255f), (b / 255f), 1));
			pixels.drawPixel(x, y);
			x++;
			if(x >= width) {
				x = 0;
				y--;
			}
		}
		_pixels = pixels;
		return pixels;

	}
	
	public void saveImage(String filePath) throws IOException {
		FileOutputStream output = new FileOutputStream(filePath);
		byte[] color;
		byte[] colorData = new byte[_pixels.getWidth() * _pixels.getHeight() * 3];
		int colorIndex = 0;
		for(int y = _pixels.getHeight() - 1; y >= 0; y--) {
			 for(int x = 0; x < _pixels.getWidth(); x++) {
				 color = Util.intToSignedBytes(_pixels.getPixel(x, y));
				 colorData[colorIndex] = color[2];
				 colorData[colorIndex + 1] = color[1];
				 colorData[colorIndex + 2] = color[0];
				 colorIndex += 3;
			 }
		}
		Pixmap doodle = Util.scalePixmap(EditWindow.Instance._doodleMap, new Vector2(_pixels.getWidth(), _pixels.getHeight()));
		colorIndex = 0;
		for(int y = doodle.getHeight() - 1; y >= 0; y--) {
			for(int x = 0; x < doodle.getWidth(); x++) {
				color = Util.intToSignedBytes(doodle.getPixel(x, y));
				if(color[3] != -1) {colorIndex += 3; continue; }
				colorData[colorIndex] = color[2];
				colorData[colorIndex + 1] = color[1];
				colorData[colorIndex + 2] = color[0];
				colorIndex += 3;
			}
		}
		output.write(_fileHeader);
		output.write(colorData);
		output.close();
	}
}
