package yamaha.catalogomotos.model;

public class Moto {
    String name;
    int image;

    public Moto() {

    }

    public Moto(String na, int img) {
        this.name = na;
        this.image = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
