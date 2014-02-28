static void printReport(String title, Map map) {
	println(title)
	map.each {key, value ->
		println("$key\n$value\n")
	}
}

static void saveReport(String fileName, Map map) {
	def file = new File(fileName)
	map.each {key, value ->
		file << "${key},${value.join(',')}\n"
	}
}