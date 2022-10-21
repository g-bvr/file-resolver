package org.jkube.gitbeaver;

import org.jkube.gitbeaver.interfaces.FileResolver;
import org.jkube.gitbeaver.richfile.RichFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class RichFileResolver implements FileResolver {
    @Override
    public List<String> resolve(Path workspacePath, Path relativePath, Map<String, String> variables, boolean resolveVariables) {
        RichFile rf = new RichFile(workspacePath, relativePath);
        return resolveVariables ? rf.resolve(variables) : rf.resolve();
    }
}
