Program: Port Scanner

This is a groovy program to scan the entire port range on a list of IPs and provide reports of the results.

DEPENDENCIES: This program assumes that nmap is available on the command line.

This program was written using groovy 2.2

INPUTS: All inputs are expected to be in the "inputs" directory. There should be two files there:

ApprovedPorts.txt - this is a CSV file of the ports that are approved to be open. They should be in the following format:

<IPAddress>,PORT,PORT....

Each IP should only appear once.

IPs.txt - this is the list of IP addresses to scan. Each IP address should be on it's own line with no other delimiters

OUTPUTS: The created reports will be in the Reports folder. Each report has the current date appended to it to prevent overwriting the content. They are:

ClosedPorts-<current date>.csv - this is a report of the ports that have been closed (IE ports specified in the ApprovedPorts.txt file, but were not found to be open during the current scan).

UnapprovedPorts-<current date>.csv - This is a report of the ports that were not approved but were found to be open (IE ports that were found in the scan, but were not specified in the ApprovedPorts.txt file)

USAGE: This program can be run from the command line with the following command

groovy Driver.groovy

It will then scan the IP addresses specified, compare the results to the approved ports, and print a report.

NOTE: Since all 65,535 TCP ports are scanned this program can easily take days or weeks to run if there are enough IPs in the address list.