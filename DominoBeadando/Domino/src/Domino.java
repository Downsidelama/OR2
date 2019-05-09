import java.io.Serializable;

class Domino implements Serializable {
	int side1;
	int side2;

	Domino(int side1, int side2) {
		this.side1 = side1;
		this.side2 = side2;
	}
}
