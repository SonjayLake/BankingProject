class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if( this.firstName != null || !(this.firstName.equals("") ) ) {
            this.firstName = firstName;
        }
    }

    public void setLastName(String lastName) {
        if(!(this.lastName == null) || !(this.lastName.equals("")) ){
            this.lastName = lastName;
        }
    }

    public String getFullName() {
        if(this.lastName == null || this.lastName.equals("")){
            if(this.firstName == null || this.firstName.equals("")){
                //first and last name empty
                return "Unknown";
            }
            //last name empty, first name there
            return this.firstName;
        }
        if(this.firstName == null || this.firstName.equals("")){
            //first name empty, last name present
            return this.lastName;
        }
        //first name and last name present
        return this.firstName + " " + this.lastName;
    }
}
