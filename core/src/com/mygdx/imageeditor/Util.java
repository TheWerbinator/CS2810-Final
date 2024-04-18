package com.mygdx.imageeditor;

import java.nio.ByteBuffer;

import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.math.Vector2;

public class Util {
	public static int bytesToInt(byte[] bytes) {
		int[] newBytes = unsignBytes(bytes);
		int result = 0;
		for(int i = 0; i < newBytes.length; i++) {
			result += (int) newBytes[i] << (8 * i);
		}
		return result;
	}
	
	public static int[] unsignBytes(byte[] bytes) {
		int[] ints = new int[bytes.length];
		for(int i = 0; i < bytes.length; i++) {
			if(bytes[i] >= 0) {
				ints[i] = bytes[i];
			} else {				
				ints[i] = bytes[i] - (-129) + 127;
			}
		}
		return ints;
	}
	
	public static byte[] intToSignedBytes(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) (value >> 24);
		result[1] = (byte) ((value << 8) >> 24);
		result[2] = (byte) ((value << 16) >> 24);
		result[3] = (byte) ((value << 24) >> 24);
		return result;
	}

	public static void testIntToSignedBytes() {
		byte[] testResults = intToSignedBytes(543152314);
		int[] expectedResults = {32, 95, -40, -70};
		for(int i = 0; i < testResults.length; i++) {
			if((int) testResults[i] != expectedResults[i]) 
				System.out.println("TEST FAILED! INDEX " + i + " IS "
						+ testResults[i] + " EXPECTED: " + expectedResults[i]);
		}
	}
	
	public static Pixmap scalePixmap(Pixmap map, Vector2 desiredSize) {
		Pixmap target = new Pixmap((int) desiredSize.x, (int) desiredSize.y, Pixmap.Format.RGBA8888);
		int srcWidth = map.getWidth();
	    int srcHeight = map.getHeight();
		int targetWidth = target.getWidth();
	    int targetHeight = target.getHeight();
		for(int x = 0; x < targetWidth; x++) {
			for(int y = 0; y < targetHeight; y++) {
				int srcX = (int) Math.round((float)x / (float)targetWidth * (float)srcWidth);
				int srcY = (int) Math.round((float)y / (float)targetHeight * (float)srcHeight);
				srcX = Math.min(srcX, srcWidth - 1);
				srcY = Math.min(srcY, srcHeight - 1);
				int srcColor = map.getPixel(srcX, srcY);
				target.setColor(srcColor);
				target.drawPixel(x, y);
			}

		}
		return target;
	}
}
