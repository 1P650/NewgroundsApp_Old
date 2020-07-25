package jewpigeon.apps.newgrounds.Fundamental;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class NG_PreferencesData {


    @Id(assignable = true) public long ID;
    private boolean isUserLogged;

    public void setLogged(boolean isLogged){
        this.isUserLogged = isLogged;
    }

    public void setID(long id){
        this.ID = id;
    }

    public boolean isUserLogged(){
        return this.isUserLogged;
    }
}
