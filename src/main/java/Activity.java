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

    public String createMessage(){
        switch (this.type){
            case "PushEvent":
                return "User pushed "+ this.count +" commits to "+this.repo;
            case "CreateEvent":
                return "User created "+ this.count +" branches or tags to "+this.repo;
            case "ForkEvent":
                return "User forked "+ this.count +" times the repo "+this.repo;
            case "IssueCommentEvent":
                return "User made "+ this.count +" comments at issues to "+this.repo;
            case "IssuesEvent":
                return "User interacted "+ this.count +" issues to "+this.repo;
            case "PullRequestReviewEvent":
                return "User made "+ this.count +" pull requests to "+this.repo;
            case "WatchEvent":
                return "User stared "+ this.count +" to "+this.repo;
        }
        return "";
    }
    public String toString(){
        return "User "+(this.type)+" "+this.count+" times to "+this.repo;
    }
}
