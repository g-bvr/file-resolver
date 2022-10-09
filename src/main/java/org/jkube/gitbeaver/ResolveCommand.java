package org.jkube.gitbeaver;

import org.jkube.gitbeaver.richfile.RichFile;
import org.jkube.gitbeaver.util.FileUtil;
import org.jkube.util.Expect;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static org.jkube.logging.Log.log;
import static org.jkube.logging.Log.onException;

/**
 * Usage: resolve source target
 */
public class ResolveCommand extends AbstractCommand {

    public ResolveCommand() {
        super(2,2, "resolve");
    }

    @Override
    public void execute(Map<String, String> variables, WorkSpace workSpace, List<String> arguments) {
        Path sourcePath = workSpace.getAbsolutePath(arguments.get(0));
        Path targetPath = workSpace.getAbsolutePath(arguments.get(1));
        log("Resolving "+sourcePath+" to "+targetPath);
        FileUtil.createIfNotExists(targetPath.getParent());
        onException(() -> Files.write(targetPath, new RichFile(workSpace.getWorkdir(), sourcePath).resolve(variables)))
                .fail("Could not write resolved lines to "+targetPath);
        log("Resolving "+sourcePath+" to "+targetPath);
    }
}
