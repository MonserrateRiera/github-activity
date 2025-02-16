public class Activity {
    private final String type;
    private int count;
    private final String repo;

    public Activity( String type, String repo){
        this.type = type;
        this.count = 1;
        this.repo = repo;
    }

    public void incrementCount() {
        this.count ++;
    }

    public String getType() {
        return type;
    }



    public String getRepo() {
        return repo;
    }


    public String toString(){
        return this.type+" "+this.count+" times to "+this.repo;
    }
}
