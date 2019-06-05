package nano.topred.nanoPlots.mymath.geometry.graphics2D;

import nano.topred.nanoPlots.Position;

import java.util.ArrayList;
import java.util.UUID;

public class Polygon2D {

    private ArrayList<Point2D> vertices;
    private int numberOfVertices;
    private ArrayList<Segment2D> edges;
    private Rectangle2D boundingBox;

    public Polygon2D(ArrayList<Point2D> p)
    {
        this.vertices= p;
        recalc();
    }

    public void recalc()
    {
        this.numberOfVertices = this.vertices.size();
        this.edges = calcEdges();
        this.boundingBox = calcBoundingBox();

    }

    public void addVertex(Point2D vertex)
    {
        this.vertices.add(vertex);
        recalc();
    }

    public void insertVertex(Point2D vertex, int index)
    {
        this.vertices.add(index,vertex);
        recalc();
    }

    public boolean removeVertex(int index)
    {
        if (this.numberOfVertices <= index)
            return false;
        this.vertices.remove(index);
        recalc();
        return true;
    }

    public boolean removeLastVertex()
    {
        if (this.numberOfVertices==0)
            return false;
        this.vertices.remove(this.numberOfVertices-1);
        recalc();
        return true;
    }

    public boolean isInside(Point2D p)
    {
        Segment2D intersectSegment = new Segment2D(p,new Point2D(Double.MAX_VALUE, Double.MAX_VALUE));
        if(this.boundingBox != null && this.boundingBox.isInside(p))
        {
            int count = 0;
            for (Segment2D s: this.edges)
                if (Segment2D.intersect(s,intersectSegment))
                    count++;
            if (count % 2 == 1)
                return true;
        }
        return false;
    }




    public ArrayList<Segment2D> calcEdges()
    {
        ArrayList<Segment2D> segments = new ArrayList<>();
        int size = this.vertices.size();
        if(size==0)
            return segments;
        if (size==1)
            segments.add(new Segment2D(this.vertices.get(0), this.vertices.get(0)));
        else
        {
            Point2D point0=this.vertices.get(size-1);
            Point2D point1=this.vertices.get(0);
            segments.add(new Segment2D(point0,point1));
            for (int i = 1; i < size ; ++i)
            {
                point0 = point1;
                point1 = this.vertices.get(i);
                segments.add(new Segment2D(point0,point1));
            }

        }
        return segments;
    }

    public ArrayList<Double>/* maxX maxY minX minY */ getExtremePositions()
    {
        ArrayList<Double> extremPositions = new ArrayList<>();
        Point2D vertex = this.vertices.get(0);
        double maxX = vertex.getX();
        double maxY = vertex.getY();
        double minX = vertex.getX();
        double minY = vertex.getY();
        for (int i = 1; i < this.vertices.size(); ++i)
        {
            vertex = this.vertices.get(i);
            double vX = vertex.getX();
            double vY = vertex.getY();
            if (vX>maxX)
                maxX=vX;
            if (vX<minX)
                minX=vX;
            if (vY>maxY)
                maxY=vY;
            if (vY<minY)
                minY=vY;
        }
        extremPositions.add(maxX);
        extremPositions.add(maxY);
        extremPositions.add(minX);
        extremPositions.add(minY);
        return extremPositions;
    }

    public Rectangle2D calcBoundingBox()
    {
        if(this.numberOfVertices==0)
            return null;
        ArrayList<Double> extremePositions = getExtremePositions();

        return new Rectangle2D(
                new Point2D(extremePositions.get(2),extremePositions.get(3)),
                new Point2D(extremePositions.get(0),extremePositions.get(3)),
                new Point2D(extremePositions.get(0),extremePositions.get(1)),
                new Point2D(extremePositions.get(2),extremePositions.get(1)));
    }

    public ArrayList<Position> boundingBoxToPositions(double y, UUID worldId)
    {
        ArrayList<Position> positions ;
        if(this.boundingBox != null)
            positions = this.boundingBox.toPositions(y, worldId);
        else
            positions = new ArrayList<>();
        return positions;
    }

    public ArrayList<Position> surfaceToPositions(double y, UUID worldId)
    {
        ArrayList<Position> positions = new ArrayList<>();
        if (this.boundingBox == null)
        {
            return positions;
        }
        System.out.println("MinX " + this.boundingBox.getMinX()+"   MaxX "+this.boundingBox.getMaxX());
        System.out.println("MinY " + this.boundingBox.getMinY()+"   MaxY "+this.boundingBox.getMaxY());

        for (int X = (int)Math.floor(this.boundingBox.getMinX()); X <= (int)Math.floor(this.boundingBox.getMaxX()); ++X) {
            for (double Y = (int)Math.floor(this.boundingBox.getMinY()); Y <= (int)Math.floor(this.boundingBox.getMaxY()); ++Y) {
                Point2D p = new Point2D(X, Y);
                if (isInside(p))
                    positions.add(p.toWorldPosition(y, worldId));
            }
        }
        return positions;
    }

    public ArrayList<Position> edgesToWorldPositions(double y, UUID worldId)
    {
        ArrayList<Position> positions = new ArrayList<>();
        for (Segment2D s: this.edges)
            positions.addAll(s.toPositions(y,worldId));
        return positions;
    }

    public ArrayList<Position> verticesToWorldPositions(double y, UUID worldId)
    {
        ArrayList<Position> positions = new ArrayList<>();
        for (Point2D p: this.vertices)
            positions.add(p.toWorldPosition(y,worldId));
        return positions;
    }


    public ArrayList<Point2D> getVertices() {
        return vertices;
    }

    public void setVertices(ArrayList<Point2D> vertices) {
        this.vertices = vertices;
        recalc();
    }

    public ArrayList<Segment2D> getEdges() {
        return edges;
    }


    public Rectangle2D getBoundingBox() {
        return boundingBox;
    }

    public int getNumberOfVertices() {
        return numberOfVertices;
    }


}
