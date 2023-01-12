package Model;

import Repository.IModel;
import Unit.DateUnit;

public class FAB implements IModel<FAB> {
    private long id;
    private String name;
    private ESize eSize;
    private double price;
    private EStatus eStatus;
    public FAB(){

    }
    public FAB(long id, String name, ESize eSize, double price, EStatus eStatus) {
        this.id = id;
        this.name = name;
        this.eSize = eSize;
        this.price = price;
        this.eStatus = eStatus;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ESize getESize() {
        return eSize;
    }

    public void setESize(ESize eSize) {
        this.eSize = eSize;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public EStatus getEStatus() {
        return eStatus;
    }

    public void setEStatus(EStatus eStatus) {
        this.eStatus = eStatus;
    }

    @Override
    public EPosition getEPosition() {
        return null;
    }
    @Override
    public void update(FAB objNew) {
        this.name = objNew.getName();
        this.eSize = objNew.getESize();
        this.price = objNew.getPrice();
        this.eStatus = objNew.getEStatus();
    }
    @Override
    public FAB parseData(String line) {
        String [] items = line.split(",");
        long id = Long.parseLong(items[0]);
        ESize size = ESize.getESizeByName(items[2].trim());
        double price = Double.parseDouble(items[3]);
        EStatus status = Model.EStatus.getEStatusByName(items[4].trim());
        FAB fab = new FAB (id, items[1],size,price, status);
        return fab;
    }
    @Override
    public String toString() {
        return String.format("%s,%s,%s,%s,%s", this.getId(), this.getName(),String.valueOf(this.getESize()),this.getPrice(),String.valueOf(this.getEStatus()));
    }
    public String toFABView() {
        return String.format("%10s|%20s|%10s|%15s|%10s", this.id,this.name, this.eSize, this.price, this.eStatus);
    }
}
