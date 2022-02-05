/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unitp.pkg0.pkg2;

/**
 *
 * @author Yarden zamir
 */
public class PFuelableObject extends PMovableObject {

    private int maxFuel;
    private int fuel;
    private int fuelCost;
    private boolean requireFuelToMove;

    /**
     *
     * @param maxFuel
     * @param fuel
     * @param fuelCost the cost of fuel per pixel
     * @param requireFuelToMove
     */
    public PFuelableObject(int maxFuel, int fuel, int fuelCost, boolean requireFuelToMove, boolean displayFuel) {
        this.maxFuel = maxFuel;
        this.fuel = fuel;
        this.fuelCost = fuelCost;
        this.requireFuelToMove = requireFuelToMove;
        if (displayFuel)
            setToolTipText(fuel + " / " + maxFuel);
        
    }

    public boolean getRequireFuelToMove(){
        return requireFuelToMove;
    }
    public void setFuel(int fuel){
        this.fuel = fuel;
    }
    public int getFuelCost(){
        return this.fuelCost;
    }
    public int getFuel() {
        return this.fuel;
    }

    public int getMaxFuel() {
        return this.maxFuel;
    }

    public void updateFuelDisplay() {
        setToolTipText(getFuel() + " / " + getMaxFuel());
    }

    @Override
    public void moveObj(double x, double y, int time) {
        super.moveObj(x, y, time); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void moveObjTo(int x, int y, int time) {
        super.moveObjTo(x, y, time); //To change body of generated methods, choose Tools | Templates.
    }

}
