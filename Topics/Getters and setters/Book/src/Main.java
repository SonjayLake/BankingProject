class Book {

    private String title;
    private int yearOfPublishing;
    private String[] authors;   

    public void setTitle(String name){
        this.title = name;
    }

    public String getTitle(){
        return this.title;
    }

    public int getYearOfPublishing(){
        return this.yearOfPublishing;
    }

    public void setYearOfPublishing(int year){
        this.yearOfPublishing = year;
    }

    public String[] getAuthors(){
        return this.authors;
    }

    public void setAuthors(String[] authorList){
        this.authors = authorList;
    }
}
