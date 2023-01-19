// update the class
import java.lang.RuntimeException;
class UncheckedException extends RuntimeException{
    public UncheckedException(){
        
    }
}

//do not change the code
class Main {
    public static void main(String[] args) {
        
        UncheckedException uncheckedException = new UncheckedException();
        System.out.println(uncheckedException instanceof RuntimeException);
    }
}
