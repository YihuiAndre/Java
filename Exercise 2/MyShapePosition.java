

public interface MyShapePosition extends MyPoint{
	//return the bounding box of an object
	MyRectangle getMyBoundingBox();
	//return true if two object is overlapping
	boolean doOverlap(MyShape p);
}
