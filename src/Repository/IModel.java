package Repository;

import Model.EGender;
import Model.EPosition;

public interface IModel <T> {
    long getId();
    String getName();
    EPosition getEPosition();

    void update(T objNew);

    T parseData(String line);
}
