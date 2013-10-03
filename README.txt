Compiled jar file glroulette.jar should be run in one of the following ways:

java -jar glroulette.jar 
	Application starts in non-manual mode (spins are performed each 5 seconds automatically).
	Application server listens on default port 9999
	
java -jar glroulette.jar port
	Application starts in non-manual mode and listens on the given port
	e.g. java -jar glroulette.jar 22213


java -jar glroulette.jar port true
	Application starts in manual mode - spins are not performed automatically. Given port is used.
	e.g. java -jar glroulette.jar 22213 true
	You cannot use form "java -jar glroulette.jar true". Whenever manual mode parameter is given it should go after the port number.
	Any value except of true (not case sensitive) is considered as false as well as absence of the parameter and tells application to run in non-manual mode with automatical spins.
	
	
	
	 
	