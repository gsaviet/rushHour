###############################################################################
# File:   Makefile
# Author: BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem
#
# Created on 20 Feb 2014
###############################################################################

JC = javac
JFLAGS = -g

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	VictoryException.java \
	IllegalMovementException.java \
	LineFileWriter.java \
	LineFileReader.java \
	Constants.java \
	Canvas.java \
	Rectangle.java \
	Vehicule.java \
	ParkingController.java \
	Grid.java \
	Parking.java \
	Position.java \
	Level.java \
	ParkingFactory.java \
	Player.java  \
	RushHour2.java


GAMENAME = rushHour
TARNAME = ${GAMENAME}
JARNAME = ${GAMENAME}.jar
BROWSER = firefox
JDOCDIR = doc/

default: classes

classes: $(CLASSES:.java=.class)

clean :
	-rm -f *.class

mrproper : clean default

tar : clean
	-rm -Rf ${TARNAME} ${TARNAME}.tar.gz
	-mkdir ${TARNAME}
	-cp -r -t ${TARNAME} ./*.java Makefile ./img ./tmp
	-tar -cvzf ${TARNAME}.tar.gz ${TARNAME}
	-rm -Rf ${TARNAME}

test : classes
	-java RushHour2

jar : mrproper
	-jar -cfe ${JARNAME} RushHour2 .

wc : 
	-wc *.java Makefile

doc :
	-rm -Rf ${JDOCDIR}
	-mkdir ${JDOCDIR}
	-javadoc -d ${JDOCDIR} ${CLASSES}

man :
	-${BROWSER} ${JDOCDIR}/index.html &

.PHONY: default clean mrproper tar test jar wc doc man
