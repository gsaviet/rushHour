###############################################################################
# File:   Makefile
# Author: BRESSAN Romain, DUCOURNEAU Jonathan, LEBLOND Xavier, YAHYAOUI Hichem
#
# Created on 20 Feb 2014
###############################################################################

PRGNAME = rushHour-2-Groupe4
MAIN = RushHour2

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

TARNAME = ${PRGNAME}
JARNAME = ${PRGNAME}.jar

# Javadoc
JDOCDIR = doc
JDOCOPT = -private -encoding utf-8 -docencoding utf-8 -charset utf-8
JDOCLINKS = -link http://docs.oracle.com/javase/7/docs/api/

BROWSER = firefox

default: classes

classes: $(CLASSES:.java=.class)

# Clean
clean :
	-rm -f *.class

mrproper : clean default

# Compile and execute
test : classes
	-java ${MAIN}

# Archive
tar : clean
	-rm -rf ${TARNAME} ${TARNAME}.tar.gz
	-mkdir ${TARNAME}
	-cp -r -t ${TARNAME} ${CLASSES} Makefile README ./conf
	-tar -cvzf ${TARNAME}.tar.gz ${TARNAME}
	-rm -rf ${TARNAME}

zip : clean
	-rm -rf ${TARNAME} ${TARNAME}.zip
	-mkdir ${TARNAME}
	-cp -r -t ${TARNAME} ${CLASSES} Makefile README ./conf
	-zip -vr ${TARNAME}.zip ${TARNAME}
	-rm -rf ${TARNAME}

# Jar
jar : clean
	-rm -rf bin
	-mkdir bin
	-cp -r -t bin/ conf
	-javac ${JFLAGS} -g:none -d bin ${CLASSES}
	-(cd bin && jar cvfe ${JARNAME} ${MAIN} .)
	-mv bin/${JARNAME} .
	-rm -rf bin

wc :
	-wc ${CLASSES} Makefile

# Javadoc
docclean :
	-rm -rf ${JDOCDIR}

doc : docclean
	-mkdir ${JDOCDIR}
	-javadoc ${JDOCOPT} ${JDOCLINKS} -d ${JDOCDIR} ${CLASSES}

man :
	-${BROWSER} ${JDOCDIR}/index.html

.PHONY: default clean mrproper tar test jar wc doc docclean man zip
