package org.jkube.gitbeaver;

import org.jkube.gitbeaver.richfile.RichFile;
import org.jkube.gitbeaver.util.FileUtil;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.jkube.gitbeaver.logging.Log.log;
import static org.jkube.gitbeaver.logging.Log.onException;

/**
 * Usage: resolve source target
 */
public class ResolveCommand extends AbstractCommand {

    private static final String SOURCE = "source";
    private static final String TARGET = "target";

    public ResolveCommand() {
        super("resolve a file (using the rich file resolver)");
        commandline("RESOLVE "+SOURCE+" TO "+TARGET);
        argument(SOURCE, "the file to be resolved");
        argument(TARGET, "the file into which the resolution result is written");
    }

    @Override
    public void execute(Map<String, String> variables, WorkSpace workSpace, Map<String, String> arguments) {
        Path sourcePath = workSpace.getAbsolutePath(arguments.get(SOURCE));
        Path targetPath = workSpace.getAbsolutePath(arguments.get(TARGET));
        log("Resolving "+sourcePath+" to "+targetPath);
        List<String> resolved = new RichFile(workSpace.getWorkdir(), sourcePath).resolve(variables);
        FileUtil.createIfNotExists(targetPath.getParent());
        onException(() -> Files.write(targetPath, resolved)).fail("Could not write resolved lines to "+targetPath);
    }
}
