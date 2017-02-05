

JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = Main.java \
	Model/Errors.java \
	Model/Coordinate.java \
	Model/ModelInterface.java \
	Model/Move.java \
	Model/TakBoard.java \
	Model/TakPiece.java \
	Model/TakPlayer.java \
	Model/TakStack.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
