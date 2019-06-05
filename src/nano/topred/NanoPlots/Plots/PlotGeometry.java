package nano.topred.NanoPlots.Plots;
import nano.topred.NanoPlots.MyMath.Geometry.G2D.Point2D;
import nano.topred.NanoPlots.MyMath.Geometry.G2D.Polygon2D;
import nano.topred.NanoPlots.Position;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.UUID;

import static nano.topred.NanoPlots.MyMath.Mean.mean;


public class PlotGeometry
{
    private Polygon2D plotShape;
    private Position meanPosition;
    private double yMin;
    private double yMax;
    private double yMean;
    private UUID worldId;

    public PlotGeometry(Polygon2D shape, double yMin, double yMax, UUID worldId)
    {
        this.plotShape = shape;
        this.yMin = yMin;
        this.yMax = yMax;
        this.worldId = worldId;
        recalc();
    }

    public void addControlePoint(Position p)
    {
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



    public void recalc()
    {
        this.yMean = mean(yMin,yMax);
        if (this.plotShape.getBoundingBox()!=null)
            this.meanPosition = this.plotShape.getBoundingBox().getCenter().toWorldPosition(this.yMean, this.worldId);
        else
            this.meanPosition = null;
    }

    public Polygon2D getPlotShape() {
        return plotShape;
    }

    public void setPlotShape(Polygon2D plotShape) {

        this.plotShape = plotShape;
        recalc();
    }

    public Position getMeanPosition() {
        return meanPosition;
    }


    public double getYMin() {
        return yMin;
    }

    public void setYMin(double yMin) {
        this.yMin = yMin;
        recalc();
    }

    public double getYMax() {
        return yMax;
    }

    public void setYMax(double yMax) {

        this.yMax = yMax;
        recalc();
    }

    public double getYMean() {
        return yMean;
    }


}
