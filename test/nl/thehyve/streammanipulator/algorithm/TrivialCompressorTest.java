package nl.thehyve.streammanipulator.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrivialCompressorTest {

	@Test
	void testStandardInput() {
		TrivialCompressor c = new TrivialCompressor();
		Byte[] bytes = {61, 61, 61};
		assertArrayEquals(new Byte[] {0, 61, 0, 61, 0, 61}, c.encode(bytes));
	}
	

	// Note: assumption here is that erroneous bytes should be written as a normal byte
	// and are propagated as list of error bytes in the encoded stream
	@Test
	void testEncounterErrorByte() {
		TrivialCompressor c = new TrivialCompressor();
		Byte[] bytes = {61, 61, Decompressor.ERROR_BYTE};
		assertArrayEquals(new Byte[] {0, 61, 0, 61, 0, Decompressor.ERROR_BYTE}, c.encode(bytes));
	}

}
