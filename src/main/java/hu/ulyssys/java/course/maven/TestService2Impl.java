package hu.ulyssys.java.course.maven;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Specializes;
import javax.inject.Named;

public class TestService2Impl implements TestService{

    @Override
    public void print() {
        System.out.println("Almafa2");
    }
}
