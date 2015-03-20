package ua.name.anton.mail.util;

public class Memory {
	
	private static final long MEGABYTE = 1024L * 1024L;
	
	private static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}
	
	public static long getUsedMemory() {
		Runtime runtime = Runtime.getRuntime();
		long memory = runtime.totalMemory() - runtime.freeMemory();
		return bytesToMegabytes(memory);
	}

}
