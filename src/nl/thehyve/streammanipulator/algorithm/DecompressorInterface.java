package nl.thehyve.streammanipulator.algorithm;

public interface DecompressorInterface {
	
	void put(byte p, byte q);
	Byte[] getBuffer();

}
