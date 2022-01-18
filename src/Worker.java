public class Worker extends Consumer{
    private int rank;

    public Worker(String firstName, String lastName, String username, String password, int rank) {
        super(firstName, lastName, username, password);
        this.rank = rank;
    }

    public String introduce() {
        return super.introduce()+"(" +(rank==1? "worker":(rank==2? "manager": "big manager" +")"))+"!";
    }

    public int getRank() {
        return rank;
    }

    @Override
    public boolean isWorker() {
        return true;
    }
}
