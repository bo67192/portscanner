static Map parseNmapOutput(String input) {
	def Map map = [:]
	def matcher = input =~ /(?s)((?:\d{1,3}\.){3}\d{1,3}).*?(?:Nmap scan report|\Z)/
	matcher.each {ip ->
		map[ip[1]] = []
		def ports = ip[0] =~ /(?sm)^(\d+)\/.*?$+/
		ports.each {
			map[ip[1]] << it[1]
		}
	}
	return map
}