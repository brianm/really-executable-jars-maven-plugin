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
import java.util.ArrayList;
import java.util.List;

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
            List<File> files = new ArrayList<File>();

            if (project.getArtifact() != null && project.getArtifact().getType().equals("jar")) {
                files.add(project.getArtifact().getFile());
            }

            for (Object item : project.getAttachedArtifacts()) {
                AttachedArtifact artifact = (AttachedArtifact) item;
                if (artifact.getType().equals("jar")) {
                    files.add(artifact.getFile());
                }
            }

            if (files.isEmpty()) {
                throw new MojoExecutionException("Could not find any jars to make executable");
            }

            for (File file : files) {
                makeExecutable(file);
            }
        }
        catch (IOException e) {
            throw new MojoExecutionException(e, "FAILURE!", e.getMessage());
        }

    }

    private void makeExecutable(File file)
            throws IOException
    {
        getLog().debug("Making " + file.getAbsolutePath() + " executable");

        File tmp = File.createTempFile("waffles", ".tmp");
        try {
            FileUtils.rename(file, tmp);

            FileOutputStream out = new FileOutputStream(file);
            FileInputStream in = new FileInputStream(tmp);
            out.write(("#!/bin/sh\n\nexec java " + flags + " -jar $0 \"$@\"\n\n").getBytes("ASCII"));
            IOUtil.copy(in, out);
            in.close();
            out.close();
            Runtime.getRuntime().exec("chmod +x " + file.getAbsolutePath());
        }
        finally {
            tmp.delete();
        }
    }
}
