import ScanOutputParser

static void main(String[] args) {
	
	def approvedPortsFile = new File(/Input\ApprovedPorts.csv/);
	def nmapScanOutput = NmapScanner.runNmapScan(/Input\Addresses.txt/);
	def scanResults = ScanOutputParser.parseNmapOutput(nmapScanOutput.toString())

	def approvedPorts = ApprovedPorts.getApprovedPorts(/Input\ApprovedPorts.csv/)
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
	
	Output.saveReport("Reports/UnapprovedPorts-${new Date().format('MM-dd-yyyy')}.csv", newPorts)
	Output.saveReport("Reports/ClosedPorts-${new Date().format('MM-dd-yyyy')}.csv", closedPorts)
}
