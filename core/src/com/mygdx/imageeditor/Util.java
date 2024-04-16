package com.mygdx.imageeditor;

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

}
