Rules-Demo
==========
Este proyecto tiene como objetivo demostrar el funcionamiento de pruebas unitarias
parametrizables sobre servicios EJB que implementan reglas o decisiones de negocio.

Los servicios EJB tiene parámetros de entrada, modelo de dominio, y producen determinadas
salidas segun un modelo de hechos o reglas utilizadas.

Las pruebas unitarias invocan estos servicios tomando los parámetros de entrada
desde un archivo excel o csv y validando las salidas con aquellas que se especificaron
como esperadas dentro del mismo archivo.

Ej: Supongamos que tenemos una base de personas y queremos saber si son adultas

Nombre	Apellido	TipoDoc	NumeroDoc	Edad	Salida Esperada
------	--------	-------	---------	----	---------------
Bart	Simpson		DNI		111			11		false	
Liza	Simpson		DNI		222			8		false	
Maguie	Simpson		DNI		333					ValidationException	
March	Simpson		DNI		444			34		true	
Homer	Simpson		DNI		555			36		true	

Se puede ver en que para el caso de Bart Simpson se espera que el servicio
devuelva false y para Homer Simpson devuelva true, mientras que para
Maguie que no tiene especificada edad este sea tomado como un error
en la entrada de datos validos que el servicio espera.

Estas pruebas unitarias se integran con el ciclo de integracion continua del 
desarrollo del software a través de maven.

Ej: ubicados sobre el proyecto maven tipeamos

$>mvn test

Lo que nos arroja los resultados por consola ademas de dejarnos un detalle 
de la ejecución de las pruebas como reporte

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.cuyum.rules.demo.services.PersonRulesTest
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.249 sec


Contenido del proyecto
======================
Tiene tres directorios, cada uno de ellos es un subproyecto maven

rules-ejb: 	es el proyecto principal, en el se encuentra el modelo de dominio, los srevicios
			y las pruebas unitarias. También se encuentra el archivo de ejemplo "isAdult.csv"
			que contiene los datos de entrada del ejemplo anterior
			
rules-ear: 	contiene al informacion y descriptores para generar el archivo ear que se 
			deployará en el Application Server
			
rules-pom:	proyecto maven padre de los dos anteriores, creado a efectos de facilitar integración
			con Jenkis
			
			
Ejecución de Pruebas
====================
1) 	Bajarse el proyecto desde github: git clone https://github.com/gleotta/rules-demo.git

2) 	Ir al directorio de los ejb:  $>cd rules-ejb

3) 	Ejecutar los test con el ejemplo default: $>mvn clean test

4) 	Se puede ejecutar las pruebas tomando el archivo desde otra ubicación, para ello debemos pasarle 
	la variable de sistema "rules.demo.docbase" a maven indicando el path absoulto del directorio
	desde donde se tomará el archivo. El archivo si debe llamarse "isAdult.csv", ya que isAdult es
	el nombre de la regla o servicio que se va a probar.
	$>mvn test -DargLine="-Drules.demo.docbase=/home/gleotta/public" 
	
5) Se puede ver el reporte de los test dentro del directorio rules-ejb/target/surefire-reports

	

