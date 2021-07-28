package hu.ulyssys.java.course.maven;

import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.enterprise.inject.Specializes;

@Specializes
public class TestServiceImpl extends TestService2Impl implements TestService {

    public void print() {
        System.out.println("Almafa");
    }
}
