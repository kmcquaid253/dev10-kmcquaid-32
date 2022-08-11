public class Musician {

     String name;
    private int rating;

    /**
     * @param name   The name of the musician.
     * @param rating A number representing how much a musician is loved relative to other musicians.
     */
    //Constructor
    public Musician(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }

    //Empty Constructor
    public Musician(){

    }

    //getters
    public String getName() {
        return name;
    }

    public int getRating(){
        return rating;
    }

    //setters
    public void setName(String name){
        this.name = name;
    }

    public void setRating(int rating){
        this.rating = rating;
    }
}
