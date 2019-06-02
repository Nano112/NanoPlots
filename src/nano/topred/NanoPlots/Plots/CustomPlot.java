package nano.topred.NanoPlots.Plots;

import nano.topred.NanoPlots.Position;
import nano.topred.NanoPlots.MyMath.Rounding;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomPlot
{
    private  ArrayList<Position> controlePoints;

    public CustomPlot()
    {
        this.controlePoints = new ArrayList<>();
    }

    public void addPosition(Position p)
    {
        this.controlePoints.add(p);
    }

    public void showControlePoints(Player player)
    {
        Position.positionsToBlocks(this.controlePoints,player,Material.BLACK_WOOL.createBlockData());
    }

    public void unShowControlePoints(Player player)
    {
        Position.updatePositionBlocks(this.controlePoints,player);
    }

    public void showBorder(Player player)
    {
        Position.positionsToBlocks(getBorderPosition(),player,Material.ORANGE_WOOL.createBlockData());
    }

    public void unShowBorder(Player player)
    {
        Position.updatePositionBlocks(getBorderPosition(),player);
    }

    public ArrayList<Position> getBorderPosition()
    {

        int positionsLenght = this.controlePoints.size();
        if (positionsLenght==0)
        {
            return new ArrayList<Position>();
        }
        if (positionsLenght==1)
        {
            return this.controlePoints;
        }
        ArrayList<Position> border = new ArrayList<>();

        Position pos0 = this.controlePoints.get(positionsLenght-1);
        Position pos1 = this.controlePoints.get(0);
        border.addAll(getEdge(pos0,pos1));
        for (int i = 1; i < positionsLenght; ++i)
        {
            pos0=pos1;
            pos1=this.controlePoints.get(i);
            border.addAll(getEdge(pos0,pos1));
        }
        return border;
    }



    private ArrayList<Position>  getEdge( Position pos0, Position pos1) {
        //Bresenhams algorithm from rosetta code
        int x1 = (int)signedFloor(pos0.getX());
        int x2 = (int)Math.floor(pos1.getX());
        int y1 = (int)Math.floor(pos0.getZ());
        int y2 = (int)Math.floor(pos1.getZ());
        ArrayList<Position> edge = new ArrayList<>();
        int d = 0;

        int dx = Math.abs(x2 - x1);
        int dy = Math.abs(y2 - y1);

        int dx2 = 2 * dx; // slope scaling factors to
        int dy2 = 2 * dy; // avoid floating point

        int ix = x1 < x2 ? 1 : -1; // increment direction
        int iy = y1 < y2 ? 1 : -1;

        int x = x1;
        int y = y1;

        if (dx >= dy) {
            while (true) {
                edge.add(new Position(x,pos0.getY(),y,pos0.getWorld()));
                if (x == x2)
                    break;
                x += ix;
                d += dy2;
                if (d > dx) {
                    y += iy;
                    d -= dx2;
                }
            }
        } else {
            while (true) {
                edge.add(new Position(x,pos0.getY(),y,pos0.getWorld()));
                if (y == y2)
                    break;
                y += iy;
                d += dx2;
                if (d > dy) {
                    x += ix;
                    d -= dy2;
                }
            }
        }
        return edge;
    }

    public ArrayList<Position> getControlePoints()
    {
        return this.controlePoints;
    }

    public void setControlePoints(ArrayList<Position> p)
    {
        this.controlePoints = p;
    }
}
