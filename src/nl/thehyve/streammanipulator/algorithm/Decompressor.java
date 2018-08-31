package nl.thehyve.streammanipulator.algorithm;

import java.util.ArrayList;
import java.util.List;

public class Decompressor implements DecompressorInterface {

	protected static final byte ERROR_BYTE = (byte)63;

	List<Byte> decomp = new ArrayList<Byte>();

	@Override
	public void put(byte p, byte q) {
		if (p == 0) {
			decomp.add(q);
		} else if (p > decomp.size() || decomp.size()-p+q > decomp.size()) {
			// when an invalid byte pair is encountered an error byte is added to the buffer
			decomp.add(ERROR_BYTE);
		} else {
			int index = decomp.size()-p;
			for (int i = 0; i < q; i++) {
				decomp.add(decomp.get(index+i));
			}
				
		}
	}

	@Override
	public Byte[] getBuffer() {
		return (Byte[]) decomp.toArray(new Byte[decomp.size()]);
	}

}
