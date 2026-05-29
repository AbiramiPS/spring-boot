package spring.learning.demo;

// making service file to talk to db using repository file
public class DemoService{
    private DemoRepository demoRepository; // create instance for repository

    public DemoService(){  // constructor 
        demoRepository = new DemoRepository();
    }

    public void printDemo(){
        System.out.println(demoRepository.getAllTodos());  // calls the demo repository function
    }
} 