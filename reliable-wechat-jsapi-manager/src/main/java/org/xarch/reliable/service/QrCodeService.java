package org.xarch.reliable.service;

import java.io.File;

public interface QrCodeService {

	public String drawLogoQRCode(File logoFile, File codeFile, String qrUrl, String note);

}
