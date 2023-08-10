package entities;

public class Id {
	
    private static int proximoId = 1;
    private int id;

    public Id() {
        this.id = proximoId++;
    }

    public int getId() {
        return id;
    }
}
