package org.skife.waffles;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.project.MavenProject;
import org.apache.maven.project.artifact.AttachedArtifact;
import org.codehaus.plexus.util.FileUtils;
import org.codehaus.plexus.util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @goal really-executable-jar
 */
public class MyMojo extends AbstractMojo
{

    /**
     * The Maven project.
     *
     * @parameter expression="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * The greeting to display.
     *
     * @parameter
     */
    private String flags = "";

    public void execute() throws MojoExecutionException
    {
        try {

            for (Object o : project.getAttachedArtifacts()) {
                AttachedArtifact a = (AttachedArtifact) o;
                File f = a.getFile();
                File tmp = File.createTempFile("waffles", ".tmp");

                FileUtils.rename(f, tmp);

                FileOutputStream fout = new FileOutputStream(f);
                FileInputStream in = new FileInputStream(tmp);
                fout.write(("#!/bin/sh\n\nexec java " + flags + " -jar $0 \"$@\"\n\n").getBytes());
                IOUtil.copy(in, fout);
                in.close();
                fout.close();
                Runtime.getRuntime().exec("chmod +x " + f.getAbsolutePath());
            }
        }
        catch (IOException e) {
            throw new MojoExecutionException(e, "FAILURE!", e.getMessage());
        }

    }
}
