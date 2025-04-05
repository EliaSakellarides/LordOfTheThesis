

public class Room {
    private String name;
    private String description;
    
    public Room(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getLongDescription() {
        return "Sei in " + name + ". " + description;
    }
}
