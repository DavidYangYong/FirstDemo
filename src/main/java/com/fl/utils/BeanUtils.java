package com.fl.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * BeanUtils
 * 
 * @Author ����ͥ
 * @Time 2014-07-18
 *
 */
public class BeanUtils {
	
	/**
	 * byte[] convert to Object
	 * 
	 * @param bytes
	 * @return Object
	 */
	public static Object bytes2Object(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bais = null;
		ObjectInputStream ois = null;
		try {
			bais = new ByteArrayInputStream(bytes);
			ois = new ObjectInputStream(bais);
			obj = (Object) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
			// ...
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
				// ...
			}
		}
		
		return obj;
	}

	/**
	 * Object convert to byes[]
	 * 
	 * @param obj
	 * @return bytes[]
	 */
	public static byte[] object2Bytes(Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream baos = null;
		ObjectOutputStream oos = null;
		try {
			baos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(baos);
			oos.writeObject(obj);
			bytes = baos.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
			// ...
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
				// ...
			}
		}
		
		return bytes;
	}
}
