package nl.thehyve.streammanipulator.algorithm;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DecompressorTest {
	
	@Test
	void testEdgeCase1() {
		Decompressor d = new Decompressor();
		// p cannot be larger than the number of elements in the buffer
		// q+p cannot be larger than the number of elements in the buffer
		// if fail: hex 3F should be returned
		d.put((byte)0, (byte)1);
		d.put((byte)0, (byte)1);
		d.put((byte)0, (byte)1);
		d.put((byte)5, (byte)2);
		assertArrayEquals(new Byte[] {1, 1, 1, 63}, d.getBuffer()); // result is [1, 1, 1, 63]
	}
	
	@Test
	void testEdgeCase2() {
		Decompressor d = new Decompressor();
		// p cannot be larger than the number of elements in the buffer
		// q+p cannot be larger than the number of elements in the buffer
		// if fail: hex 3F should be returned
		d.put((byte)0, (byte)1);
		d.put((byte)0, (byte)1);
		d.put((byte)0, (byte)1);
		d.put((byte)1, (byte)10);
		assertArrayEquals(new Byte[] {1, 1, 1, 63}, d.getBuffer()); // result is [1, 1, 1, 63]
	}
	
	@Test
	void testCorrectPQ() {
		Decompressor d = new Decompressor();
		// p cannot be larger than the number of elements in the buffer
		// q+p cannot be larger than the number of elements in the buffer
		// if fail: hex 3F should be returned
		d.put((byte)0, (byte)1);
		d.put((byte)0, (byte)1);
		d.put((byte)2, (byte)2);
		assertArrayEquals(new Byte[] {1, 1, 1, 1}, d.getBuffer()); // result is [1, 1, 1, 63]
	}


	@Test
	void testZeroP() {
		Decompressor d = new Decompressor();
		byte p = 0;
		byte q = 1;
		d.put(p, q);
		assertArrayEquals(new Byte[] {1}, d.getBuffer()); // result is [1]
	}
	
	@Test
	void testPositiveP() {
		Decompressor d = new Decompressor();
		d.put((byte)0, (byte)1); // result is [1]
		byte p = 1;
		byte q = 1;
		d.put(p, q);
		assertArrayEquals(new Byte[] {1,1}, d.getBuffer()); // result is [1,1]
	}
	
	@Test
	void testExampleData() {
		Decompressor d = new Decompressor();
		d.put((byte)0, (byte)61);
		d.put((byte)1, (byte)1);
		d.put((byte)0, (byte)62);
		d.put((byte)3, (byte)2);
		d.put((byte)3, (byte)3);
		assertArrayEquals(new Byte[] {61, 61, 62, 61, 61, 62, 61, 61}, d.getBuffer()); // result is [1,1]
	}

}
