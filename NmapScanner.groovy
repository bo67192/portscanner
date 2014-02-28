static StringBuffer runNmapScan(String addressFile) {
	def outBuff = new StringBuffer();
	def errBuff = new StringBuffer();
	
	def proc = "nmap -iL ${addressFile} -Pn --min-rate 400 -p 1-65535".execute()
	proc.waitForProcessOutput(outBuff, errBuff);
	
	return outBuff;
}