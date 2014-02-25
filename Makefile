###############################################################################
# File:   Makefile
# Author: BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem
#
# Created on 20 Feb 2014
###############################################################################

JC = javac
JFLAGS = -encoding utf-8 -implicit:none
JFLAGSDEBUG = -g -Werror -Xlint:all -Xlint:-serial

.SUFFIXES: .java .class

.java.class:
	$(JC) $(JFLAGS) $(JFLAGSDEBUG) $*.java

CLASSES = \
	VictoryException.java \
	IllegalMovementException.java \
	LineFileWriter.java \
	LineFileReader.java \
	Constants.java \
	Canvas.java \
	Rectangle.java \
	Movement.java \
	Vehicule.java \
	ParkingController.java \
	Grid.java \
	Parking.java \
	Position.java \
	Level.java \
	ParkingFactory.java \
	Player.java  \
	RushHour2.java

GAMENAME = rushHour-2-Groupe4
MAIN = RushHour2

TARNAME = ${GAMENAME}
JARNAME = ${GAMENAME}.jar

# JavaDoc
JDOCDIR = doc/
JDOCOPT = -private -encoding utf-8 -docencoding utf-8 -charset utf-8
JDOCLINKS = -link http://docs.oracle.com/javase/7/docs/api/

BROWSER = firefox

default: classes

classes: $(CLASSES:.java=.class)

clean :
	-rm -f *.class

mrproper : clean default

tar : clean
	-rm -Rf ${TARNAME} ${TARNAME}.tar.gz
	-mkdir ${TARNAME}
	-cp -r -t ${TARNAME} ${CLASSES} Makefile README ./conf
	-tar -cvzf ${TARNAME}.tar.gz ${TARNAME}
	-rm -Rf ${TARNAME}

zip : clean
	-rm -Rf ${TARNAME} ${TARNAME}.zip
	-mkdir ${TARNAME}
	-cp -r -t ${TARNAME} ${CLASSES} Makefile README ./conf
	-zip -vr ${TARNAME}.zip ${TARNAME}
	-rm -Rf ${TARNAME}

test : classes
	-java ${MAIN}

jar : clean
	-rm -Rf bin
	-mkdir bin
	-cp -r -t bin/ conf
	-javac ${JFLAGS} -g:none -d bin ${CLASSES}
	-(cd bin && jar cvfe ${JARNAME} ${MAIN} .)
	-mv bin/${JARNAME} .
	-rm -rf bin

wc : 
	-wc ${CLASSES} Makefile

docclean :
	-rm -Rf ${JDOCDIR}

doc : docclean
	-mkdir ${JDOCDIR}
	-javadoc ${JDOCOPT} ${JDOCLINKS} -d ${JDOCDIR} ${CLASSES}

man :
	-${BROWSER} ${JDOCDIR}index.html &

.PHONY: default clean mrproper tar test jar wc doc docclean man zip
