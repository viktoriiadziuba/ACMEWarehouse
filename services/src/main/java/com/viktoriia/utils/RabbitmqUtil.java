package com.viktoriia.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.Base64;

public class RabbitmqUtil {
	
	public static String convertToByteString(Object obj) throws IOException {
		try(ByteArrayOutputStream baos = new ByteArrayOutputStream(); ObjectOutput out = new ObjectOutputStream(baos)) {
			out.writeObject(obj);
			final byte[] byteArray = baos.toByteArray();
			return Base64.getEncoder().encodeToString(byteArray);
		}
	}
	
	public static Object convertFromByteString(String byteString) throws IOException, ClassNotFoundException {
		final byte[] bytes = Base64.getDecoder().decode(byteString);
		try(ByteArrayInputStream bais = new ByteArrayInputStream(bytes); ObjectInput in = new ObjectInputStream(bais)) {
			return in.readObject();
		}
	}

}
