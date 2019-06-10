package nano.topred.nanoplots.plots;
import nano.topred.nanoplots.mymath.geometry.geometry2D.Point2D;
import nano.topred.nanoplots.mymath.geometry.geometry2D.Polygon2D;
import nano.topred.nanoplots.mymath.Position;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static nano.topred.nanoplots.mymath.Mean.mean;


public class PlotGeometry
{
    private Polygon2D plotShape;

    private Position meanPosition;

    private ArrayList<Point2D> surface;

    private double yMin;

    private double yMax;

    private double yMean;

    private UUID worldId;

    public PlotGeometry(Polygon2D shape, double yMin, double yMax, UUID worldId)
    {
        this.plotShape = shape;
        this.surface = this.plotShape.getSurfacePoints();
        this.surface.addAll(this.plotShape.edgesToPoint2D());
        this.yMin = yMin;
        this.yMax = yMax;
        this.worldId = worldId;
        reCalcMeanPosition();
    }

    public void addControlePoint(Position p)
    {
        this.yMean = mean(yMin,yMax);
        this.plotShape.addVertex(new Point2D(p.getX(),p.getZ()));
    }

    public boolean isInside(Position p)
    {
        double x = p.getX();
        double y = p.getY();
        double z = p.getZ();
        if(y < yMin || y > yMax)
            return false;
        return this.plotShape.isInside(new Point2D(x,z));
    }

    public void showBorder(Player player)
    {
        ArrayList<Position> positions = this.plotShape.edgesToWorldPositions(this.yMean, this.worldId);
        Position.positionsToBlocks(positions,player, Material.ORANGE_WOOL.createBlockData());
    }

    public void unShowBorder(Player player)
    {
        ArrayList<Position> positions = this.plotShape.edgesToWorldPositions(this.yMean, this.worldId);
        Position.updatePositionBlocks(positions,player);
    }

    public void showBoundingBox(Player player) {
        if (this.plotShape.getBoundingBox() != null) {
            ArrayList<Position> positions = this.plotShape.boundingBoxToPositions(this.yMean, this.worldId);
            Position.positionsToBlocks(positions, player, Material.GREEN_WOOL.createBlockData());
        }
    }

    public void unShowBoundingBox(Player player)
    {
        if(this.plotShape.getBoundingBox() != null)
        {
            ArrayList<Position> positions = this.plotShape.boundingBoxToPositions(this.yMean, this.worldId);
            Position.updatePositionBlocks(positions,player);
        }
    }

    public void showSurface(Player player)
    {
        ArrayList<Position> positions = this.plotShape.surfaceToPositions(this.yMean, this.worldId);
        Position.positionsToBlocks(positions,player,Material.BLUE_WOOL.createBlockData());
    }

    public void unShowSurface(Player player)
    {
        Position.updatePositionBlocks(this.plotShape.surfaceToPositions(this.yMean, this.worldId),player);
    }

    public void showControlePoints(Player player)
    {
        ArrayList<Position> positions = this.plotShape.verticesToWorldPositions(this.yMean, this.worldId);
        Position.positionsToBlocks(positions,player,Material.BLACK_WOOL.createBlockData());
    }



    public void reCalcMeanPosition()
    {
        this.yMean = mean(yMin,yMax);
        if (this.plotShape.getBoundingBox()!=null)
            this.meanPosition = this.plotShape.getBoundingBox().getCenter().toWorldPosition(this.yMean, this.worldId);
        else
            this.meanPosition = null;
    }

    public Polygon2D getPlotShape() {
        return this.plotShape;
    }

    public void setPlotShape(Polygon2D plotShape) {

        this.plotShape = plotShape;
        reCalcMeanPosition();
    }

    public Position getMeanPosition() {
        return this.meanPosition;
    }


    public double getYMin() {
        return yMin;
    }

    public void setYMin(double yMin) {
        this.yMin = yMin;
        reCalcMeanPosition();
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {

        this.yMax = yMax;
        reCalcMeanPosition();
    }

    public double getYMean() {
        return yMean;
    }

    public ArrayList<Point2D> getSurface()
    {
        return this.surface;
    }

}
