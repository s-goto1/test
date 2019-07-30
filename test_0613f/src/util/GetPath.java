package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GetPath {
	public static String getDesktopPath() {
		String ret = "";

		try {
			String cmdline = "reg query \"HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders\" /v Desktop";

			String line = "";

			Process pc = Runtime.getRuntime().exec(cmdline);
			BufferedReader br = new BufferedReader(new InputStreamReader(pc.getInputStream()));

			while ((line = br.readLine()) != null) {
				if (line.indexOf("Desktop") != -1) {
					ret = line.substring(line.indexOf("C"), line.length());
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ret;
	}
}
