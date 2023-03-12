package Zoo_Project;

import java.util.List;

public interface Zoo_Service<T> {


    void createTable();

    void ListAll();

    void addANew();

    void update(int id);

    void filter();

    void delete(int id);


}
