package com.github.liufarui;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author lfr
 * @date 2020/11/18 16:43
 */
@Mojo(name = "test1", requiresProject = false, defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class TestMojo1 extends AbstractMojo {
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("Hello maven, This is my Second plugin.");
    }
}
