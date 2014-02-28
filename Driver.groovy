import ScanOutputParser

static void main(String[] args) {
	
	println('Running Nmap scan')
	def nmapScanOutput = NmapScanner.runNmapScan(/Input\IPs.txt/);
	
	println('Parsing scan results')
	def scanResults = ScanOutputParser.parseNmapOutput(nmapScanOutput.toString())
	
	println('Importing approved ports')
	def approvedPorts = ApprovedPorts.getApprovedPorts(/Input\ApprovedPorts.txt/)
	
	println('Comparing scan results to approved ports');
	def newPorts = scanResults.inject([:]) {map, key, value ->
		def diff = value - approvedPorts[key]
		
		if (map[key]) {
			map[key] << diff
		} else if (diff) {
			map[key] = diff
		}
		return map
	}
		
	def closedPorts = approvedPorts.inject([:]) {map, key, value ->
		def diff = value - scanResults[key]
		if (map[key]) {
			map[key] << diff
		} else if (diff) {
			map[key] = diff
		}
		return map
	}
	
	Output.printReport('Unapproved Ports', newPorts)
	Output.printReport('Closed Ports\n', closedPorts)
	
	println('Saving reports')
	Output.saveReport("Reports/UnapprovedPorts-${new Date().format('MM-dd-yyyy')}.csv", newPorts)
	Output.saveReport("Reports/ClosedPorts-${new Date().format('MM-dd-yyyy')}.csv", closedPorts)
}
