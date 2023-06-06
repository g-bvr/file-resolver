package org.jkube.gitbeaver;

import org.jkube.gitbeaver.richfile.RichFile;
import org.jkube.gitbeaver.util.FileUtil;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.jkube.logging.Log.log;

/**
 * Usage: resolve source target
 */
public class ResolveAppendCommand extends AbstractCommand {

    private static final String SOURCE = "source";
    private static final String TARGET = "target";

    public ResolveAppendCommand() {
        super("resolve a file (using the rich file resolver)");
        commandline("RESOLVE "+SOURCE+" AND APPEND TO "+TARGET);
        argument(SOURCE, "the file to be resolved");
        argument(TARGET, "the file to which the resolution result will be appended");
    }

    @Override
    public void execute(Map<String, String> variables, WorkSpace workSpace, Map<String, String> arguments) {
        Path sourcePath = workSpace.getAbsolutePath(arguments.get(SOURCE));
        Path targetPath = workSpace.getAbsolutePath(arguments.get(TARGET));
        log("Resolving "+sourcePath+" and appending to "+targetPath);
        List<String> resolved = new RichFile(workSpace.getWorkdir(), sourcePath).resolve(variables);
        FileUtil.createIfNotExists(targetPath.getParent());
        FileUtil.append(resolved, targetPath.toFile());
    }
}
