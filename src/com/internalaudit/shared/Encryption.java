package com.internalaudit.shared;

import com.googlecode.gwt.crypto.bouncycastle.digests.SHA1Digest;

public class Encryption {

	public String getSHA1Encryption(String text) 
    {
		SHA1Digest sd = new SHA1Digest();
		byte[] bs = text.getBytes();
		sd.update(bs, 0, bs.length);
		byte[] result = new byte[20];
		sd.doFinal(result, 0);
		return byteArrayToHexString(result);
	}
	private	String byteArrayToHexString(final byte[] b) 
		{
			final StringBuffer sb = new StringBuffer(b.length * 2);
			for (int i = 0, len = b.length; i < len; i++) {
			int v = b[i] & 0xff;
			if (v < 16) sb.append('0');
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
		}
}
