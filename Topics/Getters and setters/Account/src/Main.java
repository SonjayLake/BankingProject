class Account {

    private long balance;
    private String ownerName;
    private boolean locked;

    public long getBalance(){
        return this.balance;
    }
    
    public void setBalance(long val){
        this.balance = val;
    }
    public String getOwnerName(){
        return this.ownerName;
    }

    public void setOwnerName(String name){
        this.ownerName = name;
    }

    public boolean isLocked(){
        return this.locked;
    }

    public void setLocked(boolean val){
        this.locked = val;
    }
}
