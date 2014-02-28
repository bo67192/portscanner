static Map getApprovedPorts(String file) {
	def Map map = [:]
	new File(file).each {
		def line = it =~ /(?m)^((?:\d{1,3}\.?){4}),(.*)$/
		def ipAddress = line[0][1]
		if(map[ipAddress]) { throw new Exception('You have an IP address in the approved list twice')}
		map[ipAddress] = []
		line[0][2].split(',').each {
			map[ipAddress] << it
		}
	}
	return map
}